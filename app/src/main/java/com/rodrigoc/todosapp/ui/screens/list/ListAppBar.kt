package com.rodrigoc.todosapp.ui.screens.list

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoc.todosapp.R
import com.rodrigoc.todosapp.components.DisplayAlertDialog
import com.rodrigoc.todosapp.components.PriorityItem
import com.rodrigoc.todosapp.data.models.Priority
import com.rodrigoc.todosapp.ui.theme.*
import com.rodrigoc.todosapp.ui.viewmodels.SharedViewModel
import com.rodrigoc.todosapp.util.Action
import com.rodrigoc.todosapp.util.SearchAppBarState
import com.rodrigoc.todosapp.util.TrailingIconState

@Composable
fun ListAppBar(
    sharedViewModel: SharedViewModel,
    searchAppBarState: SearchAppBarState,
    searchTextState: String,
) {
    when (searchAppBarState) {
        SearchAppBarState.CLOSED -> {
            DefaultAppBar(
                onSearchClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.OPENED
                },
                onSortClicked = {},
                onDeleteAllConfirmed = {
                    sharedViewModel.action.value = Action.DELETE_ALL
                }
            )
        }
        else -> {
            SearchAppBar(
                text = searchTextState,
                textFieldState = { newText ->
                    sharedViewModel.searchTextState.value = newText
                },
                onCloseClicked = {
                    sharedViewModel.searchAppBarState.value = SearchAppBarState.CLOSED
                    sharedViewModel.searchTextState.value = ""
                },
                onSearchClicked = {
                    sharedViewModel.searchDatabase(searchQuery = it)
                },
            )
        }
    }

}

@Composable
fun DefaultAppBar(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.list_screen_title), color = MaterialTheme.colors.topAppBarContentColor)
        },
        actions = {
            ListAppBarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteAllConfirmed = onDeleteAllConfirmed)
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
fun ListAppBarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteAllConfirmed: () -> Unit,
) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_all_tasks),
        message = stringResource(id = R.string.delete_all_tasks_confirmation),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { onDeleteAllConfirmed() }
    )
    SearchAction(onSearchClicked)
    SortAction(onSortClicked)
    DeleteAllAction(onDeleteAllConfirmed = { openDialog = true })
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
            tint = MaterialTheme.colors.appBarIcons)
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
    onDeleteAllConfirmed: () -> Unit,
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
            tint = MaterialTheme.colors.appBarIcons)
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                expanded = false
                onDeleteAllConfirmed()
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
fun SearchAppBar(
    text: String,
    textFieldState: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
) {

    var trailingIconState by remember { mutableStateOf(TrailingIconState.READY_TO_DELETE) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(TOP_APP_BAR_HEIGHT),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = MaterialTheme.colors.topAppBarBackgroundColor
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = text,
            onValueChange = { text ->
                textFieldState(text)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = stringResource(R.string.placeholder_search),
                    color = MaterialTheme.colors.appBarIcons
                )
            },
            textStyle = TextStyle(
                color = MaterialTheme.colors.topAppBarContentColor,
                fontSize = MaterialTheme.typography.subtitle1.fontSize
            ),
            singleLine = true,
            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(ContentAlpha.disabled),
                    onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = stringResource(R.string.icon_search),
                        tint = MaterialTheme.colors.appBarIcons
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        when (trailingIconState) {
                            TrailingIconState.READY_TO_DELETE -> {
                                textFieldState("")
                                trailingIconState = TrailingIconState.READY_TO_CLOSE
                            }
                            else -> {
                                if (text.isNotEmpty()) {
                                    textFieldState("")
                                } else {
                                    onCloseClicked()
                                    TrailingIconState.READY_TO_DELETE
                                }
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = stringResource(R.string.close_icon),
                        tint = MaterialTheme.colors.appBarIcons
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearchClicked(text)
                }
            ),
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = MaterialTheme.colors.topAppBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        )
    }
}

@Composable
@Preview
private fun DefaultListAppBarPreview() {
    DefaultAppBar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteAllConfirmed = {}
    )
}

@Composable
@Preview
private fun SearchAppBarPreview() {

}