package com.rodrigoc.todosapp.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.rodrigoc.todosapp.data.models.Priority
import com.rodrigoc.todosapp.data.models.Task
import com.rodrigoc.todosapp.ui.viewmodels.SharedViewModel
import com.rodrigoc.todosapp.util.Action

@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    selectedTask: Task?,
    navigateToListScreen: (Action) -> Unit,
) {

    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    Scaffold(
        topBar = {
            TaskAppBar(
                selectedTask = selectedTask,
                navigateToListScreen = navigateToListScreen,
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChange = { newTitle ->
                    sharedViewModel.updateTitle(newTitle)
                },
                description = description,
                onDescriptionChange = { newDescription ->
                    sharedViewModel.description.value = newDescription
                },
                priority = priority,
                onPrioritySelected = { newPriority ->
                    sharedViewModel.priority.value = newPriority
                },
            )
        }
    )
}