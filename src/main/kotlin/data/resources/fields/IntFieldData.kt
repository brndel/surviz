package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class IntFieldData constructor(
    initialValue: Int,
    label: String, val min: Int, val max: Int
) : FieldData(label) {
    val value: MutableState<Int> = mutableStateOf(initialValue)

    override fun getValue(): Any {
        return value.value
    }
}