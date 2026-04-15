package com.example.expense_tracker_app.repository

import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    // Expense operations
    fun getAllExpenseStream(): Flow<List<Expense>>
    fun getItemStream(id: Int): Flow<Expense?>
    suspend fun insertExpense(expense: Expense)
    suspend fun insertExpenses(expenses: List<Expense>)
    suspend fun deleteExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    // Category operations
    fun getAllCategoryStream(): Flow<List<Category>>
    suspend fun getPlannedAmount(categoryName: String): Double?  // ← suspend

}