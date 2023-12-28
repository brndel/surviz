package data.io.exporter.resources

class FilePathField constructor(var label: String, var placeholders: List<String>) : ExportFieldConfig {
    override fun getLabel(): String {
        return label
    }
}