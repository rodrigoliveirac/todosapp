package com.rodrigoc.todosapp.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.rodrigoc.todosapp.data.models.Task
import com.rodrigoc.todosapp.ui.viewmodels.SharedViewModel
import com.rodrigoc.todosapp.util.Action

@Composable
fun TaskScreen(
    selectedTask: Task?,
    navigateToListScreen: (Action) -> Unit,
) {

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen,
                )
        },
        content = { }
    )
}