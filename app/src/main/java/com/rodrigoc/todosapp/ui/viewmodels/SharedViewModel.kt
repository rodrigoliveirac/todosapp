package com.rodrigoc.todosapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoc.todosapp.data.TaskDao
import com.rodrigoc.todosapp.data.models.Task
import com.rodrigoc.todosapp.data.repositories.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {

    private val _allTasks = MutableStateFlow<List<Task>>(emptyList())
    val allTasks: StateFlow<List<Task>> = _allTasks

    fun getTasks() {
        viewModelScope.launch {
            repository.getTasks.collect { tasks ->
                _allTasks.value = tasks
            }
        }
    }
}