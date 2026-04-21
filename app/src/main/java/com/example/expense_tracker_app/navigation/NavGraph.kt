package com.example.expense_tracker_app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.screens.AddScreen
import com.example.expense_tracker_app.screens.CategoryScreen
import com.example.expense_tracker_app.screens.EditScreen
import com.example.expense_tracker_app.screens.HomeScreen

@Composable
fun AppNavGraph(navController: NavHostController, appRepository: AppRepository) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                appRepository = appRepository
            )
        }

        composable(
            route = Screen.Add.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            AddScreen(
                navController = navController,
                categoryName = categoryName,
                appRepository = appRepository
            )
        }

        composable(
            route = Screen.Edit.route,
            arguments = listOf(navArgument("expenseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val expenseId = backStackEntry.arguments?.getInt("expenseId") ?: 0
            EditScreen(
                navController = navController,
                expenseId = expenseId,
                appRepository = appRepository
            )
        }

        composable(
            route = Screen.Category.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            CategoryScreen(
                navController = navController,
                categoryName = categoryName,
                appRepository = appRepository
            )
        }
    }
}
