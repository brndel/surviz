package ui.fields

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * An input field where the user can select a Color
 *
 * @param color the current color of the field
 * @param onColorChange gets called when the color gets changed by the user
 */
@Composable
fun ColorField(color: Color, onColorChange: (Color) -> Unit) {
}