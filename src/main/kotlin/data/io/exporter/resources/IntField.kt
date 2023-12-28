package data.io.exporter.resources

class IntField constructor(
    var label: String, var min: Int, var max: Int
) : ExportFieldConfig {

    override fun getLabel(): String {
        return label
    }
}