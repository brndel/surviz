package data.io.exporter

import data.io.utils.result.ExportResult
import data.project.Project
import data.resources.fields.NamedField

/**
 * This class implements the [Exporter] interface and exports the project to an HTML file.
 */
object HtmlExporter : Exporter {
    /**
     * This method exports the project to an HTML file.
     *
     * @param project The project to export.
     * @param exportConfig The export configuration.
     */
    override fun export(project: Project, exportConfig: Map<String, Any>): ExportResult {
        TODO("Not yet implemented")
    }

    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields of the exporter.
     */
    override fun getFields(project: Project): List<NamedField> {
        TODO("Not yet implemented")
    }
}
