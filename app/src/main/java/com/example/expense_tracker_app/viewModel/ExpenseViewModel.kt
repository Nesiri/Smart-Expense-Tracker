package com.example.expense_tracker_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.repository.AppRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExpenseViewModel(
    private val repository: AppRepository  // Just one repository now
) : ViewModel() {

    // Add a new expense
    fun addExpense(
        title: String,
        category: String,
        amount: Double,
        plannedAmount: Double,
        description: String,
        date: String
    ) {
        val expense = Expense(
            id = 0, // Room will auto-generate
            title = title,
            category = category,
            amount = amount,
            plannedAmount = plannedAmount,
            description = description,
            date = date
        )

        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }

    // Delete an existing expense
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }

    // Update an existing expense
    fun updateExpense(expense: Expense) {
        viewModelScope.launch {
            repository.updateExpense(expense)
        }
    }

    // Stream all expenses and categories from single repository
    val allExpenses: Flow<List<Expense>> = repository.getAllExpenseStream()
    val allCategories: Flow<List<Category>> = repository.getAllCategoryStream()
}