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
        180.0,
        "Bought groceries for the week including fresh produce.",
        "3/2026"
    ),
    Expense(
        "Electricity Bill",
        "BILLS",
        120.0,
        100.0,
        "Paid monthly electricity bill for the house.",
        "3/2026"
    ),
    Expense(
        "Water Bill",
        "UTILITIES",
        45.0,
        50.0,
        "Monthly water service bill.",
        "3/2026"
    ),
    Expense(
        "Internet Bill",
        "UTILITIES",
        60.0,
        60.0,
        "Monthly home internet service payment.",
        "3/2026"
    ),
    Expense(
        "Netflix Subscription",
        "ENTERTAIN",
        15.0,
        15.0,
        "Monthly entertainment subscription.",
        "3/2026"
    ),
    Expense(
        "Movie Night",
        "ENTERTAIN",
        35.0,
        30.0,
        "Cinema tickets and snacks.",
        "3/2026"
    ),
    Expense(
        "Bus Ticket",
        "TRANSPORT",
        50.0,
        60.0,
        "Weekly transport card recharge for commuting.",
        "3/2026"
    ),
    Expense(
        "Fuel",
        "TRANSPORT",
        110.0,
        100.0,
        "Car fuel refill for the month.",
        "3/2026"
    ),
    Expense(
        "Doctor Visit",
        "HEALTH",
        80.0,
        90.0,
        "General health check-up and consultation.",
        "3/2026"
    ),
    Expense(
        "Pharmacy",
        "HEALTH",
        35.0,
        40.0,
        "Medicines and supplements.",
        "3/2026"
    ),
    Expense(
        "Misc Purchase",
        "OTHER",
        40.0,
        50.0,
        "Random small personal expenses.",
        "3/2026"
    ),
    Expense(
        "Clothing",
        "OTHER",
        70.0,
        100.0,
        "Bought new clothes.",
        "3/2026"
    ),

    // ====== FEB 2026 ======

    Expense(
        "Netflix Subscription",
        "ENTERTAIN",
        15.0,
        15.0,
        "Monthly entertainment subscription.",
        "2/2026"
    ),
    Expense(
        "Misc Purchase",
        "OTHER",
        40.0,
        50.0,
        "Random small personal expenses.",
        "2/2026"
    ),

    // ====== JAN 2026 ======

    Expense(
        "Bus Ticket",
        "TRANSPORT",
        50.0,
        60.0,
        "Weekly transport card recharge for commuting.",
        "1/2026"
    ),
    Expense(
        "Doctor Visit",
        "HEALTH",
        80.0,
        100.0,
        "General health check-up and consultation.",
        "1/2026"
    ),
    Expense(
        "Internet Bill",
        "UTILITIES",
        60.0,
        60.0,
        "Monthly home internet service payment.",
        "1/2026"
    )
)