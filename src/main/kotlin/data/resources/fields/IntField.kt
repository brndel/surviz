package data.resources.fields

class IntField constructor(
    var label: String, var min: Int, var max: Int
) : ConfigField {

    override fun getLabel(): String {
        return label
    }
}