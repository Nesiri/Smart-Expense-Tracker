package com.example.expense_tracker_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.ui.theme.component.AppScaffold

@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryName: String
) {

    AppScaffold(
        navController = navController,
        title = categoryName,
    ) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.tertiary)

        ) {

        }
    }
}
