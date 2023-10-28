package com.ilhomsoliev.consolepro.presentation.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ilhomsoliev.consolepro.presentation.home.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    vm: HomeViewModel,
    openSearch: () -> Unit,
    openPageDetails: (pageId: Int) -> Unit
) {

    val controller = rememberSystemUiController()

    LaunchedEffect(key1 = Unit, block = {
        controller.setSystemBarsColor(Color(0xFFFFFFFF))
        controller.setNavigationBarColor(Color(0xFFFFFFFF))
        controller.setStatusBarColor(Color(0xFFFFFFFF))
    })

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