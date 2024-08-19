package data.io.utils.result.warnings

class InvalidSegmentWarning(segment: String): ExportWarning(message = segment) {
}