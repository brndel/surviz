package data.resources.fields

class StringField constructor(
    private var label: String
) : ConfigField  {

    override fun getLabel(): String {
        return label
    }
}