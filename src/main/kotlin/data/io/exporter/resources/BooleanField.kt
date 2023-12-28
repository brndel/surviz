package data.io.exporter.resources

class BooleanField constructor(
    var label: String
) : ExportFieldConfig {

    override fun getLabel(): String {
        return label
    }
}