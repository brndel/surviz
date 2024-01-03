package data.resources.fields

class FilePathField constructor(var label: String, var placeholders: List<String>) : ConfigField {
    override fun getLabel(): String {
        return label
    }
}