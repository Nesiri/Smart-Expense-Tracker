package com.example.expense_tracker_app.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import java.time.LocalDate
import java.util.Calendar

class HomeViewModel : ViewModel() {

    private val _expenses = MutableStateFlow(com.example.expense_tracker_app.data.expenses)
    val expenses = _expenses.asStateFlow()

    private val _categories = MutableStateFlow(com.example.expense_tracker_app.data.categories)
    val categories = _categories.asStateFlow()
    //  GLOBAL SELECTED DATE (STRING FORMAT = SAFE FOR API 24)
    private val calendar = Calendar.getInstance()

    private val _selectedDate = MutableStateFlow(
                "${calendar.get(Calendar.MONTH) + 1}/" +
                "${calendar.get(Calendar.YEAR)}"
    )

    val selectedDate = _selectedDate.asStateFlow()

    fun setDate(date: String) {
        _selectedDate.value = date
    }

    // FILTERED EXPENSES
    val filteredExpenses = combine(_expenses, _selectedDate) { expenses, date ->
        expenses.filter { it.date == date }
    }

    //  TOTAL AMOUNT
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

}