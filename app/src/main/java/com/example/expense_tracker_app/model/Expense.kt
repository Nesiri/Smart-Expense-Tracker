package com.example.expense_tracker_app.model

import android.accessibilityservice.GestureDescription
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="expenses" )
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val category: String,
    val amount :Double,
    val plannedAmount:Double,
    val description: String,
    val date: String
)
