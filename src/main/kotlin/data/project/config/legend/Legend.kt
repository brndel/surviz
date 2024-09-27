package data.project.config.legend

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.useResource
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

import ui.fields.fromHex
import ui.fields.toHex
import java.util.Properties

/**
 * Legend holds configuration for the legend and the individual entries
 *
 * @property entries list of legend entries
 * @property color color icons and text will be drawn in
 * @property drawDivider whether to draw a divider between legend entries
 * @property segmentPadding padding between the color icon and the text
 * @constructor Create empty Legend
 */
data class Legend(
    val entries: SnapshotStateList<LegendEntry> = mutableStateListOf(),
    val color: MutableState<Color> = mutableStateOf(Color.Black),
    val drawDivider: MutableState<Boolean> = mutableStateOf(true),
    val segmentPadding: MutableIntState,
) {

    fun addEntry() {
        entries.add(LegendEntry())
    }

    fun removeEntry(entry: LegendEntry) {
        entries.remove(entry)
    }

    fun swapEntryOrder(indexA: Int, indexB: Int) {
        if (indexA >= 0 && indexB >= 0 && indexA < entries.size && indexB < entries.size) {
            val temp = entries[indexA]
            entries[indexA] = entries[indexB]
            entries[indexB] = temp
        }
    }

    companion object {
        fun loadFromProperties(): Legend {
            val properties = Properties()
            useResource("config/image_generator.properties") {
                properties.load(it)
            }
            val segmentPadding =
                mutableIntStateOf(properties.getProperty("legend_segment_padding").toInt())

            return Legend(segmentPadding = segmentPadding)
        }

        val serializer = JsonSerializer<Legend> { value, _, ctx ->
            val obj = JsonObject()
            obj.add("entries", ctx.serialize(value.entries))
            obj.add("color", ctx.serialize(value.color.value.toHex()))
            obj.addProperty("drawDivider", value.drawDivider.value)
            obj.addProperty("segmentPadding", value.segmentPadding.value)
            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject
            val entries = mutableStateListOf<LegendEntry>()
            for (entry in obj.get("entries").asJsonArray) {
                val entryData = ctx.deserialize<LegendEntry>(entry, LegendEntry::class.java)
                entries.add(entryData)
            }
            val color = Color.fromHex(obj.get("color").asString)!!
            val drawDivider = obj.get("drawDivider").asBoolean
            val segmentPadding = obj.get("segmentPadding").asInt
            Legend(
                entries,
                mutableStateOf(color),
                mutableStateOf(drawDivider),
                mutableIntStateOf(segmentPadding)
            )
        }
    }
}
