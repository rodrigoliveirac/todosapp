package com.rodrigoc.todosapp.navigation

import androidx.navigation.NavHostController
import com.rodrigoc.todosapp.util.Action
import com.rodrigoc.todosapp.util.Constants.LIST_SCREEN

class Screens(navController: NavHostController) {

    val list: (Action) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN) { inclusive = true }
        }
    }
    val task: (Int) -> Unit = { taskId ->
        navController.navigate(route = "task/$taskId")
    }
}