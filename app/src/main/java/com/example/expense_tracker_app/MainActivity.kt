package com.example.expense_tracker_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker_app.ui.theme.ExpenseTrackerAppTheme
import com.example.expense_tracker_app.screens.AddScreen
import com.example.expense_tracker_app.screens.EditScreen
import com.example.expense_tracker_app.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpenseTrackerAppTheme {
                Surface ( modifier = Modifier.fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                ){
                    ExpenseTrackerApp()
                }

            }
        }
    }
}

@Composable
fun ExpenseTrackerApp() {
    val navController = rememberNavController()
    //val viewModel: ExpenseViewModel = viewModel()
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home") {
            HomeScreen(
                navController = navController,
               // viewModel = viewModel
            )
        }
        composable("add") {
            AddScreen(
                navController = navController,

            )
        }
        composable("edit") {
            EditScreen(
                navController = navController,

                )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun  ExpenseTrackerAppPreview() {
    ExpenseTrackerAppTheme {
        ExpenseTrackerApp()
    }
}