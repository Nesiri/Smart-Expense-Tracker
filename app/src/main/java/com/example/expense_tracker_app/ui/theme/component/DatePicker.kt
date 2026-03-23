package com.example.expense_tracker_app.ui.theme.component

import android.app.DatePickerDialog
import android.content.Context
import java.util.Calendar

fun showDatePicker(
    context: Context,
    onDateSelected: (String) -> Unit
) {
    val calendar = Calendar.getInstance()

    DatePickerDialog(
        context,
        { _, year, month, _ ->

            // FORMAT: MM/yyyy
            val formattedDate = "${month + 1}/$year"
            onDateSelected(formattedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}