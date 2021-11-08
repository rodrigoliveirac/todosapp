package com.rodrigoc.todosapp.ui.screens.list

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rodrigoc.todosapp.R
import com.rodrigoc.todosapp.ui.theme.fabBackgroundColor
import com.rodrigoc.todosapp.ui.theme.iconFabColor
import com.rodrigoc.todosapp.ui.viewmodels.SharedViewModel
import com.rodrigoc.todosapp.util.Action
import com.rodrigoc.todosapp.util.SearchAppBarState
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel,
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.getTasks()
    }

    val action by sharedViewModel.action

    val allTasks by sharedViewModel.allTasks.collectAsState() // collectAsState will observe Flow from the composable function.

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState

    val searchTextState: String by sharedViewModel.searchTextState

    val scaffoldState = rememberScaffoldState()

    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseActions(action = action) },
        unDoClicked = { sharedViewModel.action.value = it },
        taskTitle = sharedViewModel.title.value,
        action = action)

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                tasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen,
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit,
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        modifier = Modifier
            .shadow(6.dp, RoundedCornerShape(16.dp), clip = true)
            .clip(RoundedCornerShape(16.dp)),
        shape = RectangleShape,
        backgroundColor = MaterialTheme.colors.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_fab),
            tint = MaterialTheme.colors.iconFabColor
        )
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions: () -> Unit,
    unDoClicked: (Action) -> Unit,
    taskTitle: String,
    action: Action,
) {
    handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if (action != Action.NONE_ACTION) {
            scope.launch {
                val snackBarResult =
                    scaffoldState
                        .snackbarHostState
                        .showSnackbar(
                            message = "${action.name}: $taskTitle",
                            actionLabel = setActionLabel(action)
                        )
                undoDeleteTask(action, snackBarResult, unDoClicked)
            }
        }
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

private fun undoDeleteTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit,
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}