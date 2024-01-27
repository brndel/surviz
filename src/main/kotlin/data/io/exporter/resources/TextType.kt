package data.io.exporter.resources

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

enum class TextType(val fontSize: TextUnit, val fontWeight: FontWeight) {
    Title(20.sp, FontWeight.Normal),
    Label(14.sp, FontWeight.Normal);
}