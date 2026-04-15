package com.example.expense_tracker_app.navigation

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(
    val route: String,
    val title: String,

) {

    object Home : Screen(
        route = "home",
        title = "Expense Tracker",
    )



    object Edit : Screen(
        route = "edit/{expenseId}",
        title = "Edit Expense"
    ) {
        fun createRoute(expenseId: Int): String {
            return "edit/$expenseId"
        }
    }

    object Category : Screen(
        route = "category/{categoryName}",
        title = "{categoryName}"
    ) {
        fun createRoute(categoryName: String): String {
            val encoded =
                URLEncoder.encode(categoryName, StandardCharsets.UTF_8.toString())
            return "category/$encoded"
        }
    }
}