package ui.fields

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

/**
 * An input field where the user can select an Int
 *
 * @param value the current integer value of the field
 * @param onValueChange gets called when the user selects a new int
 * @param maxValue the maximum value that is allowed in this field. If maxValue is null, there is no maximum value
 * @param minValue the minimum value that is allowed in this field. If minValue is null, there is no maximum value
 */
@Composable
fun IntField(
    value: Int,
    onValueChange: (Int) -> Unit,
    maxValue: Int? = null,
    minValue: Int? = null,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    ParserField(value, onValueChange, parse = {
        val newValue = it.toIntOrNull()

        if (newValue != null && (maxValue == null || newValue <= maxValue) && (minValue == null || newValue >= minValue)) {
            newValue
        } else {
            null
        }

    }, toString = { it.toString() }, modifier, label)
}