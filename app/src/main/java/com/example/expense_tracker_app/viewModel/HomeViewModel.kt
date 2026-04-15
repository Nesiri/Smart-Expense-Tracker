package com.example.expense_tracker_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import java.util.Calendar

class HomeViewModel(private val repository: AppRepository) : ViewModel() {

    //  Use stateIn for automatic collection
    val expenses: StateFlow<List<Expense>> = repository.getAllExpenseStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val categories: StateFlow<List<com.example.expense_tracker_app.model.Category>> = repository.getAllCategoryStream()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // GLOBAL SELECTED DATE
    private val calendar = Calendar.getInstance()
    private val _selectedDate = MutableStateFlow(
        "${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
    )
    val selectedDate: StateFlow<String> = _selectedDate.asStateFlow()

    fun setDate(date: String) {
        _selectedDate.value = date
    }

    // FILTERED EXPENSES
    val filteredExpenses = combine(expenses, selectedDate) { expenses, date ->
        expenses.filter { it.date == date }
    }

    // TOTAL AMOUNT
    val totalAmount = combine(expenses, selectedDate) { expenses, date ->
        expenses
            .filter { it.date == date }
            .sumOf { it.amount }
    }

    // TOTAL PLANNED AMOUNT
    val totalPlannedAmount = combine(expenses, selectedDate) { expenses, date ->
        expenses
            .filter { it.date == date }
            .sumOf { it.plannedAmount }
    }
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense)
        }
    }
}