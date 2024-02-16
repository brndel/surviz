package data.io.utils.result.report.generator

import data.io.utils.result.report.ExportReport
import data.io.utils.result.report.InvalidSituationExportReport
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.InvalidSituationWarning

object InvalidSituationReportGenerator: ReportGenerator{
    override fun generateReport(warnings: List<ExportWarning>): ExportReport? {
        for (warning in warnings) {
            if (warning is InvalidSituationWarning) {
                return warning.id?.let { InvalidSituationExportReport(it) }
            }
        }
        return null
    }

}