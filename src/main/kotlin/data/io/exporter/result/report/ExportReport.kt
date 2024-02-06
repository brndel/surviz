package data.io.exporter.result.report

import data.io.exporter.result.OptionId
import data.project.Project

abstract class ExportReport(
    val id: OptionId,
    val label: String,
    val info: Any,
    val unit: String = ""
) {
    abstract fun applyFix(project: Project)

}


