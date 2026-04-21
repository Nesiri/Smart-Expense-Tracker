package com.example.expense_tracker_app.ui_test

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.expense_tracker_app.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenInstrumentedTest {

    @get:Rule
    val rule = createAndroidComposeRule<MainActivity>()

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun homeScreenLoads() {
        // Wait for Categories to appear (handles database loading time)
        rule.waitUntilAtLeastOneExists(hasText("Categories"), 5000L)
        rule.onNodeWithText("Categories").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun monthlyOverviewHeaderExists() {
        // Wait specifically for the conditional content
        // Note: This assumes categories are pre-populated in your database
        rule.waitUntilAtLeastOneExists(hasText("Monthly Overview"), 5000L)
        rule.onNodeWithText("Monthly Overview").assertIsDisplayed()
    }

    @OptIn(ExperimentalTestApi::class)
    @Test
    fun expenseSummaryTextVisible() {
        // Wait for the summary card
        rule.waitUntilAtLeastOneExists(hasText("Monthly expense summary"), 5000L)
        rule.onNodeWithText("Monthly expense summary").assertIsDisplayed()
    }
}