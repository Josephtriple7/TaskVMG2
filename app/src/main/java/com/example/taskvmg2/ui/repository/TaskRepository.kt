package com.example.taskvmg2.ui.repository

import com.example.taskvmg2.ui.model.Task

class TaskRepository {
    private val tasks = mutableListOf<Task>(
        Task(1, "Diseñar UI", "Crear los mockups de pantallas", 2, false),
        Task(2, "Configurar proyecto", "Crear paquetes y dependencias", 1, true),
        Task(3, "Implementar navegación", "Configurar NavHost y rutas", 3, false),
        Task(4, "Crear repositorio", "Lógica de datos local", 2, true),
        Task(5, "Conectar ViewModel", "Unir UI con lógica de negocio", 3, false)
    )

    fun getTasks(): List<Task> = tasks

    fun addTask(task: Task) = tasks.add(task)

    fun getTaskById(id: Int): Task? = tasks.find { it.id == id }

    fun removeTask(task: Task) = tasks.remove(task)

    fun toggleTask(task: Task) {
        val index = tasks.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasks[index] = task.copy(completed = !task.completed)
        }
    }
}