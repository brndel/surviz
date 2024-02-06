package data.io.exporter.result.errors

class ImageSizeExportWarning(
    val neededWidth: Int,
    blockId: Int? = null,
    situationId: Int? = null,
    optionId: Int? = null
) :
    ExportWarning(blockId, situationId, optionId)