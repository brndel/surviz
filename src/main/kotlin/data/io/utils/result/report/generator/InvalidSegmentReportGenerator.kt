package data.io.utils.result.report.generator

import data.io.utils.result.report.ExportReport
import data.io.utils.result.report.InvalidSegmentExportReport
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.InvalidSegmentWarning

object InvalidSegmentReportGenerator: ReportGenerator{
    override fun generateReport(warnings: List<ExportWarning>): ExportReport? {
        for (warning in warnings) {
            if (warning is InvalidSegmentWarning) {
                return warning.message?.let { InvalidSegmentExportReport(it) }
            }
        }
        return null
    }
}
