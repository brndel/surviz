package data.io.utils.result.report.generator

import data.io.utils.OptionId
import data.io.utils.result.report.ExportReport
import data.io.utils.result.report.ImageSizeExportReport
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.ImageSizeExportWarning

/**
 * Image size report generator generates a report for the biggest needed width of an image.
 *
 * @constructor Create empty Image size report generator
 * @see ImageSizeExportWarning
 */
object ImageSizeReportGenerator : ReportGenerator {

    /**
     * Generate report searching for the biggest needed with of all [ExportWarning]s
     *
     * @param warnings all the warnings the report should take into consideration.
     * @return report containing the id and biggest needed with of the image generation,
     * return null if no [ImageSizeExportWarning] is found in the list of warnings.
     */
    override fun generateReport(warnings: List<ExportWarning>): ExportReport? {
        val maxSizeWarning = warnings.filterIsInstance<ImageSizeExportWarning>()
            .maxByOrNull { it.neededWidth }

        return maxSizeWarning?.let { it.id?.let { it1 -> ImageSizeExportReport(it1, it.neededWidth) } }
    }
}