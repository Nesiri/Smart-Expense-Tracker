package com.example.expense_tracker_app.screens



import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.navigation.Screen
import com.example.expense_tracker_app.ui.theme.component.AppScaffold
import com.example.expense_tracker_app.viewModel.HomeViewModel


@Composable
fun AddScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
){
    AppScaffold(
        navController = navController,
        title = Screen.Add.title,
    ){
            paddingValues ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(paddingValues)
            .background(MaterialTheme.colorScheme.tertiary)

        ) {

        }
    }
}