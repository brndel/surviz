package ui.fields

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.runtime.*

@Composable
fun <T> OptionsField(value: T, onValueChange: (T) -> Unit, options: List<T>, item: @Composable (T) -> Unit) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    Box {
        Button({ dropdownExpanded = true }) {
            item(value)
        }

        DropdownMenu(expanded = dropdownExpanded, onDismissRequest = { dropdownExpanded = false }) {
            for (option in options) {
                DropdownMenuItem({
                    onValueChange(option)
                    dropdownExpanded = false
                }) {
                    item(option)
                }
            }
        }

    }
}