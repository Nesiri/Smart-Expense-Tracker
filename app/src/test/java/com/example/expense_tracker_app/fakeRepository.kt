package com.example.expense_tracker_app

import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.root.AppCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeAppRepository : AppRepository {

    private val fakeExpenses = mutableListOf(
        Expense(1, "Grocery Shopping", "Groceries", 200.0, 180.0, "Weekly groceries", "3/2026"),
        Expense(2, "Electricity Bill", "Bills", 120.0, 100.0, "Monthly bill", "3/2026"),
        Expense(3, "Netflix Subscription", "Entertain", 15.0, 15.0, "Subscription", "3/2026"),
        Expense(4, "Bus Ticket", "Transport", 50.0, 60.0, "Commute", "3/2026"),
        Expense(5, "Doctor Visit", "Health", 80.0, 90.0, "Checkup", "3/2026")
    )

    // Categories
    fun getCategories(): List<Category> {
        return AppCategories.categories
    }

    //  Expense stream
    fun getExpenses(): Flow<List<Expense>> {
        return flowOf(fakeExpenses)
    }

    override fun getAllExpenseStream(): Flow<List<Expense>> {
        return flowOf(fakeExpenses)
    }

    override fun getItemStream(id: Int): Flow<Expense?> {
        return flowOf(fakeExpenses.find { it.id == id })
    }

    override suspend fun insertExpense(expense: Expense) {
        fakeExpenses.add(expense)
    }

    override suspend fun insertExpenses(expenses: List<Expense>) {
        fakeExpenses.addAll(expenses)
    }

    //  Delete (used in tests)
    override suspend fun deleteExpense(expense: Expense) {
        fakeExpenses.removeIf { it.id == expense.id }
    }

    override suspend fun updateExpense(expense: Expense) {
        val index = fakeExpenses.indexOfFirst { it.id == expense.id }
        if (index != -1) {
            fakeExpenses[index] = expense
        }
    }

    override fun getAllCategoryStream(): Flow<List<Category>> {
        return flowOf(AppCategories.categories)
    }

    override suspend fun getPlannedAmount(categoryName: String): Double? {
        return fakeExpenses
            .filter { it.category.equals(categoryName, ignoreCase = true) }
            .sumOf { it.plannedAmount }
    }

    override fun getExpensesByCategory(categoryName: String): Flow<List<Expense>> {
        return flowOf(fakeExpenses.filter { it.category.equals(categoryName, ignoreCase = true) })
    }

    //  Test helper only
    fun addExpense(expense: Expense) {
        fakeExpenses.add(expense)
    }
}