package data.io.utils.result.warnings

/**
 * Image size export warning
 *
 * @property neededWidth the width the image would need to contain all content without getting cut off
 * @constructor create warning with IDs and needed with of the image
 *
 * @param blockId ID of the block the problem occurred
 * @param situationId ID of the situation the problem occurred
 * @param optionName ID of the option the problem occurred
 */
class ImageSizeExportWarning(
    val neededWidth: Int,
    blockId: Int,
    situationId: Int,
    optionName: String? = null
) :
    ExportWarning(blockId, situationId, optionName)