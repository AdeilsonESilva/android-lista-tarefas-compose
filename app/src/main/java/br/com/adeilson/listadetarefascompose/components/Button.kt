package br.com.adeilson.listadetarefascompose.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.adeilson.listadetarefascompose.ui.theme.LightBlue
import br.com.adeilson.listadetarefascompose.ui.theme.White

@Composable
fun Button(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = LightBlue,
            contentColor = White
        )
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, fontSize = 18.sp)
    }
}