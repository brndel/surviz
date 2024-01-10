package ui.fields

import androidx.compose.runtime.Composable

/**
 * An input field where the user can input a String
 *
 * @param value the current string value of this field
 * @param onValueChange gets called when the user changes the current value
 */
@Composable
fun TextField(value: String, onValueChange: (String) -> Unit) {
}