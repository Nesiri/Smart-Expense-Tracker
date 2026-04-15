package com.example.expense_tracker_app.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName ="categories" , indices = [Index(value = ["title"], unique = true)] )
data class Category(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    val title: String,
    @param:DrawableRes val icon: Int
)
