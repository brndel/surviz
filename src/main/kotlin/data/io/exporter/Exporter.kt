package data.io.exporter

import data.io.utils.result.ExportResult
import data.project.Project
import data.resources.fields.NamedField
import java.nio.file.Path

/**
 * Defines the contract for data exporters (e.g., PNG, HTML).
 *
 * Implementations should perform the export according to exportConfig and return an ExportResult.
 * The exportConfig map contains exporter-specific keys (documented by each exporter).
 */
interface Exporter {
    /**
     * Export the given project using exporter-specific options.
     *
     * @param project The project to export.
     * @param exportConfig Map with exporter-specific configuration keys and values.
     * @param onPathSelected Optional callback invoked if the exporter determines a filesystem
     *                       path (e.g., when prompting the user). Receives the selected Path.
     * @return an ExportResult describing success, warnings or errors.
     */
    fun export(project: Project, exportConfig: Map<String, Any>, onPathSelected: ((Path) -> Unit)?): ExportResult

    /**
     * Returns the UI fields used to configure the exporter.
     * @return list of NamedField describing available configuration options
     */
    fun getFields(): List<NamedField>

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