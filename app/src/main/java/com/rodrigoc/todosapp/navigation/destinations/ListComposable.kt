package com.rodrigoc.todosapp.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rodrigoc.todosapp.ui.screens.list.ListScreen
import com.rodrigoc.todosapp.util.Constants.LIST_ARGUMENT_KEY
import com.rodrigoc.todosapp.util.Constants.LIST_SCREEN

fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
      ListScreen(navigateToTaskScreen = navigateToTaskScreen)
    }
}