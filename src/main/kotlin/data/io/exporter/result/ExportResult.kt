package data.io.exporter.result

import data.io.exporter.result.errors.ExportWarning
import data.io.exporter.result.errors.ImageSizeExportWarning
import data.io.exporter.result.report.ExportReport
import data.io.exporter.result.report.ImageSizeExportReport
import ui.Labels

data class ExportResult(val errors: List<ExportWarning>) {
    fun getReportList(): List<ExportReport> {
        val resultList = ArrayList<ExportReport>()

        var maxWidth = 0
        var widthId = OptionId()

        for (error in errors) {
            if (error is ImageSizeExportWarning) {
                if (error.neededWidth > maxWidth) {
                    maxWidth = error.neededWidth
                    widthId = error.id
                }
            }
        }

        if(maxWidth > 0) {
            resultList.add(ImageSizeExportReport(widthId, Labels.NEEDED_WIDTH, maxWidth))
        }
        return resultList
    }
}
