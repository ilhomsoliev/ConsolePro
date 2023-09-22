package com.ilhomsoliev.consolepro.presentation.search.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.consolepro.R
import com.ilhomsoliev.consolepro.data.model.PageItem
import com.ilhomsoliev.consolepro.presentation.components.Pages
import com.ilhomsoliev.consolepro.presentation.components.Toolbar

data class SearchState(
    val hasResults: Boolean,
    val pages: List<PageItem>
)

interface SearchCallback {
    fun openPageDetails(id: Int)
    fun querySearch(value: String)
}

@Composable
fun SearchContent(
    state: SearchState,
    callback: SearchCallback,
) {
    Column(Modifier.padding(horizontal = 8.dp)) {
        Toolbar {
            SearchField {
                callback.querySearch(it)
            }
        }
        if (state.hasResults) {
            Pages(
                modifier = Modifier.padding(horizontal = 8.dp),
                pages = state.pages,
                openPageDetails = {
                    callback.openPageDetails(it)
                }
            )
        } else
            Text(text = stringResource(R.string.no_result))
    }
}

@Composable
private fun SearchField(
    onQueryChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        BasicTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .focusRequester(focusRequester),
            textStyle = LocalTextStyle.current.copy(MaterialTheme.colors.onBackground),
            cursorBrush = SolidColor(Color.Red),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box {
                    if (textFieldValue.text.isEmpty()) Text(stringResource(R.string.search_field))
                    innerTextField()
                }
            }
        )
        ClearTextButton(textFieldValue.text) { textFieldValue = TextFieldValue() }
    }

    LaunchedEffect(Unit) { focusRequester.requestFocus() }
    LaunchedEffect(textFieldValue) {
        onQueryChange(textFieldValue.text)
    }
}

@Composable
fun ClearTextButton(text: String, onClick: () -> Unit) {
    if (text.isNotEmpty()) {
        IconButton(onClick = { onClick() }) {
            Icon(
                imageVector = Icons.Rounded.Clear,
                contentDescription = stringResource(R.string.clear)
            )
        }
    }
}
