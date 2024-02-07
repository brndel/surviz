package data.io.utils.result

import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.report.ExportReport
import data.io.utils.result.report.generator.ReportGeneratorVariant

/**
 * Export result contains all the [ExportWarning]s that occurred during exporting.
 *
 * @property warnings warnings that occurred during exporting
 * @constructor Create empty Export result
 */
data class ExportResult(val warnings: List<ExportWarning>) {

    /**
     * Get a list of [ExportReport]s that can be displayed in the UI
     *
     * @return list of all reports
     */
    fun getReportList(): List<ExportReport> {
        val resultList = ArrayList<ExportReport>()
        for (generator in ReportGeneratorVariant.entries) {
            generator.getGenerator().generateReport(warnings)?.let { resultList.add(it) }
        }
        return resultList
    }
}
