package com.example.expense_tracker_app.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.navigation.Screen
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.ui.theme.component.AppScaffold
import com.example.expense_tracker_app.viewModel.ExpenseViewModel
import com.example.expense_tracker_app.viewModel.ExpenseViewModelFactory
import java.util.Calendar

@Composable
fun AddScreen(
    navController: NavHostController,
    categoryName: String,
    appRepository: AppRepository
) {
    val context = LocalContext.current
    val viewModel: ExpenseViewModel = viewModel(
        factory = ExpenseViewModelFactory(appRepository)
    )

    val amount by viewModel.amount.collectAsState()
    val category by viewModel.category.collectAsState()
    val plannedAmount by viewModel.plannedAmount.collectAsState()
    val date by viewModel.date.collectAsState()
    val note by viewModel.note.collectAsState()

    LaunchedEffect(categoryName) {
        viewModel.onCategoryChange(categoryName)
        viewModel.loadPlannedAmount(categoryName)
    }

    val calendar = Calendar.getInstance()
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, _ ->
                viewModel.onDateChange(year, month)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

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
            OutlinedTextField(
                value = amount,
                onValueChange = { viewModel.onAmountChange(it) },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = category,
                onValueChange = { viewModel.onCategoryChange(it) },
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = date,
                onValueChange = {},
                readOnly = true,
                label = { Text("Date") },
                placeholder = { Text("MM/DD/YYYY") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    IconButton(onClick = { datePickerDialog.show() }) {
                        Icon(Icons.Default.DateRange, contentDescription = "Pick date")
                    }
                }
            )

            OutlinedTextField(
                value = note,
                onValueChange = { viewModel.onNoteChange(it) },
                label = { Text("Note") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp)
            )

            Button(
                onClick = {
                    viewModel.saveExpense(
                        title = category,
                        category = category,
                        amount = amount.toDoubleOrNull() ?: 0.0,
                        plannedAmount = plannedAmount,
                        description = note,
                        date = date
                    )
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save Expense")
            }
        }
    }
}
