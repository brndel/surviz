package ui.fields

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun <T> ParserField(
    value: T,
    onValueChange: (T) -> Unit,
    parse: (String) -> T?,
    toString: (T) -> String,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    var text by remember { mutableStateOf(toString(value)) }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(text, {
        text = it
        val newValue = parse(it)

        if (newValue != null) {
            onValueChange(newValue)
            isError = false
        } else {
            isError = true
        }
    }, modifier = modifier, singleLine = true, isError = isError, label = label)
}