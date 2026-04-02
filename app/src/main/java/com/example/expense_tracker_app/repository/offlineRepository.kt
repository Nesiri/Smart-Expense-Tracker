package com.example.expense_tracker_app.repository

import com.example.expense_tracker_app.dao.CategoryDao
import com.example.expense_tracker_app.dao.ExpenseDao

import com.example.expense_tracker_app.model.Expense
import kotlinx.coroutines.flow.Flow

class OfflineRepository(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao
) : AppRepository {
    override fun getAllExpenseStream(): Flow<List<Expense>> =
        expenseDao.getExpenses()

    override fun getItemStream(id: Int): Flow<Expense?> =
        expenseDao.getExpense(id)

    override suspend fun insertExpense(expense: Expense) =
        expenseDao.insert(expense)

    override suspend fun insertExpenses(expenses: List<Expense>) =
        expenseDao.insertExpenses(expenses)



    override suspend fun deleteExpense(expense: Expense) =
        expenseDao.delete(expense)

    override suspend fun updateExpense(expense: Expense) =
        expenseDao.update(expense)

    override fun getAllCategoryStream() =
        categoryDao.getAllCategories()
}