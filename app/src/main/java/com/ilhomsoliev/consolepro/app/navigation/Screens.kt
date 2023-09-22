package com.ilhomsoliev.consolepro.app.navigation

import androidx.navigation.NavBackStackEntry

sealed class Screens(val route: String) {
    data object Home : Screens("Home")
    data object Search : Screens("Search")
    data object Pages : Screens("pages/{pageId}") {
        fun buildRoute(chatId: Int): String = "pages/${chatId}"
        fun getPageId(entry: NavBackStackEntry): Int =
            entry.arguments!!.getString("pageId")?.toInt()
                ?: throw IllegalArgumentException("chatId argument missing.")
    }
}