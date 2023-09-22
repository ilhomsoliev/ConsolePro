package com.ilhomsoliev.consolepro.presentation.home.ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.consolepro.R
import com.ilhomsoliev.consolepro.data.model.PageItem
import com.ilhomsoliev.consolepro.presentation.components.Pages
import com.ilhomsoliev.consolepro.presentation.components.SectionHeader
import com.ilhomsoliev.consolepro.presentation.components.Toolbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class HomeState(
    val showToolbar: Boolean = false,
    val pages: List<PageItem> = emptyList()
) {
    val showMostFrequent: Boolean
        get() = pages.isNotEmpty()
}

interface HomeCallback {
    fun openSearch()
    fun openPageDetails(id: Int)
}

@Composable
fun HomeContent(
    state: HomeState,
    callback: HomeCallback,
) {
    Column(Modifier.padding(horizontal = 8.dp)) {
        SetupToolbar(state.showToolbar) {
            callback.openSearch()
        }

        if (state.showMostFrequent) {
            Spacer(Modifier.height(32.dp))
            FrequentPagesCard(pages = state.pages, openPageDetails = {
                callback.openPageDetails(it)
            })
        } else {
            DisplayAppName()
        }
    }
}

@Composable
private fun FrequentPagesCard(
    pages: List<PageItem>,
    openPageDetails: (pageId: Int) -> Unit
) {
    Column {
        SectionHeader(stringResource(R.string.frequent_pages))
        Spacer(Modifier.height(16.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = 4.dp
        ) {
            Pages(
                modifier = Modifier.padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
                pages = pages,
                openPageDetails = openPageDetails
            )
        }
    }
}

@Composable
private fun SetupToolbar(showToolbar: Boolean, openSearch: () -> Unit) {
    Crossfade(showToolbar) { toolbar ->
        if (toolbar) {
            Toolbar(Modifier.clickable { openSearch() }) {
                Text(stringResource(R.string.search_field))
            }
        } else {
            Spacer(Modifier.height(Toolbar.height))
        }
    }
}

@Composable
private fun DisplayAppName() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = BiasAlignment(0f, -0.2f)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row {
                val style = MaterialTheme.typography.h1
                Text(
                    text = stringResource(R.string.app_name),
                    style = style
                )
                TextCursor(style)
            }
            Spacer(Modifier.padding(16.dp))
            Text(
                text = stringResource(R.string.app_description),
                style = MaterialTheme.typography.h2
            )
        }
    }
}

@Composable
private fun TextCursor(
    textStyle: TextStyle
) {
    var cursor by remember { mutableStateOf(" ") }
    Text(text = cursor, style = textStyle)
    LaunchedEffect(Unit) {
        launch {
            while (true) {
                delay(700)
                cursor = if (cursor == " ") "_" else " "
            }
        }
    }
}
