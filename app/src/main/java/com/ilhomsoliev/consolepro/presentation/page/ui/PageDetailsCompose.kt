package com.ilhomsoliev.consolepro.presentation.page.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.consolepro.R

data class PageDetailsState(
    val pageName: String,
    val pageContent: AnnotatedString? = null
)

interface PageDetailsCallback {
    fun navigateUp()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PageDetailsContent(
    state: PageDetailsState,
    callback: PageDetailsCallback,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Black,
                elevation = 0.dp,
                title = { Text(text = state.pageName, color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { callback.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.navigate_back),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { innerPadding ->

        state.pageContent?.let { markdown ->
            Column(
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(markdown)
            }
        }
    }

    LocalSoftwareKeyboardController.current?.hide()

}