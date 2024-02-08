package data.io.exporter

import data.io.utils.result.ExportResult
import data.project.Project
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
    fun export(project: Project, exportConfig: Map<String, Any>): ExportResult

    /**
     * This method returns the fields that the UI uses to configure the export.
     * @param project project to get the fields for
     * * @return The fields that can be exported.
     */
    fun getFields(project: Project): List<NamedField>

    companion object {
        fun getNameFromScheme(template: String, vararg values: Pair<String, String>): String {
            var result = template
            values.forEach { (placeholder, replacement) ->
                result = result.replace("\$$placeholder\$", replacement)
            }
            return result
        }
    }
}