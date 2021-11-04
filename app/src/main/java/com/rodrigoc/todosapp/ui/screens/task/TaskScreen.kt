package com.rodrigoc.todosapp.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.rodrigoc.todosapp.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit
) {

    Scaffold(
        topBar = {
            TaskAppBar(navigateToListScreen = navigateToListScreen)
        },
        content = { }
    )
}