package ui.fields

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*

/**
 * An input field where the user can select an Int
 *
 * @param value the current integer value of the field
 * @param onValueChange gets called when the user selects a new int
 * @param maxValue the maximum value that is allowed in this field. If maxValue is null, there is no maximum value
 * @param minValue the minimum value that is allowed in this field. If minValue is null, there is no maximum value
 */
@Composable
fun IntField(value: Int, onValueChange: (Int) -> Unit, maxValue: Int? = null, minValue: Int? = null, label: @Composable (() -> Unit)? = null) {
    var text by remember { mutableStateOf(value.toString()) }

    var hasError by remember { mutableStateOf(false) }

    OutlinedTextField(text, {
        text = it
        val newValue = text.toIntOrNull()

        val isValid =
            newValue != null && (maxValue == null || newValue <= maxValue) && (minValue == null || newValue >= minValue)

        if (isValid) {
            onValueChange(newValue!!)
        }

        hasError = !isValid
    }, isError = hasError, label = label)
}