package com.rodrigoc.todosapp.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoc.todosapp.R
import com.rodrigoc.todosapp.components.PriorityItem
import com.rodrigoc.todosapp.data.models.Priority
import com.rodrigoc.todosapp.ui.theme.*

@Composable
fun ListAppBar() {
    DefaultAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteAllClicked = {}
    )
}

@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = "Tasks", color = MaterialTheme.colors.topAppBarContentColor)
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteAllClicked = onDeleteAllClicked)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllClicked: () -> Unit,
) {
    SearchAction(onSearchClicked)
    SortAction(onSortClicked)
    DeleteAllAction(onDeleteAllClicked)
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit,
) {
    IconButton(
        onClick = { onSearchClicked() }
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.search_tasks),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        }
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_filter),
            contentDescription = stringResource(
                R.string.sort_action),
            tint = MaterialTheme.colors.AppBarIcons)
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onSortClicked(Priority.LOW)
            }
        ) {
            PriorityItem(priority = Priority.LOW)
        }

        DropdownMenuItem(
            onClick = {
                expanded = false
                onSortClicked(Priority.HIGH)
            }
        ) {
            PriorityItem(priority = Priority.HIGH)
        }

        DropdownMenuItem(
            onClick = {
                expanded = false
                onSortClicked(Priority.NONE)
            }
        ) {
            PriorityItem(priority = Priority.NONE)
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteAllClicked: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            expanded = true
        }
    ) {

        Icon(
            painter = painterResource(id = R.drawable.icon_vertical_menu),
            contentDescription = stringResource(
                R.string.sort_action),
            tint = MaterialTheme.colors.AppBarIcons)
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onDeleteAllClicked()
            }) {
                 Text(
                     modifier = Modifier
                         .padding(start = LARGE_PADDING),
                     text = stringResource(R.string.delete_all),
                     style = Typography.subtitle1
                 )
        }
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteAllClicked = {}
    )
}