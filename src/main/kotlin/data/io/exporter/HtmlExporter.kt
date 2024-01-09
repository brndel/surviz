package data.io.exporter

import data.project.config.ProjectConfiguration
import data.resources.fields.NamedField

/**
 * This class implements the [Exporter] interface and exports the project to an HTML file.
 */
class HtmlExporter : Exporter {
    /**
     * This method exports the project to an HTML file.
     *
     * @param configuration The project configuration.
     * @param exportConfig The export configuration.
     */
    override fun export(configuration: ProjectConfiguration, exportConfig: Map<String, Any>) {
        TODO("Not yet implemented")
    }

    /**
     * This method returns the fields of the exporter.
     *
     * @return The fields of the exporter.
     */
    override fun getFields(): List<NamedField> {
        TODO("Not yet implemented")
    }
}
