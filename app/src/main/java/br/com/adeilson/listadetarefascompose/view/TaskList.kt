package br.com.adeilson.listadetarefascompose.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.adeilson.listadetarefascompose.R
import br.com.adeilson.listadetarefascompose.listItem.TaskItem
import br.com.adeilson.listadetarefascompose.repository.TasksRepository
import br.com.adeilson.listadetarefascompose.ui.theme.Black
import br.com.adeilson.listadetarefascompose.ui.theme.Purple700
import br.com.adeilson.listadetarefascompose.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TaskList(navController: NavController) {

    val tasksRepository = TasksRepository()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple700,
                    titleContentColor = White
                ),
                title = {
                    Text(
                        text = stringResource(R.string.task_list), fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                },
            )
        },
        containerColor = Black,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("saveTask")
                },
                containerColor = Purple700,
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(R.string.save_task),
                )
            }
        }
    ) {
//        val taskList: MutableList<Task> = mutableListOf()
//        taskList.add(Task("Tarefa 1", "Descrição Tarefa 1", 0))
//        taskList.add(Task("Tarefa 2", "Descrição Tarefa 2", 1))
//        taskList.add(Task("Tarefa 3", "Descrição Tarefa 3", 2))
//        taskList.add(Task("Tarefa 4", "Descrição Tarefa 4", 3))
//        taskList.add(Task("Tarefa 5", "Descrição Tarefa 5", 3))

//        LazyColumn(
//            modifier = Modifier.padding(it)
//        ) {
//            itemsIndexed(taskList){
//                index, _ ->
//                TaskItem(index, taskList)
//            }
//        }

        val tasksList = tasksRepository.getTasks().collectAsState(mutableListOf()).value

        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            itemsIndexed(tasksList) { index, _ ->
                TaskItem(index, tasksList, context, navController)
            }
        }
    }
}