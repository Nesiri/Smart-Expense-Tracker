package com.example.expense_tracker_app.ui_test

import androidx.compose.ui.test.hasClickAction
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expense_tracker_app.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CategoryScreenInstrumentedTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @Test
    fun categoryScreenOpens() {
        rule.onNodeWithText("Categories").assertExists()
    }

    @Test
    fun clickingCategoryOpensAddScreen() {
        rule.onNodeWithText("Groceries").performClick()
        rule.onNodeWithText("Add Expense").assertExists()
    }

}