package data.io.exporter.resources

import data.io.exporter.resources.errors.ExportError

data class ExportResult(val errors: List<ExportError>)
