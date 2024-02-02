package ui.fields

import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * An input field where the user can input a String
 *
 * @param value the current string value of this field
 * @param onValueChange gets called when the user changes the current value
 */
@Composable
fun TextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(value, onValueChange, modifier, label = label)
}