package ui.fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * An input field where the user can select a Double
 *
 * @param value the current integer value of the field
 * @param onValueChange gets called when the user selects a new int
 * @param maxValue the maximum value that is allowed in this field. If maxValue is null, there is no maximum value
 * @param minValue the minimum value that is allowed in this field. If minValue is null, there is no maximum value
 */
@Composable
fun DoubleField(
    value: Double,
    onValueChange: (Double) -> Unit,
    maxValue: Double? = null,
    minValue: Double? = null,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {

    ParserField(value, onValueChange, parse = {
        val newValue = it.toDoubleOrNull()

        if (newValue != null && (maxValue == null || newValue <= maxValue) && (minValue == null || newValue >= minValue)) {
            newValue
        } else {
            null
        }

    }, toString = { it.toString() }, modifier, label)
}