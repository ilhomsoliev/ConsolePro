package com.ilhomsoliev.consolepro.presentation.page.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.ilhomsoliev.consolepro.presentation.page.viewmodel.PageDetailsViewModel

@Composable
fun PageDetailsScreen(
    vm: PageDetailsViewModel,
    navigateUp: () -> Unit,
    pageId: Int,
) {

    val pageName by vm.pageName.collectAsState()
    val pageContent by vm.pageContent.collectAsState()

    LaunchedEffect(key1 = Unit, block = {
        vm.queryPage(pageId)
    })

    PageDetailsContent(
        state = PageDetailsState(
            pageName = pageName,
            pageContent = pageContent,
        ),
        callback = object : PageDetailsCallback {
            override fun navigateUp() {
                navigateUp()
            }
        }
    )
}