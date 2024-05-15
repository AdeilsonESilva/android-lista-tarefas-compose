package br.com.adeilson.listadetarefascompose.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import br.com.adeilson.listadetarefascompose.ui.theme.Black
import br.com.adeilson.listadetarefascompose.ui.theme.LightBlue
import br.com.adeilson.listadetarefascompose.ui.theme.ShapeEditText
import br.com.adeilson.listadetarefascompose.ui.theme.White

@Composable
fun InputText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType,
) {
    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = {
            Text(label)
        },
        maxLines = maxLines,
        colors = OutlinedTextFieldDefaults.colors(
            focusedSupportingTextColor = Black,
            focusedBorderColor = LightBlue,
            focusedLabelColor = LightBlue,
            cursorColor =  LightBlue,
            focusedContainerColor = White,
        ),
        shape = ShapeEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

@Composable
@Preview
private fun InputTextPreview() {
    InputText(
        value = "Adeilson",
        onValueChange =  {},
        modifier =  Modifier.fillMaxWidth(),
        label = "Descrição",
        maxLines = 1,
        keyboardType = KeyboardType.Text
    )
}