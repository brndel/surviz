package ui.fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <T> OptionsField(
    value: T,
    onValueChange: (T) -> Unit,
    options: List<T>,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
    item: @Composable (T) -> Unit
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        label?.invoke()

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


}