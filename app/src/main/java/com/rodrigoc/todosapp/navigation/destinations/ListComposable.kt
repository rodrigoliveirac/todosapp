package com.rodrigoc.todosapp.navigation.destinations

import android.app.Notification
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rodrigoc.todosapp.ui.screens.list.ListScreen
import com.rodrigoc.todosapp.ui.viewmodels.SharedViewModel
import com.rodrigoc.todosapp.util.Constants.LIST_ARGUMENT_KEY
import com.rodrigoc.todosapp.util.Constants.LIST_SCREEN
import com.rodrigoc.todosapp.util.toAction

@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel,
) {
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()
        Log.d("ListComposable", action.name)

        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }
        ListScreen(
            navigateToTaskScreen = navigateToTaskScreen,
            sharedViewModel = sharedViewModel)
    }
}


