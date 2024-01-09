package data.resources.fields

sealed class FieldData(private val label: String) {

    /**
     *
     * @return
     */
    fun getLabel(): String {
        return label
    }

    abstract fun getValue(): Any
}