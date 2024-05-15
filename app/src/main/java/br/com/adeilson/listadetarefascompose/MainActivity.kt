package br.com.adeilson.listadetarefascompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.adeilson.listadetarefascompose.ui.theme.ListaDeTarefasComposeTheme
import br.com.adeilson.listadetarefascompose.view.TaskList
import br.com.adeilson.listadetarefascompose.view.SaveTask

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaDeTarefasComposeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "taskList") {
                    composable(
                        route = "taskList"
                    ) {
                        TaskList(navController)
                    }

                    composable(
                        route = "saveTask"
                    ) {
                        SaveTask(navController)
                    }
                }
            }
        }
    }
}
