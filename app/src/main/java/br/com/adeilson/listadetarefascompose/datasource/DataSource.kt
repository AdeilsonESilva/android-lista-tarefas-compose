package br.com.adeilson.listadetarefascompose.datasource

import br.com.adeilson.listadetarefascompose.constants.FirebaseConstants
import br.com.adeilson.listadetarefascompose.model.Task
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DataSource {
    private val db = FirebaseFirestore.getInstance()
    private val _allTasks = MutableStateFlow<MutableList<Task>>(mutableListOf())
    private val allTasks: StateFlow<MutableList<Task>> = _allTasks

    fun saveTask(task: String, description: String, priority: Int) {
        val mapTask = hashMapOf(
            "task" to task,
            "description" to description,
            "priority" to priority
        )

        db.collection(FirebaseConstants.collectionName).document(task).set(mapTask)
            .addOnCompleteListener {

            }.addOnFailureListener {

        }
    }

    fun getTasks(): Flow<MutableList<Task>> {
        val tasksList: MutableList<Task> = mutableListOf()

        db.collection(FirebaseConstants.collectionName).get()
            .addOnCompleteListener { querySnapshot ->
                if (querySnapshot.isSuccessful) {
                    for (document in querySnapshot.result) {
                        val task = document.toObject<Task>()
                        tasksList.add(task)
                        _allTasks.value = tasksList
                    }
                }
            }

        return allTasks
    }

    fun deleteTask(task: String) {
        db.collection(FirebaseConstants.collectionName)
            .document(task)
            .delete()
            .addOnCompleteListener {

            }.addOnFailureListener {

            }
    }
}