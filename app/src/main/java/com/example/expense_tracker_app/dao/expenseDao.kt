package com.example.expense_tracker_app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.expense_tracker_app.model.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: List<Expense>)

    @Update
    suspend fun update(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM expenses WHERE id = :id")
    fun getExpense(id: Int): Flow<Expense?>

    @Query("""
    SELECT plannedAmount 
    FROM expenses 
   WHERE UPPER(category) = UPPER(:categoryName) 
    ORDER BY date DESC 
    LIMIT 1
""")
    suspend fun getPlannedAmount(categoryName: String): Double?  // ← suspend, not Flow

    @Query("SELECT * FROM expenses ORDER BY amount Desc")
    fun getExpenses(): Flow<List<Expense>>
}
