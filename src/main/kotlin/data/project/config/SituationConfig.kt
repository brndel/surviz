package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import data.generator.resources.LineType
import data.project.config.columns.SchemeColumns

import data.project.config.columns.SingleValueColumn
import java.util.UUID

/**
 * This class represents the configuration of a situation.
 * @param name the name that is given to the situation
 * @param color the color of the situation
 * @param singleValueColumns the columns of the Ngene file that contain the information for this situation. The single value UUID's will be assigned to a type of SingleValueColumn, that stores the columns in the Ngene file that contain the information of this single value.
 * @param timeline the timeline entrys
 */
data class SituationConfig(
    val name: MutableState<String> = mutableStateOf(""),
    val color: MutableState<Color> = mutableStateOf(Color.Black),
    val singleValueColumns: SnapshotStateMap<UUID, SingleValueColumn> = mutableStateMapOf(),
    private val timeline: SnapshotStateList<TimelineEntry> = mutableStateListOf()
) {


    /**
     * this method adds a new timeline entry.
     */
    fun addTimelineEntry() {
        val newTimelineEntry = TimelineEntry(
            mutableStateOf(null), mutableStateOf(""), mutableStateOf(
                LineType.Solid
            )
        )
        timeline.add(newTimelineEntry)
    }

    /**
     * This method removes the given timeline entry.
     * @param timelineEntry the timeline entry to be removed
     */
    fun removeTimelineEntry(timelineEntry: TimelineEntry) {
        timeline.remove(timelineEntry)
    }

    /**
     * This method removes the given timeline entry.
     * @param index the index of the timeline entry to be removed
     */
    fun removeTimelineEntry(index: Int) {
        timeline.remove(timeline[index])
    }

    /**
     * This method swaps the order of two given timeline entrys.
     * @param indexA the index of the first timeline entry to be swapped
     * @param indexB the index of the second timeline entry to be swapped
     */
    fun swapTimelineOrder(indexA: Int, indexB: Int) {
        val temp = timeline[indexA]
        timeline[indexA] = timeline[indexB]
        timeline[indexB] = temp
    }

    /**
     * This method returns the timeline of this situation.
     * @return the timeline to be returned
     */
    fun getTimeline(): List<TimelineEntry> {
        return timeline
    }

    fun getColumns(id: UUID): SingleValueColumn {
        return singleValueColumns.getOrPut(id) { SchemeColumns }
    }

    companion object {
        val serializer = JsonSerializer<SituationConfig> { value, _, ctx ->
            val obj = JsonObject()

            obj.addProperty("name", value.name.value)
            obj.add("color", ctx.serialize(value.color.value))
            obj.add("singleValueColumns", ctx.serialize(value.singleValueColumns))
            obj.add("timeline", ctx.serialize(value.timeline))

            obj
        }

        val deserializer = JsonDeserializer<SituationConfig> { element, _, ctx ->
            println("deserializing SituationConfig $element")
            val obj = element.asJsonObject

            val name = obj.get("name").asString
            val color = ctx.deserialize<Color>(obj.get("color"), Color::class.java)

            val singleValueColumns = ctx.deserialize<SnapshotStateMap<UUID, SingleValueColumn>>(
                obj.get("singleValueColumns"),
                SnapshotStateMap::class.java
            )

            val timeline = mutableStateListOf<TimelineEntry>()

            val jsonTimeline = obj.get("timeline").asJsonArray

            for (entry in jsonTimeline) {
                val timelineEntry = ctx.deserialize<TimelineEntry>(entry, TimelineEntry::class.java)
                timeline.add(timelineEntry)
            }

            SituationConfig(
                mutableStateOf(name),
                mutableStateOf(color),
                singleValueColumns,
                timeline
            )
        }
    }
}