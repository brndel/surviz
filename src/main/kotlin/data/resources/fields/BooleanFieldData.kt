package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Boolean field data holds a single [Boolean] value. Can be used for simple *"yes or no"* inputs
 * by the user.
 *
 * @param initialValue [Boolean] value that will initially be stored
 * @param label label of the [FieldData]
 */
class BooleanFieldData(
    initialValue: Boolean,
    label: String
) : FieldData(label) {

    /**
     * [Boolean] value the field holds
     */
    val value: MutableState<Boolean> = mutableStateOf(initialValue)

    /**
     * Gets the [Boolean] value stored in the field.
     *
     * @return value stored in the field
     */
    override fun getValue(): Any {
        return value.value
    }

}