package com.rodrigoc.todosapp.data.models

import androidx.compose.ui.graphics.Color
import com.rodrigoc.todosapp.ui.theme.HighPriorityColor
import com.rodrigoc.todosapp.ui.theme.LowPriorityColor
import com.rodrigoc.todosapp.ui.theme.MediumPriorityColor
import com.rodrigoc.todosapp.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {

    HIGH(color = HighPriorityColor),
    MEDIUM(color = MediumPriorityColor),
    LOW(color = LowPriorityColor),
    NONE(color = NonePriorityColor),
}