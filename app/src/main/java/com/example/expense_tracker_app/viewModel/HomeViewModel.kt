package com.example.expense_tracker_app.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {

    private val _expenses = MutableStateFlow(com.example.expense_tracker_app.data.expenses)
    val expenses = _expenses.asStateFlow()

    private val _categories = MutableStateFlow(com.example.expense_tracker_app.data.categories)
    val categories = _categories.asStateFlow()
}