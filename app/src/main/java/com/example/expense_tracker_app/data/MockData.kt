package com.example.expense_tracker_app.data

import com.example.expense_tracker_app.R
import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense

val categories = listOf(
    Category("Total", R.drawable.total),
    Category("Groceries", R.drawable.shopping),
    Category("Utilities", R.drawable.utilities),
    Category("Entertain", R.drawable.stage),
    Category("Transport", R.drawable.airplane),
    Category("Health", R.drawable.health),
    Category("Bills", R.drawable.bill),
    Category("Other", R.drawable.other)
)

val expenses = listOf(
    Expense(
        "Grocery Shopping",
        "GROCERIES",
        200.0,
        "Bought groceries for the week including fresh produce."
    ),
    Expense(
        "Electricity Bill",
        "BILLS",
        120.0,
        "Paid monthly electricity bill for the house."
    ),
    Expense(
        "Netflix Subscription",
        "ENTERTAIN",
        15.0,
        "Monthly entertainment subscription."
    ),
    Expense(
        "Bus Ticket",
        "TRANSPORT",
        50.0,
        "Weekly transport card recharge for commuting."
    ),
    Expense(
        "Doctor Visit",
        "HEALTH",
        80.0,
        "General health check-up and consultation."
    ),
    Expense(
        "Internet Bill",
        "UTILITIES",
        60.0,
        "Monthly home internet service payment."
    ),
    Expense(
        "Misc Purchase",
        "OTHER",
        40.0,
        "Random small personal expenses."
    ) ,
    Expense(
            "",
    "",
    0.0,
    "."
)
)