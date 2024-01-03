package data.resources.fields

class OptionsField constructor(var label: String, var options: List<String>) : ConfigField {
    override fun getLabel(): String {
        return label
    }
}