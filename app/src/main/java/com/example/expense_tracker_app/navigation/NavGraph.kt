package com.example.expense_tracker_app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.expense_tracker_app.screens.AddScreen
import com.example.expense_tracker_app.screens.CategoryScreen
import com.example.expense_tracker_app.screens.EditScreen
import com.example.expense_tracker_app.screens.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

        composable(Screen.Add.route) {
            AddScreen(navController)
        }

        composable(Screen.Edit.route) {
            EditScreen(navController)
        }

        composable(
            route = Screen.Category.route,
            arguments = listOf(
                navArgument("categoryName") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val categoryName =
                backStackEntry.arguments?.getString("categoryName") ?: ""

            CategoryScreen(
                navController = navController,
                categoryName = categoryName
            )
        }
    }
}