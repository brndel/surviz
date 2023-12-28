package data.project.config

import data.project.config.columns.SingleValueColumn
import java.awt.Color
import java.util.UUID

class SituationConfig constructor(
        val name : String,
        val color : Color,
        val singleValueColumns : Map<UUID, SingleValueColumn>,
        timeline : List<TimelineEntry>
) {
}