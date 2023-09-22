package com.ilhomsoliev.consolepro.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ilhomsoliev.consolepro.data.model.PageItem

@Composable
fun Pages(
    modifier: Modifier = Modifier,
    pages: List<PageItem>,
    openPageDetails: (Int) -> Unit
) {
    Column(modifier) {
        pages.forEach { page ->
            ListItem(page, openPageDetails)
        }
    }
}
