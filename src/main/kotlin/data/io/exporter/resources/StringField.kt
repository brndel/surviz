package data.io.exporter.resources

class StringField constructor(
    private var label: String
) : ExportFieldConfig  {

    override fun getLabel(): String {
        return label
    }
}