package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Int field data stores an [Int] value.
 * Int field data can also contain [min] and [max] values that can limit the possible inputs.
 *
 * @property min minimum value that is allowed
 * @property max maximum value that is allowed
 *
 * @param initialValue initial [Int] that is stored in the field
 * @param label label of the [FieldData]
 */
class IntFieldData(
    initialValue: Int,
    label: String, val min: Int? = null, val max: Int? = null
) : FieldData(label) {

    /**
     * The [Int] value that is stored in the field.
     */
    val value: MutableState<Int> = mutableStateOf(initialValue)

    /**
     * Gets [Int] stored in the field.
     *
     * @return [Int] value of the field
     */
    override fun getValue(): Any {
        return value.value
    }
}