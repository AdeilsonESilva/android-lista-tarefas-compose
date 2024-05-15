package br.com.adeilson.listadetarefascompose.listItem

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import br.com.adeilson.listadetarefascompose.R
import br.com.adeilson.listadetarefascompose.model.Task
import br.com.adeilson.listadetarefascompose.repository.TasksRepository
import br.com.adeilson.listadetarefascompose.ui.theme.Black
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonGreenEnable
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonRedEnable
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonYellowEnable
import br.com.adeilson.listadetarefascompose.ui.theme.ShapeCardPriority
import br.com.adeilson.listadetarefascompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TaskItem(
    index: Int,
    taskList: MutableList<Task>,
    context: Context,
    navController: NavController
) {
    val taskTitle = taskList[index].task
    val taskDescription = taskList[index].description
    val taskPriority = taskList[index].priority

    val scope = rememberCoroutineScope()
//    val context = LocalContext.current
    val tasksRepository = TasksRepository()

    fun dialogDeleteTask() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog
            .setTitle("Excluir Tarefa")
            .setMessage("Deseja realmente excluir a tarefa?")
            .setPositiveButton("Sim") { _, _ ->
                tasksRepository.deleteTask(taskTitle.toString())

                scope.launch(Dispatchers.Main) {
                    taskList.removeAt(index)
                    navController.navigate("taskList")
                    Toast.makeText(context, "Tarefa excluída com sucesso!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Não") { _, _ -> }
            .show()

    }

    val priorityLevel: String = when (taskPriority) {
        0 -> {
            "Sem prioridade"
        }

        1 -> {
            "Prioridade Baixa"
        }

        2 -> {
            "Prioridade Média"
        }

        else -> {
            "Prioridade Alta"
        }
    }

    val priorityColor = when (taskPriority) {
        0 -> {
            Black
        }

        1 -> {
            RadioButtonGreenEnable
        }

        2 -> {
            RadioButtonYellowEnable
        }

        else -> {
            RadioButtonRedEnable
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(20.dp)
        ) {
            val (txtTitle, txtDescription, cardPriority, txtPriority, btnDelete) = createRefs()

            Text(
                text = taskTitle.toString(),
                modifier = Modifier.constrainAs(txtTitle) {
                    top.linkTo(parent.top, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = taskDescription.toString(),
                modifier = Modifier.constrainAs(txtDescription) {
                    top.linkTo(txtTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                }
            )

            Text(
                text = priorityLevel,
                modifier = Modifier.constrainAs(txtPriority) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            )

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = priorityColor
                ),
                modifier = Modifier
                    .size(30.dp)
                    .constrainAs(cardPriority) {
                        top.linkTo(txtDescription.bottom, margin = 10.dp)
                        start.linkTo(txtPriority.end, margin = 10.dp)
                        bottom.linkTo(parent.bottom, margin = 10.dp)
                    },
                shape = ShapeCardPriority.large
            ) {

            }

            IconButton(
                onClick = {

                    dialogDeleteTask()
                },
                modifier = Modifier.constrainAs(btnDelete) {
                    top.linkTo(txtDescription.bottom, margin = 10.dp)
                    start.linkTo(cardPriority.end, margin = 30.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                }
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_delete),
                    contentDescription = null
                )
            }
        }
    }
}

//@Composable
//@Preview
//private fun TaskItemPreview() {
//    TaskItem()
//}