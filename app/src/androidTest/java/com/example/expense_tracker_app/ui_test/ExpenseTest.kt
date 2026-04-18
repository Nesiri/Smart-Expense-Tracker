package com.example.expense_tracker_app.ui_test

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expense_tracker_app.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExpenseEditInstrumentedTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalTestApi::class)
    private fun waitForNode(matcher: SemanticsMatcher, timeout: Long = 10000) {
        rule.waitUntilAtLeastOneExists(matcher, timeout)
    }

    // Helper to add an expense for editing tests
    private fun createElectricityBillExpense() {
        waitForNode(hasTestTag("categories_row"), 10000L)
        
        rule.onNodeWithTag("categories_row")
            .performScrollToNode(hasTestTag("category_Bills"))
        
        rule.onNodeWithTag("category_Bills").performClick()

        waitForNode(hasTestTag("scaffold_title") and hasText("Add Expense"))

        rule.onNodeWithTag("title_input").performTextInput("Electricity Bill")
        rule.onNodeWithTag("amount_input").performTextInput("120")
        rule.onNodeWithTag("note_input").performTextInput("Monthly bill")
        
        rule.onNodeWithTag("add_expense_button").performScrollTo().performClick()
        
        waitForNode(hasTestTag("expense_item_Electricity Bill"))
    }

    // ADD SCREEN TESTS

    @Test
    fun screenLoadsAddExpense() {
        waitForNode(hasTestTag("categories_row"))
        rule.onNodeWithTag("categories_row")
            .performScrollToNode(hasTestTag("category_Groceries"))
            
        rule.onNodeWithTag("category_Groceries").performClick()
        
        waitForNode(hasTestTag("scaffold_title") and hasText("Add Expense"))
        rule.onNodeWithTag("scaffold_title").assertIsDisplayed()
    }

    @Test
    fun allInputFieldsExistInAddScreen() {
        waitForNode(hasTestTag("categories_row"))
        rule.onNodeWithTag("categories_row")
            .performScrollToNode(hasTestTag("category_Groceries"))
            
        rule.onNodeWithTag("category_Groceries").performClick()

        waitForNode(hasTestTag("scaffold_title") and hasText("Add Expense"))

        rule.onNodeWithTag("title_input").assertExists()
        rule.onNodeWithTag("amount_input").assertExists()
        rule.onNodeWithTag("note_input").performScrollTo().assertIsDisplayed()
    }

    @Test
    fun addExpenseButtonWorks() {
        waitForNode(hasTestTag("categories_row"))
        rule.onNodeWithTag("categories_row")
            .performScrollToNode(hasTestTag("category_Groceries"))
            
        rule.onNodeWithTag("category_Groceries").performClick()
        
        waitForNode(hasTestTag("add_expense_button"))
        rule.onNodeWithTag("add_expense_button").performScrollTo().performClick()
    }

    // EDIT SCREEN TESTS

    @Test
    fun screenLoadsEditExpense() {
        createElectricityBillExpense()
        
        // Use onFirst() to handle cases where multiple items exist in the list
        rule.onAllNodesWithTag("expense_item_Electricity Bill").onFirst().performTouchInput {
            doubleClick()
        }
        
        waitForNode(hasTestTag("scaffold_title") and hasText("Edit Expense"))
        rule.onNodeWithTag("scaffold_title").assertIsDisplayed()
    }

    @Test
    fun updateExpenseButtonWorks() {
        createElectricityBillExpense()
        
        rule.onAllNodesWithTag("expense_item_Electricity Bill").onFirst().performTouchInput {
            doubleClick()
        }
        
        waitForNode(hasTestTag("update_expense_button"))
        rule.onNodeWithTag("update_expense_button").performScrollTo().performClick()
    }
}
