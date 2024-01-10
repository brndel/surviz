package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Options field data contains a [List] of [String]s that represent the possible options that can
 * be chosen from. It also holds the currently selected option. Only one option can be chosen at once.
 *
 *
 * @property options a [List] of all possible options
 *
 * @param initialValue the initially selected option
 * @param label label of the [FieldData]
 */
class OptionsFieldData(initialValue: String, label: String, var options: List<String>) :
    FieldData(label) {

    /**
     * The currently selected Option.
     */
    val value: MutableState<String> = mutableStateOf(initialValue)

    /**
     * Gets the selected option.
     *
     * @return the currently selected option
     */
    override fun getValue(): Any {
        return value.value
    }
}