package com.example.expense_tracker_app

import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.viewModel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    val testDispatcher: kotlinx.coroutines.test.TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    // 1. Categories should not be empty when loaded
    @Test
    fun categoriesAreLoaded() = runTest {
        val viewModel = HomeViewModel(FakeAppRepository())
        // Filter out the initial empty list and wait for the actual data
        val result = viewModel.categories.filter { it.isNotEmpty() }.first()
        assertTrue(result.isNotEmpty())
    }

    // 2. Expenses list starts valid
    @Test
    fun expensesInitialState() = runTest {
        val viewModel = HomeViewModel(FakeAppRepository())
        // Wait for data if necessary
        val result = viewModel.expenses.filter { it.isNotEmpty() }.first()
        assertNotNull(result)
        assertTrue(result.isNotEmpty())
    }

    // 3. Set date updates state
    @Test
    fun setDateUpdatesSelectedDate() {
        val viewModel = HomeViewModel(FakeAppRepository())
        viewModel.setDate("3/2026")
        assertEquals("3/2026", viewModel.selectedDate.value)
    }

    // 4. Total amount not negative
    @Test
    fun totalAmountIsNotNegative() = runTest {
        val viewModel = HomeViewModel(FakeAppRepository())
        // Use a default date that has data in FakeRepository
        viewModel.setDate("3/2026")
        val total = viewModel.totalAmount.filter { it > 0 }.first()
        assertTrue(total >= 0.0)
    }

    // 5. Planned amount not negative
    @Test
    fun plannedAmountIsNotNegative() = runTest {
        val viewModel = HomeViewModel(FakeAppRepository())
        viewModel.setDate("3/2026")
        val planned = viewModel.totalPlannedAmount.filter { it > 0 }.first()
        assertTrue(planned >= 0.0)
    }

    // 6. Delete expense removes item
    @Test
    fun deleteExpenseRemovesItem() = runTest {
        val viewModel = HomeViewModel(FakeAppRepository())
        viewModel.setDate("3/2026")

        val expense = Expense(
            1,
            "Grocery Shopping",
            "Groceries",
            200.0,
            180.0,
            "Weekly groceries",
            "3/2026"
        )

        viewModel.deleteExpense(expense)

        // Wait for the change to reflect in the filtered list
        val result = viewModel.filteredExpenses.filter { list -> list.none { it.id == 1 } }.first()
        assertFalse(result.any { it.id == 1 })
    }

    // 7. Expense model valid
    @Test
    fun expenseHasValidData() {
        val expense = Expense(
            1,
            "Food",
            "Groceries",
            10.0,
            20.0,
            "note",
            "3/2026"
        )

        assertEquals("Food", expense.title)
        assertEquals(10.0, expense.amount, 0.0)
    }

    @Test
    fun categoryTitleIsCorrect() {
        val category = Category(
            title = "Groceries",
            icon = com.example.expense_tracker_app.R.drawable.shopping
        )

        assertEquals("Groceries", category.title)
    }

    // 9. Selected date not null
    @Test
    fun selectedDateDefaultNotNull() {
        val viewModel = HomeViewModel(FakeAppRepository())
        assertNotNull(viewModel.selectedDate.value)
    }

    // 10. Repository returns categories
    @Test
    fun repositoryReturnsData() {
        val repo = FakeAppRepository()
        val data = repo.getCategories()
        assertTrue(data.isNotEmpty())
    }
}