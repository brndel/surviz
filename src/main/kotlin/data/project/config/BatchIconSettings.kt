package data.project.config

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

data class BatchIconSettings(
    val settings: SnapshotStateList<BatchIconSetting> = mutableStateListOf(),
    val width: MutableIntState = mutableIntStateOf(480),
    val height: MutableIntState = mutableIntStateOf(480),
) {
    fun addSetting() {
        settings.add(BatchIconSetting())
    }

    fun removeSetting(setting: BatchIconSetting) {
        settings.remove(setting)
    }

    companion object {
        val serializer =
            JsonSerializer<BatchIconSettings> { value, _, ctx ->
                val obj = JsonObject()

                obj.add("settings", ctx.serialize(value.settings))
                obj.addProperty("width", value.width.value)
                obj.addProperty("height", value.height.value)

                obj
            }

        val deserializer =
            JsonDeserializer { element, _, ctx ->
                val obj = element.asJsonObject

                val settings = mutableStateListOf<BatchIconSetting>()
                for (elem in obj.get("settings").asJsonArray) {
                    settings.add(ctx.deserialize(elem, BatchIconSetting::class.java))
                }
                val width = obj.get("width").asInt
                val height = obj.get("height").asInt

                BatchIconSettings(
                    settings,
                    mutableIntStateOf(width),
                    mutableIntStateOf(height),
                )
            }
    }
}
