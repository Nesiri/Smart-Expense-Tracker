package com.example.expense_tracker_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ExpenseViewModel(
    private val repository: AppRepository
) : ViewModel() {

    // ✅ Converted to StateFlow
    private val _amount = MutableStateFlow("")
    val amount: StateFlow<String> = _amount.asStateFlow()

    private val _plannedAmount = MutableStateFlow(0.0)
    val plannedAmount: StateFlow<Double> = _plannedAmount.asStateFlow()

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _category = MutableStateFlow("")
    val category: StateFlow<String> = _category.asStateFlow()

    private val _note = MutableStateFlow("")
    val note: StateFlow<String> = _note.asStateFlow()

    // Formatter for M/YYYY (no leading zero)
    private val formatter = SimpleDateFormat("M/yyyy", Locale.getDefault())

    // Default date = current month/year
    private val _date = MutableStateFlow(formatter.format(Date()))
    val date: StateFlow<String> = _date.asStateFlow()

    private val _expense = MutableStateFlow<Expense?>(null)
    val expense: StateFlow<Expense?> = _expense.asStateFlow()

    // Update functions
    fun onAmountChange(value: String) {
        _amount.value = value
    }

    fun onPlannedAmountChange(value: String) {
        _plannedAmount.value = value.toDoubleOrNull() ?: 0.0
    }

    fun onNoteChange(value: String) {
        _note.value = value
    }

    fun onTitleChange(value: String) {
        _title.value = value
    }

    fun onCategoryChange(value: String) {
        _category.value = value
    }

    fun onDateChange(year: Int, month: Int) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, month)
        _date.value = formatter.format(cal.time)
    }

    fun loadPlannedAmount(categoryName: String) {
        viewModelScope.launch {
            try {
                val amount = repository.getPlannedAmount(categoryName)
                _plannedAmount.value = amount ?: 0.0
            } catch (e: Exception) {
                _plannedAmount.value = 0.0
            }
        }
    }

    // Populate all state variables
    fun loadExpense(expenseId: Int) {
        viewModelScope.launch {
            repository.getItemStream(expenseId).collect { expense ->
                expense?.let {
                    _title.value = it.title
                    _amount.value = it.amount.toString()
                    _category.value = it.category
                    _plannedAmount.value = it.plannedAmount
                    _note.value = it.description
                    _date.value = it.date
                    _expense.value = it
                }
            }
        }
    }

    fun saveExpense(
        title: String,
        category: String,
        amount: Double,
        plannedAmount: Double,
        description: String,
        date: String
    ) {
        viewModelScope.launch {
            val expense = Expense(
                id = 0,
                title = title,
                category = category,
                amount = amount,
                plannedAmount = plannedAmount,
                description = description,
                date = date
            )
            repository.insertExpense(expense)
        }
    }

    fun update(
        id: Int,
        title: String,
        category: String,
        amount: String,
        plannedAmount: Double,
        note: String,
        date: String
    ) {
        viewModelScope.launch {
            val expense = Expense(
                id = id,
                title = title,
                category = category,
                amount = amount.toDoubleOrNull() ?: 0.0,
                plannedAmount = plannedAmount,
                description = note,
                date = date
            )
            repository.updateExpense(expense)
        }
    }
}