package com.example.expense_tracker_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Calendar

class HomeViewModel(
    private val expenseViewModel: ExpenseViewModel
) : ViewModel() {

    // Get expenses from ExpenseViewModel
    private val _expenses = MutableStateFlow(emptyList<com.example.expense_tracker_app.model.Expense>())
    val expenses: StateFlow<List<com.example.expense_tracker_app.model.Expense>> = _expenses.asStateFlow()

    // Get categories from ExpenseViewModel
    private val _categories = MutableStateFlow(emptyList<com.example.expense_tracker_app.model.Category>())
    val categories: StateFlow<List<com.example.expense_tracker_app.model.Category>> = _categories.asStateFlow()

    // GLOBAL SELECTED DATE (STRING FORMAT = SAFE FOR API 24)
    private val calendar = Calendar.getInstance()
    private val _selectedDate = MutableStateFlow(
        "${calendar.get(Calendar.MONTH) + 1}/" +
                "${calendar.get(Calendar.YEAR)}"
    )
    val selectedDate = _selectedDate.asStateFlow()

    init {
        // Collect expenses from ExpenseViewModel
        expenseViewModel.allExpenses
            .onEach { expenseList ->
                _expenses.value = expenseList
            }
            .launchIn(viewModelScope)

        // Collect categories from ExpenseViewModel
        expenseViewModel.allCategories
            .onEach { categoryList ->
                _categories.value = categoryList
            }
            .launchIn(viewModelScope)
    }

    fun setDate(date: String) {
        _selectedDate.value = date
    }

    // FILTERED EXPENSES
    val filteredExpenses = combine(_expenses, _selectedDate) { expenses, date ->
        expenses.filter { it.date == date }
    }

    // TOTAL AMOUNT
    val totalAmount = combine(_expenses, _selectedDate) { expenses, date ->
        expenses
            .filter { it.date == date }
            .sumOf { it.amount }
    }

    val totalPlannedAmount = combine(_expenses, _selectedDate) { expenses, date ->
        expenses
            .filter { it.date == date }
            .sumOf { it.plannedAmount }
    }

    // Delegate delete operation to ExpenseViewModel
    fun deleteExpense(expense: com.example.expense_tracker_app.model.Expense) {
        expenseViewModel.deleteExpense(expense)
    }

    // Delegate update operation to ExpenseViewModel
    fun updateExpense(expense: com.example.expense_tracker_app.model.Expense) {
        expenseViewModel.updateExpense(expense)
    }
}