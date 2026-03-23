package com.example.expense_tracker_app.model

import android.accessibilityservice.GestureDescription

data class Expense(
    val title:String,
    val category: String,
    val amount :Double,
    val plannedAmount:Double,
    val description: String,
    val date: String
)
