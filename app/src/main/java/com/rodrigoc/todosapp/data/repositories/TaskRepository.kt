package com.rodrigoc.todosapp.data.repositories

import com.rodrigoc.todosapp.data.TaskDao
import com.rodrigoc.todosapp.data.models.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject
constructor(private val taskDao: TaskDao) {

    val getTasks: Flow<List<Task>> = taskDao.getTasks()
    val sortByLowPriority: Flow<List<Task>> = taskDao.searchByLowPriority()
    val sortByHighPriority: Flow<List<Task>> = taskDao.searchByHighPriority()

    fun getTask(taskId: Int) : Flow<Task> {
        return taskDao.getById(taskId = taskId)
    }

    suspend fun insertTask(task: Task) {
        taskDao.insertTask(task = task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task = task)
    }

    suspend fun removeTask(task: Task) {
        taskDao.removeTask(task = task)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String) : Flow<List<Task>> {
        return taskDao.searchDb(searchQuery = searchQuery)
    }
}