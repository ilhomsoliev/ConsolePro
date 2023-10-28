package com.ilhomsoliev.consolepro.presentation.search.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ilhomsoliev.consolepro.presentation.search.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    vm: SearchViewModel,
    openPageDetails: (Int) -> Unit
) {

    val controller = rememberSystemUiController()

    LaunchedEffect(key1 = Unit, block = {
        controller.setSystemBarsColor(Color(0xFFFFFFFF))
        controller.setNavigationBarColor(Color(0xFFFFFFFF))
        controller.setStatusBarColor(Color(0xFFFFFFFF))
    })

    val scope = rememberCoroutineScope()

    val resultsPages by vm.resultsPages.collectAsState()

    SearchContent(
        SearchState(hasResults = resultsPages.isNotEmpty(), pages = resultsPages),
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