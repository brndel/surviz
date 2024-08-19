package data.io.utils.result.report

import ui.Labels

class InvalidSegmentExportReport(segment: String): ExportReport(label = Labels.INVALID_SEGMENT, info = segment) {

}