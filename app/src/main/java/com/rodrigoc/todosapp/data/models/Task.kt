package com.rodrigoc.todosapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rodrigoc.todosapp.util.Constants.DATABASE_TABLE

@Entity(tableName = DATABASE_TABLE)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val priority: Priority
)


