package data.io.utils.result.report

import ui.Labels

class IllegalCharacterExportReport(private val char: String):ExportReport(label = Labels.ILLEGAL_CHAR, info = char)