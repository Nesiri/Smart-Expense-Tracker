package com.example.expense_tracker_app.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.ui.theme.component.AppScaffold
import com.example.expense_tracker_app.viewModel.ExpenseViewModel
import com.example.expense_tracker_app.viewModel.ExpenseViewModelFactory
import java.util.*

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



    // Auto-load planned amount whenever category name changes
    LaunchedEffect(categoryName) {
        viewModel.loadPlannedAmount(categoryName)
    }

    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, _ ->
            viewModel.onDateChange(year, month)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    AppScaffold(
        navController = navController,
        title = "Add Expense",
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
        ) {

        }
    }
}