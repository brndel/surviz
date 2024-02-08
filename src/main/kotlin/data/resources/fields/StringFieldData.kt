package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * String field data can store a [String] of any length.
 *
 * @param initialValue initial [String] that is stored in the field
 * @param label label of the [FieldData]
 */
class StringFieldData(
    initialValue: String,
    label: String,
    val hint: StringFieldHint? = null
) : FieldData(label) {

    /**
     * [String] that the field holds.
     */
    val value: MutableState<String> = mutableStateOf(initialValue)

    /**
     * Gets the [String] stored in the field.
     *
     * @return the stored [String]
     */
    override fun getValue(): Any {
        return value.value
    }
}

enum class StringFieldHint {
    Directory
}