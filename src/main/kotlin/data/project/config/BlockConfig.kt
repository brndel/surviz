package data.project.config

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

class BlockConfig(
    val situationConfigs: SnapshotStateMap<Int, SituationConfig> = mutableStateMapOf()
) {
    companion object {
        val serializer = JsonSerializer<BlockConfig> { value, _, ctx ->
            val obj = JsonObject()

            obj.add("situationConfigs", ctx.serialize(value.situationConfigs))

            obj

        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject

            val situationConfigs = mutableStateMapOf<Int, SituationConfig>()
            for ((key, entryElem) in obj.get("situationConfigs").asJsonObject.entrySet()) {
                val situationConfig = ctx.deserialize<SituationConfig>(entryElem, SituationConfig::class.java)
                situationConfigs[key.toInt()] = situationConfig
            }

            BlockConfig(
                situationConfigs
            )
        }
    }
}