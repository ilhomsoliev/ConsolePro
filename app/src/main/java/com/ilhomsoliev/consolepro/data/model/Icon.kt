package com.ilhomsoliev.consolepro.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.ilhomsoliev.consolepro.ui.theme.History
import com.ilhomsoliev.consolepro.ui.theme.LabelImportant

enum class Icon(
    val imageVector: ImageVector,
    val description: String
) {
    RECENT_PAGE(Icons.Rounded.History, "Recently visited page"),
    FREQUENT_PAGE(Icons.Rounded.LabelImportant, "Frequently visited page"),
    SEARCH_RESULT(Icons.Rounded.Search, "Search Result")
}
