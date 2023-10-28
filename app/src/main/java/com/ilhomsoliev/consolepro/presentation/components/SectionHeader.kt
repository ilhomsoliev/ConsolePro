package com.ilhomsoliev.consolepro.presentation.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun SectionHeader(text: String) {
    Text(
        text = text,
        maxLines = 1,
        style = androidx.compose.material3.MaterialTheme.typography.displaySmall
    )
}
