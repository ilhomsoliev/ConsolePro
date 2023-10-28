package com.ilhomsoliev.consolepro.presentation.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.ilhomsoliev.consolepro.presentation.search.viewmodel.SearchViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchScreen(
    vm: SearchViewModel,
    openPageDetails: (Int) -> Unit
) {
    val scope = rememberCoroutineScope()

    val resultsPages by vm.resultsPages.collectAsState()

    SearchContent(
        SearchState(hasResults = resultsPages.isEmpty(), pages = resultsPages),
        object : SearchCallback {
            override fun openPageDetails(id: Int) {
                openPageDetails(id)
            }

            override fun querySearch(value: String) {
                scope.launch { vm.querySearch(value) }
            }
        }
    )
}