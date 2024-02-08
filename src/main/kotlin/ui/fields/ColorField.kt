package ui.fields

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun ColorField(
    color: Color,
    onColorChange: (Color) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    var popupOpen by remember { mutableStateOf(false) }

    var hexColor by remember { mutableStateOf(color.toHex()) }
    var isError by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = hexColor,
        singleLine = true,
        modifier = modifier,
        label = label,
        onValueChange = {
            hexColor = it
            val col = Color.fromHex(it)
            isError = col != null

            if (col != null) {
                onColorChange(col)
            }
        },
        isError = isError,
        leadingIcon = {
            Surface(
                color = color,
                modifier = Modifier.size(16.dp),
                shape = RoundedCornerShape(4.dp)
            ) {}
        },
        trailingIcon = {
            IconButton(onClick = {
                popupOpen = true
            }) {
                Box {
                    Icon(Icons.Default.ArrowDropDown, null)
                    if (popupOpen) {
                        Popup(onDismissRequest = {
                            popupOpen = false
                        }) {
                            fun updateColor(newColor: Color) {
                                onColorChange(newColor)
                                hexColor = newColor.toHex()
                                isError = false
                            }
                            Surface(
                                Modifier.width(128.dp),
                                shape = RoundedCornerShape(4.dp),
                                elevation = 8.dp
                            ) {

                                @Composable
                                fun ColorSlider(
                                    value: (Color) -> Float,
                                    newColor: Color.(Float) -> Color,
                                    sliderColor: Color
                                ) {
                                    Slider(
                                        value = value(color),
                                        valueRange = 0F..1F,
                                        onValueChange = {
                                            updateColor(newColor(color, it))
                                        },
                                        colors = SliderDefaults.colors(
                                            thumbColor = sliderColor,
                                            activeTrackColor = sliderColor
                                        )
                                    )
                                }

                                Column(Modifier.padding(4.dp)) {
                                    ColorSlider({ it.red }, { copy(red = it) }, Color.Red)
                                    ColorSlider({ it.green }, { copy(green = it) }, Color.Green)
                                    ColorSlider({ it.blue }, { copy(blue = it) }, Color.Blue)
                                }
                            }
                        }
                    }
                }
            }
        })

}

@OptIn(ExperimentalStdlibApi::class)
private val hexFormat = HexFormat {
    upperCase = true
}

@OptIn(ExperimentalStdlibApi::class)
private fun Color.Companion.fromHex(hex: String): Color? {
    return try {
        val value = hex.uppercase().hexToInt(hexFormat)
        Color(value)
    } catch (e: IllegalArgumentException) {
        null
    }
}


@OptIn(ExperimentalStdlibApi::class)
private fun Color.toHex(): String {
    return toArgb().toHexString(hexFormat)
}