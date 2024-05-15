package br.com.adeilson.listadetarefascompose.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.adeilson.listadetarefascompose.components.Button
import br.com.adeilson.listadetarefascompose.components.InputText
import br.com.adeilson.listadetarefascompose.constants.Constants
import br.com.adeilson.listadetarefascompose.repository.TasksRepository
import br.com.adeilson.listadetarefascompose.ui.theme.Purple700
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonGreenDisable
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonGreenEnable
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonRedDisable
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonRedEnable
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonYellowDisable
import br.com.adeilson.listadetarefascompose.ui.theme.RadioButtonYellowEnable
import br.com.adeilson.listadetarefascompose.ui.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SaveTask(navController: NavHostController) {

    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val tasksRepository = TasksRepository()

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple700,
                    titleContentColor = White
                ),
                title = {
                    Text(
                        text = "Salvar Tarefa", fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            )
        }
    ) {
        var taskTitle by remember {
            mutableStateOf("")
        }
        var taskDescription by remember {
            mutableStateOf("")
        }
        var lowPriorityTask by remember {
            mutableStateOf(false)
        }
        var mediumPriorityTask by remember {
            mutableStateOf(false)
        }
        var highPriorityTask by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            InputText(
                value = taskTitle,
                onValueChange = {
                    taskTitle = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, it.calculateTopPadding(), 20.dp, 0.dp),
                label = "Título Tarefa",
                maxLines = 1,
                keyboardType = KeyboardType.Text,
            )

            InputText(
                value = taskDescription,
                onValueChange = {
                    taskDescription = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(20.dp, 10.dp, 20.dp, 0.dp),
                label = "Descrição",
                maxLines = 5,
                keyboardType = KeyboardType.Text,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Nível de prioridade")

                RadioButton(
                    selected = lowPriorityTask,
                    onClick = {
                        lowPriorityTask = !lowPriorityTask
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonGreenDisable,
                        selectedColor = RadioButtonGreenEnable
                    )
                )

                RadioButton(
                    selected = mediumPriorityTask,
                    onClick = {
                        mediumPriorityTask = !mediumPriorityTask
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonYellowDisable,
                        selectedColor = RadioButtonYellowEnable
                    )
                )

                RadioButton(
                    selected = highPriorityTask,
                    onClick = {
                        highPriorityTask = !highPriorityTask
                    },
                    colors = RadioButtonDefaults.colors(
                        unselectedColor = RadioButtonRedDisable,
                        selectedColor = RadioButtonRedEnable
                    )
                )
            }

            Button(
                onClick = {
                    var message = true

                    scope.launch(Dispatchers.IO){
                        if(taskTitle.isEmpty()){
                            message = false
                            return@launch
                        }

                        when {
                            lowPriorityTask -> tasksRepository.saveTask(taskTitle, taskDescription, Constants.LOW_PRIORITY)
                            mediumPriorityTask -> tasksRepository.saveTask(taskTitle, taskDescription, Constants.MEDIUM_PRIORITY)
                            highPriorityTask -> tasksRepository.saveTask(taskTitle, taskDescription, Constants.HIGH_PRIORITY)
                            else -> tasksRepository.saveTask(taskTitle, taskDescription, Constants.WITHOUT_PRIORITY)
                        }
                    }

                    scope.launch(Dispatchers.Main){
                        if (message) {
                            Toast.makeText(context, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Título da tarefa é obrigatório!", Toast.LENGTH_SHORT).show()
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(20.dp),
                text = "Salvar"
            )
        }
    }
}