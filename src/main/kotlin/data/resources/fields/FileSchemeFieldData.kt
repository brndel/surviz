package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * File scheme field data can store the scheme for dynamically naming files.
 * It also can contain a [List] of [placeholders] that can be replaced depending on the object to store.
 *
 * @property placeholders a [List] of [String]s to dynamically replace
 *
 * @param initialValue initial Scheme for file names
 * @param label label of the [FieldData]
 */
class FileSchemeFieldData(
    initialValue: String,
    label: String,
    val placeholders: List<String>
) : FieldData(label) {

    /**
     * [String] holding the current Scheme value the field holds
     */
    val value: MutableState<String> = mutableStateOf(initialValue)

    /**
     * Get the stored file name scheme
     *
     * @return the current scheme
     */
    override fun getValue(): Any {
        return value.value
    }
}