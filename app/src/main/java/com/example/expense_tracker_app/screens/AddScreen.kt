package com.example.expense_tracker_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.navigation.Screen
import com.example.expense_tracker_app.ui.theme.component.AppScaffold
import com.example.expense_tracker_app.viewModel.HomeViewModel

@Composable
fun AddScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel()
) {
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var note by remember { mutableStateOf("") }

    AppScaffold(
        navController = navController,
        title = Screen.Add.title,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Add Expense",
                style = MaterialTheme.typography.headlineSmall
            )

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = date,
                onValueChange = { date = it },
                label = { Text("Date") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = note,
                onValueChange = { note = it },
                label = { Text("Note") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Button(
                onClick = {
                    // save logic goes here later
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Expense")
            }
        }
    }
}