package data.io.exporter

import data.project.Project
import data.project.config.ProjectConfiguration
import data.resources.fields.NamedField

/**
 * This interface describes the necessary methods for an exporter.
 */
interface Exporter {
    /**
     * This method exports the data.
     * @param project The project.
     * @param exportConfig The export configuration.
     */
    fun export(project: Project, exportConfig: Map<String, Any>)

    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields that can be exported.
     */
    fun getFields(): List<NamedField>
}