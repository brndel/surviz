package data.io.exporter.result.errors

import data.io.exporter.result.OptionId

abstract class ExportWarning(private val blockId: Int? = null, private  val situationId: Int? = null, private  val optionId: Int? = null) {
    val id = OptionId(blockId, situationId, optionId)
}
