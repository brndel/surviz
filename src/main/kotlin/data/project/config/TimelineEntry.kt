package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import data.generator.resources.LineType

/**
 * This class represents a single timeline entry of the timeline of a situation.
 * @param icon the path of the icon that will be displayed above this entry in the timeline
 * @param column the column of the Ngene file that contains the value of this timeline entry
 * @param lineType the type of line that will be used to display the timeline entry
 */
data class TimelineEntry(
    val icon: MutableState<String?>,
    val column: MutableState<String>,
    val lineType: MutableState<LineType>,
    val showChangeOvers: MutableState<Boolean> = mutableStateOf(false),
    val changeOverColumn: MutableState<String>
) {
    companion object {
        val serializer = JsonSerializer<TimelineEntry> { value, _, _ ->
            val obj = JsonObject()

            obj.addProperty("icon", value.icon.value)
            obj.addProperty("column", value.column.value)
            obj.addProperty("lineType", value.lineType.value.name)
            obj.addProperty("showChangeOvers", value.showChangeOvers.value)
            obj.addProperty("changeOverColumn", value.changeOverColumn.value)

            obj
        }

        val deserializer = JsonDeserializer<TimelineEntry> { element, _, _ ->
            val obj = element.asJsonObject

            val icon = if (obj.has("icon")) obj.get("icon").asString else null
            val column = obj.get("column").asString
            val lineType = LineType.valueOf(obj.get("lineType").asString)
            val showChangeOvers = obj.get("showChangeOvers").asBoolean
            val changeOverColumn = obj.get("changeOverColumn").asString

            TimelineEntry(
                mutableStateOf(icon),
                mutableStateOf(column),
                mutableStateOf(lineType),
                mutableStateOf(showChangeOvers),
                mutableStateOf(changeOverColumn)
            )
        }
    }
}