package com.rodrigoc.todosapp.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.rodrigoc.todosapp.R
import com.rodrigoc.todosapp.data.models.Task
import com.rodrigoc.todosapp.ui.theme.onTaskItem
import com.rodrigoc.todosapp.ui.theme.taskItem
import com.rodrigoc.todosapp.ui.theme.topAppBarBackgroundColor
import com.rodrigoc.todosapp.ui.theme.topAppBarContentColor
import com.rodrigoc.todosapp.util.Action

@Composable
fun TaskAppBar(
    navigateToListScreen: (Action) -> Unit,
) {
    NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    // ExistingTaskAppBar(selectedTask = , navigateToTaskScreen = )
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Action) -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = "Add Task",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingTaskAppBar(
    selectedTask: Task,
    navigateToTaskScreen: (Action) -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            CloseAction(onCloseClicked = navigateToTaskScreen)
        },
        title = {
            Text(
                text = selectedTask.title,
                color = MaterialTheme.colors.onTaskItem,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        backgroundColor = MaterialTheme.colors.taskItem,
        actions = {
            DeleteAction(onDeleteClicked = navigateToTaskScreen)
            UpdateAction(onUpdateClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun CloseAction(
    onCloseClicked: (Action) -> Unit,
) {
    IconButton(onClick = { onCloseClicked(Action.NONE_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.close_icon),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun BackAction(
    onBackClicked: (Action) -> Unit,
) {
    IconButton(onClick = { onBackClicked(Action.NONE_ACTION) }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_action),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action) -> Unit,
) {
    IconButton(onClick = {
        onAddClicked(Action.ADD)
    }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Add Task",
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action) -> Unit,
) {
    IconButton(onClick = { onDeleteClicked(Action.DELETE) }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_icon),
            tint = MaterialTheme.colors.topAppBarContentColor)
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action) -> Unit,
) {
    IconButton(onClick = { onUpdateClicked(Action.UPDATE) }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.update_icon),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}