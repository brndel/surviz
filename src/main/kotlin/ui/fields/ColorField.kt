package ui.fields

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup

/**
 * An input field where the user can select a Color
 *
 * @param color the current color of the field
 * @param onColorChange gets called when the color gets changed by the user
 */
@Composable
fun ColorField(color: Color, onColorChange: (Color) -> Unit) {
    var popupOpen by remember { mutableStateOf(false) }

    var hexColor by remember { mutableStateOf(color.toHex()) }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(value = hexColor, onValueChange = {
        hexColor = it
        val col = Color.fromHex(it)
        isError = col != null

        if (col != null) {
            onColorChange(col)
        }
    }, isError = isError, leadingIcon = {
        Surface(
            color = color,
            modifier = Modifier.size(16.dp),
            shape = RoundedCornerShape(4.dp)
        ) {}
    }, trailingIcon = {
        IconButton(onClick = {
            popupOpen = true
        }) {
            Box {
                Icon(Icons.Default.ArrowDropDown, null)
                if (popupOpen) {
                    Popup(onDismissRequest = {
                        popupOpen = false
                    }) {
                        val updateColor = { newColor: Color ->
                            onColorChange(newColor)
                            hexColor = newColor.toHex()
                        }
                        Surface(Modifier.width(128.dp), shape = RoundedCornerShape(4.dp), elevation = 8.dp) {
                            Column(Modifier.padding(4.dp)) {
                                Slider(value = color.red, valueRange = 0F..1F, onValueChange = {
                                    updateColor(color.copy(red = it))
                                }, colors = SliderDefaults.colors(Color.Red))

                                Slider(value = color.green, valueRange = 0F..1F, onValueChange = {
                                    updateColor(color.copy(green = it))
                                }, colors = SliderDefaults.colors(Color.Green))

                                Slider(value = color.blue, valueRange = 0F..1F, onValueChange = {
                                    updateColor(color.copy(blue = it))
                                }, colors = SliderDefaults.colors(Color.Blue))
                            }
                        }
                    }
                }
            }
        }
    })

}

@OptIn(ExperimentalStdlibApi::class)
private fun Color.Companion.fromHex(hex: String): Color? {
    return try {
        val value = hex.hexToInt(HexFormat.Default)
        Color(value)
    } catch (e: IllegalArgumentException) {
        null
    }
}


@OptIn(ExperimentalStdlibApi::class)
private fun Color.toHex(): String {
    return toArgb().toHexString(HexFormat.UpperCase)
}