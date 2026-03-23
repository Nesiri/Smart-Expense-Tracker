package com.example.expense_tracker_app.ui.theme.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.navigation.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    navController: NavHostController,
    title: String,
    content: @Composable (PaddingValues) -> Unit
) {
    val currentRoute = navController.currentBackStackEntry?.destination?.route

    val isHome = currentRoute == Screen.Home.route

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(title) },
                //  BACK BUTTON ONLY IF NOT HOME
                navigationIcon = {
                    if (!isHome) {
                        androidx.compose.material3.IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            androidx.compose.material3.Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },


    ) { paddingValues ->
        content(paddingValues)
    }
}