package com.example.expense_tracker_app.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="categories" )
data class Category(
    @PrimaryKey
    val id: String,
    val title: String,
    @param:DrawableRes val icon: Int
)
