package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

class ColorFieldData constructor(initialValue: Color, label: String) : FieldData(label) {
    val value: MutableState<Color> = mutableStateOf(initialValue)

    override fun getValue(): Any {
        return value.value
    }
}