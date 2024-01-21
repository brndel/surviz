package data.io.exporter

import data.project.Project
import data.project.config.ProjectConfiguration
import data.resources.fields.NamedField

/**
 * This class implements the [Exporter] interface and exports the project to a PNG file.
 */
class PngExporter : Exporter {
    /**
     * Export the given configuration to a PNG file.
     * @param project The project to export.
     * @param exportConfig The configuration for the export.
     */
    override fun export(project: Project, exportConfig: Map<String, Any>) {
        TODO("Not yet implemented")
    }
    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields that can be used in the export configuration.
     */
    override fun getFields(): List<NamedField> {
        TODO("Not yet implemented")
    }
}
