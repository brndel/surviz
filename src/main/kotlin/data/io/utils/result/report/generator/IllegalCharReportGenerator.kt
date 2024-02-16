package data.io.utils.result.report.generator

import data.io.utils.result.report.ExportReport
import data.io.utils.result.report.IllegalCharacterExportReport
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.IllegalCharacterWarning

object IllegalCharReportGenerator: ReportGenerator {
    override fun generateReport(warnings: List<ExportWarning>): ExportReport? {
        val illegalCharSet = hashSetOf<String>()

        for (warning in warnings){
            if (warning is IllegalCharacterWarning) {
                illegalCharSet.add(warning.char)
            }
        }
        if (illegalCharSet.isEmpty()) return null
        return IllegalCharacterExportReport(illegalCharSet.joinToString())
    }
}