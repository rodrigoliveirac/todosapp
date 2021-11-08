package com.rodrigoc.todosapp.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rodrigoc.todosapp.ui.screens.task.TaskScreen
import com.rodrigoc.todosapp.ui.viewmodels.SharedViewModel
import com.rodrigoc.todosapp.util.Action
import com.rodrigoc.todosapp.util.Constants.TASK_ARGUMENT_KEY
import com.rodrigoc.todosapp.util.Constants.TASK_SCREEN

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit,
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ) { navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)

        sharedViewModel.selectedTask(taskId = taskId)

        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {  //sometimes this operation runs first than collectAsState() which its could be a problem such as update the information, so to make sure if the task is already update on collectAsState, we need to pass the 'selectedTask' as a key instead of 'taskId'
            if (selectedTask != null || taskId == -1) {
                sharedViewModel.updateTask(selectedTask = selectedTask)
            }
        }
        TaskScreen(
            selectedTask = selectedTask,
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen
        )
    }
}