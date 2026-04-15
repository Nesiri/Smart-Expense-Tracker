package com.example.expense_tracker_app.screens



import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
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
fun EditScreen(
    navController: NavHostController,
    expenseId: Int,
    appRepository: AppRepository
){
    val context = LocalContext.current
    val viewModel: ExpenseViewModel = viewModel(
        factory = ExpenseViewModelFactory(appRepository)
    )

    // Load expense when screen opens
    LaunchedEffect(expenseId) {
        viewModel.loadExpense(expenseId)
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
        title = Screen.Edit.title,
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