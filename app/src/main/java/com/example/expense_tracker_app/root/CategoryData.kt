package com.example.expense_tracker_app.root

import com.example.expense_tracker_app.R
import com.example.expense_tracker_app.model.Category

object AppCategories {

    val categories: List<Category> = listOf(
        Category(title = "Total", icon = R.drawable.total),
        Category( title = "Groceries", icon = R.drawable.shopping),
        Category( title = "Utilities", icon = R.drawable.utilities),
        Category( title = "Entertain", icon = R.drawable.stage),
        Category( title = "Transport", icon = R.drawable.airplane),
        Category( title = "Health", icon = R.drawable.health),
        Category( title = "Bills", icon = R.drawable.bill),
        Category( title = "Other", icon = R.drawable.other)
    )
}
// mock data for expenses
/*
INSERT INTO expenses (title, category, amount, plannedAmount, description, date) VALUES
('Grocery Shopping', 'GROCERIES', 200.0, 180.0, 'Bought groceries for the week including fresh produce.', '3/2026'),
('Electricity Bill', 'BILLS', 120.0, 100.0, 'Paid monthly electricity bill for the house.', '3/2026'),
('Water Bill', 'UTILITIES', 45.0, 50.0, 'Monthly water service bill.', '3/2026'),
('Internet Bill', 'UTILITIES', 60.0, 60.0, 'Monthly home internet service payment.', '3/2026'),
('Netflix Subscription', 'ENTERTAIN', 15.0, 15.0, 'Monthly entertainment subscription.', '3/2026'),
('Movie Night', 'ENTERTAIN', 35.0, 30.0, 'Cinema tickets and snacks.', '3/2026'),
('Bus Ticket', 'TRANSPORT', 50.0, 60.0, 'Weekly transport card recharge for commuting.', '3/2026'),
('Fuel', 'TRANSPORT', 110.0, 100.0, 'Car fuel refill for the month.', '3/2026'),
('Doctor Visit', 'HEALTH', 80.0, 90.0, 'General health check-up and consultation.', '3/2026'),
('Pharmacy', 'HEALTH', 35.0, 40.0, 'Medicines and supplements.', '3/2026'),
('Misc Purchase', 'OTHER', 40.0, 50.0, 'Random small personal expenses.', '3/2026'),
('Clothing', 'OTHER', 70.0, 100.0, 'Bought new clothes.', '3/2026'),
('Netflix Subscription', 'ENTERTAIN', 15.0, 15.0, 'Monthly entertainment subscription.', '2/2026'),
('Misc Purchase', 'OTHER', 40.0, 50.0, 'Random small personal expenses.', '2/2026'),
('Bus Ticket', 'TRANSPORT', 50.0, 60.0, 'Weekly transport card recharge for commuting.', '1/2026'),
('Doctor Visit', 'HEALTH', 80.0, 100.0, 'General health check-up and consultation.', '1/2026'),
('Internet Bill', 'UTILITIES', 60.0, 60.0, 'Monthly home internet service payment.', '1/2026');*/