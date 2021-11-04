package com.rodrigoc.todosapp.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoc.todosapp.data.TaskDao
import com.rodrigoc.todosapp.data.models.Task
import com.rodrigoc.todosapp.data.repositories.TaskRepository
import com.rodrigoc.todosapp.util.RequestState
import com.rodrigoc.todosapp.util.SearchAppBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SharedViewModel
@Inject constructor(
    private val repository: TaskRepository,
) : ViewModel() {

    private val _searchAppBarState: MutableState<SearchAppBarState> =
        mutableStateOf(SearchAppBarState.CLOSED)
    val searchAppBarState: MutableState<SearchAppBarState> = _searchAppBarState

    private val _searchTextState: MutableState<String> =
        mutableStateOf("")
    val searchTextState: MutableState<String> = _searchTextState

    private val _allTasks = MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val allTasks: StateFlow<RequestState<List<Task>>> = _allTasks

    fun getTasks() {
        _allTasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                repository.getTasks.collect { tasks ->
                    _allTasks.value = RequestState.Success(tasks)
                }
            }
        } catch (e: Exception) {
            _allTasks.value = RequestState.Error(e)
        }
    }
}