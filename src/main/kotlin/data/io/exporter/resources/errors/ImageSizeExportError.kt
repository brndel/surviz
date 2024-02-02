package data.io.exporter.resources.errors

data class ImageSizeExportError(val neededWidth: Int, val blockId: Int? = null, val situationId: Int? = null, val optionId: Int? = null): ExportError()