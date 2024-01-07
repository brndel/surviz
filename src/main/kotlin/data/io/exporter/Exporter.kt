package data.io.exporter

import data.project.config.ProjectConfiguration
import data.resources.fields.NamedField

interface Exporter {
    fun export(configuration: ProjectConfiguration, exportConfig: Map<String, Any>)
    fun getFields():List<NamedField>
}