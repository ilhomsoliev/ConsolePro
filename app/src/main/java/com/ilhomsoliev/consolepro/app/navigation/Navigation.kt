package com.ilhomsoliev.consolepro.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ilhomsoliev.consolepro.presentation.home.ui.HomeScreen
import com.ilhomsoliev.consolepro.presentation.page.ui.PageDetailsScreen
import com.ilhomsoliev.consolepro.presentation.search.ui.SearchScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(Screens.Home.route) {
            HomeScreen(
                vm = koinViewModel(),
                openSearch = { navController.navigate(Screens.Search.route) },
                openPageDetails = { pageId ->
                    navController.navigate(Screens.Pages.buildRoute(pageId))
                }
            )
        }
        composable(Screens.Search.route) {
            SearchScreen(
                vm = koinViewModel(),
                openPageDetails = { pageId ->
                    navController.navigate(Screens.Pages.buildRoute(pageId))
                }
            )
        }
        composable(
            route = Screens.Pages.route,
            // arguments = listOf(navArgument(PAGE_ID) { type = NavType.LongType }),
        ) {
            val pageId = Screens.Pages.getPageId(it)
            PageDetailsScreen(
                vm = koinViewModel(),
                navigateUp = navController::navigateUp,
                pageId = pageId,
            )
        }
    }
}