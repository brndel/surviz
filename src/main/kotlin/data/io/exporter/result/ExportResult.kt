package data.io.exporter.result

import data.io.exporter.result.errors.ExportError

data class ExportResult(val errors: List<ExportError>)
