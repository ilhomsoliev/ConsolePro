package com.ilhomsoliev.consolepro.presentation.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ilhomsoliev.consolepro.presentation.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    vm: SearchViewModel,
    openPageDetails: (Int) -> Unit
) {
    val resultsPages by vm.resultsPages.collectAsState()

    SearchContent(
        SearchState(hasResults = resultsPages.isEmpty(), pages = resultsPages),
        object : SearchCallback {
            override fun openPageDetails(id: Int) {
                openPageDetails(id)
            }

            override fun querySearch(value: String) {
                vm.querySearch(value)
            }
        }
    )
}