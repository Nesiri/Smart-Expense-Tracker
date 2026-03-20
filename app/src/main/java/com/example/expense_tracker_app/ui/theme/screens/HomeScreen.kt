package com.example.expense_tracker_app.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){
    Scaffold(
        topBar = {
            Text("Expense Tracker")
                 },
        floatingActionButton = {
            Button(onClick = {}) {
                Text("Edit Expense")
            }
        }
    ){
        paddingValues ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(paddingValues)) {

        }
    }
}