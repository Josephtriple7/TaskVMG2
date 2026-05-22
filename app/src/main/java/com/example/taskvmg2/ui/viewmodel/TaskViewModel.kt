package com.example.taskvmg2.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskvmg2.ui.model.Task
import com.example.taskvmg2.ui.repository.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val repository = TaskRepository()

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    var id by mutableStateOf("")
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    var priority by mutableStateOf("1")
        private set

    var completed by mutableStateOf(false)
        private set

    init {
        _tasks.value = repository.getTasks().toList()
    }

    fun onIdChange(value: String) { id = value }

    fun onTitleChange(value: String) { title = value }

    fun onDescriptionChange(value: String) { description = value }

    fun onPriorityChange(value: String) { priority = value }

    fun loadTask(taskId: Int?) {
        val task = taskId?.let { repository.getTaskById(it) }
        if (task != null) {
            id = task.id.toString()
            title = task.title
            description = task.description
            priority = task.priority.toString()
            completed = task.completed
        }
    }

    fun addTask(task: Task) {
        repository.addTask(task)
        _tasks.value = repository.getTasks().toList()
    }

    fun removeTask(task: Task) {
        repository.removeTask(task)
        _tasks.value = _tasks.value.filter { it.id != task.id }
    }

    fun toggleTask(task: Task) {
        repository.toggleTask(task)
        _tasks.value = _tasks.value.map {
            if (it.id == task.id) it.copy(completed = !it.completed) else it
        }
    }
}