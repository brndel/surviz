package data.resources.fields

/**
 * Field Data is a simple interface for storing and obtaining single or multiple values of different types.
 * Each Object contains a [label] that could be displayed by the UI.
 *
 * @property label label of the field data
 */
sealed class FieldData(private val label: String) {

    /**
     * Simple method to obtain the label of the field data.
     *
     * @return the label of the field data
     */
    fun getLabel(): String {
        return label
    }

    /**
     * Get value stored in the field. Each implementation can return a different type.
     *
     * @return the value stored in the field
     */
    abstract fun getValue(): Any

    /**
     * Checks for validity of content
     *
     * @return true by default
     */
    open fun check(): Boolean {
        return true
    }
}