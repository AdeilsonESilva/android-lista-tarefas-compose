package br.com.adeilson.listadetarefascompose.repository

import br.com.adeilson.listadetarefascompose.datasource.DataSource
import br.com.adeilson.listadetarefascompose.model.Task
import kotlinx.coroutines.flow.Flow

class TasksRepository {
    private val dataSource = DataSource()

    fun saveTask(task: String, description: String, priority: Int) {
        dataSource.saveTask(task, description, priority)
    }

    fun getTasks(): Flow<MutableList<Task>> {
        return dataSource.getTasks()
    }

    fun deleteTask(task: String) {
        dataSource.deleteTask(task)
    }

}