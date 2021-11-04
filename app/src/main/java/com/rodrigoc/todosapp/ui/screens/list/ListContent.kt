package com.rodrigoc.todosapp.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.rodrigoc.todosapp.data.models.Priority
import com.rodrigoc.todosapp.data.models.Task
import com.rodrigoc.todosapp.ui.theme.*
import com.rodrigoc.todosapp.util.RequestState

@ExperimentalMaterialApi
@Composable
fun ListContent(
    tasks: RequestState<List<Task>>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    if (tasks is RequestState.Success) {
        if (tasks.data.isEmpty()) {
            EmptyContent()
        } else {
            DisplayAllTasks(
                tasks = tasks.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DisplayAllTasks(
    tasks: List<Task>,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {

    LazyColumn {
        items(tasks, key = { tasks ->
            tasks
        }) { taskItem ->
            TaskItem(
                task = taskItem,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    task: Task,
    navigateToTaskScreen: (taskId: Int) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.taskItem,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(task.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = task.title,
                    color = MaterialTheme.colors.onTaskItem,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd) {
                    Canvas(
                        modifier = Modifier
                            .size(PRIORITY_INDICATOR_SIZE)
                    ) {
                        drawCircle(
                            color = task.priority.color
                        )
                    }
                }
            }

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = task.description,
                color = MaterialTheme.colors.onTaskItem,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@ExperimentalMaterialApi
@Composable
@Preview
fun TaskItemPreview() {
    TaskItem(task = Task(0, "Titleee", "some random tet", Priority.HIGH),
        navigateToTaskScreen = {})
}