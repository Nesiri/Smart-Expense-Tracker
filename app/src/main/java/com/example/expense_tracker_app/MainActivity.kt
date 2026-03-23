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

import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker_app.navigation.AppNavGraph

import com.example.expense_tracker_app.ui.theme.ExpenseTrackerAppTheme


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
    AppNavGraph(navController)
}


@Preview(showBackground = true)
@Composable
fun  ExpenseTrackerAppPreview() {
    ExpenseTrackerAppTheme {
        ExpenseTrackerApp()
    }
}