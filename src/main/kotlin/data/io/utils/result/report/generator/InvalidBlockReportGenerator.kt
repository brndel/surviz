package data.io.utils.result.report.generator

import data.io.utils.result.report.ExportReport
import data.io.utils.result.report.InvalidBlockExportReport
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.InvalidBlockWarning

object InvalidBlockReportGenerator: ReportGenerator {
    override fun generateReport(warnings: List<ExportWarning>): ExportReport? {
        for (warning in warnings) {
            if (warning is InvalidBlockWarning) {
                return warning.id?.let { InvalidBlockExportReport(it) }
            }
        }
        return null
    }
}