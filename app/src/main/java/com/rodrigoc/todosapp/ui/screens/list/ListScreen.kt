package com.rodrigoc.todosapp.ui.screens.list

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rodrigoc.todosapp.R
import com.rodrigoc.todosapp.data.models.Task
import com.rodrigoc.todosapp.ui.theme.fabBackgroundColor
import com.rodrigoc.todosapp.ui.theme.iconFabColor
import com.rodrigoc.todosapp.ui.viewmodels.SharedViewModel
import com.rodrigoc.todosapp.util.RequestState
import com.rodrigoc.todosapp.util.SearchAppBarState

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel,
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.getTasks()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState() // collectAsState will observe Flow from the composable function.

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState

    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(
        topBar = {
            ListAppBar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        content = {
            ListContent(
                tasks = allTasks as RequestState.Success<List<Task>>,
                navigateToTaskScreen = navigateToTaskScreen
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