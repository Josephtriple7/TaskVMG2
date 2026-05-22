package com.example.taskvmg2.ui.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int = 1,
    val completed: Boolean = false
)