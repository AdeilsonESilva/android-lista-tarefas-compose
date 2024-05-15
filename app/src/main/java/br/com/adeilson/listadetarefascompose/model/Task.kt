package br.com.adeilson.listadetarefascompose.model

data class Task(
    val task: String? = null,
    val description: String? = null,
    val priority: Int? = null,
)