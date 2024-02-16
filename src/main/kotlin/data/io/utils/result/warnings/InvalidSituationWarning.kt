package data.io.utils.result.warnings

class InvalidSituationWarning(
    blockId: Int,
    situationId: Int,
) : ExportWarning(blockId, situationId) {
}