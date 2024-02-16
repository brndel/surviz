package ui.fields

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.*

@Composable
fun <T> ParserField(
    value: T,
    onValueChange: (T) -> Unit,
    parse: (String) -> T?,
    toString: (T) -> String,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    var text by remember(if (isFocused) Unit else value) { mutableStateOf(toString(value)) }
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
    }, modifier = modifier.onFocusChanged {
        isFocused = it.isFocused
    }, singleLine = true, isError = isError, label = label)
}