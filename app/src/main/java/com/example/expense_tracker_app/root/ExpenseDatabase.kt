package com.example.expense_tracker_app.root

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.expense_tracker_app.dao.CategoryDao
import com.example.expense_tracker_app.dao.ExpenseDao
import com.example.expense_tracker_app.model.Category
import com.example.expense_tracker_app.model.Expense
import com.example.expense_tracker_app.root.AppCategories.categories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                ) .addCallback(object : Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            val dao = getDatabase(context).categoryDao()
                            val count = dao.getCount() // you need a DAO query to count rows
                            if (count == 0) {
                                dao.insertCategories(categories)
                            }
                        }
                    }
                })
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}