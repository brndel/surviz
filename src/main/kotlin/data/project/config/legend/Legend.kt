package data.project.config.legend

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

data class Legend(val entries: SnapshotStateList<LegendEntry> = mutableStateListOf()) {

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
        val serializer = JsonSerializer<Legend> { value, _, ctx ->
            val obj = JsonObject()
            obj.add("entries", ctx.serialize(value.entries))
            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject
            val entries = mutableStateListOf<LegendEntry>()
            for (entry in obj.get("entries").asJsonArray) {
                val entryData = ctx.deserialize<LegendEntry>(entry, LegendEntry::class.java)
                entries.add(entryData)
            }
            Legend(entries)
        }
    }
}
