package data.io.exporter.result.report

import data.io.exporter.result.OptionId
import data.project.Project

class ImageSizeExportReport(id: OptionId, label: String, info: Int): ExportReport(id, label, info, "px") {

    override fun applyFix(project: Project) {
        val width = info.toString().toInt()
        project.configuration.imageConfig.width.value = width
    }
}