package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class BooleanFieldData constructor(
    initialValue: Boolean,
    label: String
) : FieldData(label) {
    val value: MutableState<Boolean> = mutableStateOf(initialValue)

    override fun getValue(): Any {
        return value.value
    }

}