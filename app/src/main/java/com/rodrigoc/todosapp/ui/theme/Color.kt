package com.rodrigoc.todosapp.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val PrimaryContainer = Color(0xFFEADDFF)
val OnPrimaryContainer = Color(0xFF21005D)

val DarkPrimaryContainer = Color(0xFF4F378B)
val DarkOnPrimaryContainer = Color(0xFFEADDFF)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)
val NonePriorityColor = Color(0xFFFFFFFF)

val Colors.AppBarIcons: Color
    @Composable
    get() = if (isLight) OnPrimaryContainer else DarkOnPrimaryContainer

val Colors.fabBackgroundColor: Color
    @Composable
    get() = if (isLight) PrimaryContainer else DarkPrimaryContainer

val Colors.iconFabColor: Color
    @Composable
    get() = if (isLight) OnPrimaryContainer else DarkOnPrimaryContainer

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) OnPrimaryContainer else DarkOnPrimaryContainer

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) PrimaryContainer else DarkPrimaryContainer