package data.io.utils.result.report.generator

import data.io.utils.result.report.ExportReport
import data.io.utils.result.warnings.ExportWarning

/**
 * Report generator generates a [ExportReport] from a given list of [ExportWarning]s
 *
 * @constructor Create empty Report generator
 */
interface ReportGenerator {

    /**
     * Generate report from the given list of warnings.
     *
     * @param warnings all the warnings the report should take into consideration.
     * @return report if something was found for the specific implementation of [ReportGenerator]
     */
    fun generateReport(warnings: List<ExportWarning>): ExportReport?
}