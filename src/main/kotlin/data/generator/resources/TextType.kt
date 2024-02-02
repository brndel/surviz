package data.generator.resources

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Enum describing all different text types
 *
 * @property fontSizeKey key to get font size out of .properties file
 * @property fontWeight font weight
 * @constructor Create empty Text type
 */
enum class TextType(val fontSizeKey: String, val fontWeight: FontWeight) {
    Title("text_title_size", FontWeight.Normal),
    Label("text_label_size", FontWeight.Normal);
}