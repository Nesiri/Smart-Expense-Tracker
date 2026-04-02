package com.example.expense_tracker_app.root

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.expense_tracker_app.dao.CategoryDao
import com.example.expense_tracker_app.dao.ExpenseDao
import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense

@Database(
    entities = [Expense::class, Category::class],
    version = 2,
    exportSchema = false
)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}