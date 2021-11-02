package com.rodrigoc.todosapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rodrigoc.todosapp.data.models.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TodoTaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao
}