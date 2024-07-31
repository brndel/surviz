package data.io.utils.result.report

import data.io.utils.OptionId
import ui.Labels

class InvalidBlockExportReport(id: OptionId): ExportReport(id, Labels.BLOCK_NOT_FOUND)