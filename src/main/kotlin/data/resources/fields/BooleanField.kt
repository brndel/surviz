package data.resources.fields

/**
 * TODO
 *
 * @property label
 */
class BooleanField constructor(
    var label: String
) : ConfigField {

    /**
     * TODO
     *
     * @return
     */
    override fun getLabel(): String {
        return label
    }
}