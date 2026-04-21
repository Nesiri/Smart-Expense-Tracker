package com.example.expense_tracker_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.navigation.Screen
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.ui.theme.component.AppScaffold
import com.example.expense_tracker_app.viewModel.HomeViewModel
import com.example.expense_tracker_app.viewModel.HomeViewModelFactory

@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryName: String,
    appRepository: AppRepository
) {
    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(appRepository)
    )

    val allExpenses by homeViewModel.expenses.collectAsState()
    val expenses = allExpenses.filter {
        it.category.equals(categoryName, ignoreCase = true)
    }

    AppScaffold(
        navController = navController,
        title = categoryName,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.tertiary)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "$categoryName Expenses",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (expenses.isEmpty()) {
                    Text(
                        text = "No expenses yet for $categoryName",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                } else {
                    var selectedDeleteExpense by remember { mutableStateOf<Expense?>(null) }

                    expenses.forEach { expense ->
                        ExpenseItem(
                            id = expense.id.toString(),
                            title = expense.title,
                            category = expense.category,
                            amount = expense.amount,
                            planned = expense.plannedAmount,
                            description = expense.description,
                            navController = navController,
                            isDeleteMode = selectedDeleteExpense?.id == expense.id,
                            onEdit = { expenseId ->
                                navController.navigate(Screen.Edit.createRoute(expenseId.toInt()))
                            },
                            onDelete = {
                                if (selectedDeleteExpense?.id == expense.id) {
                                    homeViewModel.deleteExpense(selectedDeleteExpense!!)
                                    selectedDeleteExpense = null
                                } else {
                                    selectedDeleteExpense = expense
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(80.dp))
            }

            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.Add.createRoute(categoryName))
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Expense",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
