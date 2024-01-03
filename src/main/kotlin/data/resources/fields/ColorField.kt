package data.resources.fields

class ColorField constructor(var label: String) : ConfigField {
    override fun getLabel(): String {
        return label
    }
}