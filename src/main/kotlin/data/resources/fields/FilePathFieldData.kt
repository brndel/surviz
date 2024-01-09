package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

class FilePathFieldData constructor(initialValue: String, label: String, val placeholders: List<String>) : FieldData(label) {
    val value: MutableState<String> = mutableStateOf(initialValue)

    override fun getValue(): Any {
        return value.value
    }
}