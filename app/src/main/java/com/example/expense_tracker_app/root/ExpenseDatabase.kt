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
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Seed database on creation
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                database.categoryDao().insertCategories(categories)
                            }
                        }
                    }
                    
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        // Ensure categories exist every time (optional, but safer for tests)
                        INSTANCE?.let { database ->
                            CoroutineScope(Dispatchers.IO).launch {
                                val dao = database.categoryDao()
                                if (dao.getCount() == 0) {
                                    dao.insertCategories(categories)
                                }
                            }
                        }
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

                INSTANCE = instance
                instance
            }
        }
    }
}