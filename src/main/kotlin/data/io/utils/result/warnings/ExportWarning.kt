package data.io.utils.result.warnings

import data.io.utils.OptionId

/**
 * Export warning describes any issue that occurs while exporting.
 * These Warnings can be used to generate export reports.
 *
 * @property blockId ID of the block the problem occurred
 * @property situationId ID of the situation the problem occurred
 * @property optionId ID of the option the problem occurred
 * @constructor Create empty Export warning with given ids
 */
abstract class ExportWarning(
    private val blockId: Int? = null,
    private val situationId: Int? = null,
    private val optionId: String? = null,
    val message: String? = null
)  {
    val id = blockId?.let { OptionId(it, situationId, optionId) }
}
