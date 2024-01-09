package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class OptionsFieldData constructor(initialValue: String, label: String, var options: List<String>) : FieldData(label) {
    val value: MutableState<String> = mutableStateOf(initialValue)

    override fun getValue(): Any {
        return value.value
    }
}