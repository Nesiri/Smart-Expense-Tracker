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
import androidx.navigation.compose.rememberNavController
import com.example.expense_tracker_app.navigation.AppNavGraph
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.root.AppContainer
import com.example.expense_tracker_app.ui.theme.ExpenseTrackerAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        appContainer = AppContainer(this)
        setContent {
            ExpenseTrackerAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    ExpenseTrackerApp(
                        appRepository = appContainer.appRepository
                    )
                }
            }
        }
    }
}

@Composable
fun ExpenseTrackerApp(appRepository: AppRepository) {
    val navController = rememberNavController()
    AppNavGraph(
        navController = navController,
        appRepository = appRepository
    )
}