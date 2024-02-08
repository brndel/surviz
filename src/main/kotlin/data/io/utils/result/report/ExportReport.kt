package data.io.utils.result.report

import data.io.utils.OptionId
import data.project.Project

/**
 * Export report is used for showing the user problems that occurred during the exporting in a compact manner.
 * Works best if the "worst" warning is used to construct the report
 *
 * @property id ID where the problem occurred
 * @property label description of the report
 * @property info any further information that the report needs to hold
 * @property unit possible unit
 * @constructor Create empty Export report
 */
abstract class ExportReport(
    val id: OptionId,
    val label: String,
    val info: Any,
    val unit: String = ""
) {

    /**
     * Fix the problem that is reported by the report.
     * Does nothing by default, implementations of [ExportReport] need to override to actually do something.
     *
     * @param project the project the fix should be made in
     */
    open fun applyFix(project: Project) {}
}


