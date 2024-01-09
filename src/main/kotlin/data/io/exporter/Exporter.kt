package data.io.exporter

import data.project.config.ProjectConfiguration
import data.resources.fields.NamedField

/**
 * This interface describes the necessary methods for an exporter.
 */
interface Exporter {
    /**
     * This method exports the data.
     * @param configuration The project configuration.
     * @param exportConfig The export configuration.
     */
    fun export(configuration: ProjectConfiguration, exportConfig: Map<String, Any>)
    /**
     * This method returns the fields that can be exported.
     * @return The fields that can be exported.
     */
    fun getFields():List<NamedField>
}