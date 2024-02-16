package ui.fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun <T> OptionsField(
    value: T,
    onValueChange: (T) -> Unit,
    options: List<T>,
    modifier: Modifier = Modifier.width(164.dp),
    label: @Composable (() -> Unit)? = null,
    itemToString: @Composable (T) -> String
) {
    var dropdownExpanded by remember { mutableStateOf(false) }

    OutlinedTextField(itemToString(value), {}, modifier, readOnly = true, label = label, trailingIcon = {
        Box {
            IconButton({
                dropdownExpanded = !dropdownExpanded
            }) {
                Icon(Icons.Default.KeyboardArrowDown, null)
            }

            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false }) {
                for (option in options) {
                    DropdownMenuItem({
                        onValueChange(option)
                        dropdownExpanded = false
                    }) {
                        Text(
                            itemToString(option)
                        )
                    }
                }
            }
        }
    })
}