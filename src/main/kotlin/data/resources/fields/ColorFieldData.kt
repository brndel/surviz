package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

/**
 * Color field data can hold a single [Color] value.
 *
 * @param initialValue initial [Color] value that will be stored in the field
 * @param label label of the [FieldData]
 */
class ColorFieldData(initialValue: Color, label: String) : FieldData(label) {

    /**
     * [Color] value the field holds
     */
    val value: MutableState<Color> = mutableStateOf(initialValue)

    /**
     * Gets [Color] that's stored in the field.
     *
     * @return [Color] value of the field
     */
    override fun getValue(): Any {
        return value.value
    }
}