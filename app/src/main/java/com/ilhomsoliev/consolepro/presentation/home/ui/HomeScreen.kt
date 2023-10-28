package com.ilhomsoliev.consolepro.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ilhomsoliev.consolepro.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel,
    openSearch: () -> Unit,
    openPageDetails: (pageId: Int) -> Unit
) {

    val showToolbar by vm.showToolbar.collectAsState()
    val pages by vm.pages.collectAsState()

    HomeContent(
        HomeState(
            showToolbar = showToolbar,
            pages = pages,
        ),
        object : HomeCallback {
            override fun openSearch() {
                openSearch()
            }

            override fun openPageDetails(id: Int) {
                openPageDetails(id)
            }
        }
    )
}