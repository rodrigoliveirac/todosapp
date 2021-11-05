package com.rodrigoc.todosapp.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NONE_ACTION
}

fun String?.toAction(): Action {
    return when {
        this == "ADD" -> {
            Action.ADD
        }
        this == "UPDATE" -> {
            Action.UPDATE
        }
        this == "DELETE" -> {
            Action.DELETE
        }
        this == "DELETE_ALL" -> {
            Action.DELETE_ALL
        }
        this == "UNDO" -> {
            Action.UNDO
        }
        else -> {
            Action.NONE_ACTION
        }
    }
}