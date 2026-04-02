package com.example.expense_tracker_app.root

import android.content.Context
import com.example.expense_tracker_app.repository.AppRepository
import com.example.expense_tracker_app.repository.OfflineRepository


class AppContainer(context: Context) {

    private val database = ExpenseDatabase.getDatabase(context)

    val appRepository: AppRepository by lazy {
        OfflineRepository(
            database.expenseDao(),
           database.categoryDao()
        )
    }


}