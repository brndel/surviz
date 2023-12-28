package data.io.exporter.resources

sealed interface ExportFieldConfig {

    fun getLabel(): String
}