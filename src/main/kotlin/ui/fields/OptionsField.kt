package ui.fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

@Composable
fun <T> OptionsField(
    value: T,
    onValueChange: (T) -> Unit,
    options: List<T>,
    modifier: Modifier = Modifier.width(164.dp),
    label: @Composable (() -> Unit)? = null,
    itemToString: @Composable (T) -> String
) {
    val currentValueString = itemToString(value)
    var dropdownExpanded by remember { mutableStateOf(false) }

    var isFocused by remember { mutableStateOf(false) }

    var fieldString by remember(if (isFocused) Unit else value) { mutableStateOf(currentValueString) }


    val filteredValues =
        if (!isFocused) {
            listOf()
        } else {
            options.map { it to itemToString(it) }.filter { it.second.contains(fieldString, ignoreCase = true) }
        }


    OutlinedTextField(fieldString, {
        fieldString = it
    }, modifier.onFocusChanged {
        isFocused = it.isFocused
    }.onKeyEvent {
        if (it.key == Key.Enter) {
            val firstValue = filteredValues.first()

            fieldString = firstValue.second
            onValueChange(firstValue.first)

            true
        } else {
            false
        }
    }, label = label, singleLine = true, trailingIcon = {
        Box {
            IconButton({
                dropdownExpanded = !dropdownExpanded
            }) {
                Icon(Icons.Default.KeyboardArrowDown, null)
            }

            DropdownMenu(
                expanded = dropdownExpanded,
                onDismissRequest = { dropdownExpanded = false }
            ) {
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

            if (isFocused) {
                Popup(
                    offset = IntOffset(0, 42)
                ) {
                    Surface(color = MaterialTheme.colors.surface, elevation = 8.dp, shape = RoundedCornerShape(8.dp)) {
                        Column(Modifier.padding(8.dp)) {
                            var isFirst = true
                            for ((_, text) in filteredValues) {
                                Text(
                                    text,
                                    style = if (isFirst) TextStyle(color = MaterialTheme.colors.onSurface) else TextStyle()
                                )
                                isFirst = false
                            }
                        }
                    }
                }
            }
        }
    })
}