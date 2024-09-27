package data.project.config.legend

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

/**
 * Legend entry holds the configuration for a single entry in the legend
 *
 * @property abbreviation short name for the entry
 * @property path path to the icon
 * @property description description of the entry
 * @property isIcon whether the entry is an icon or text
 * @constructor Create empty Legend entry
 */
data class LegendEntry(
    val abbreviation: MutableState<String> = mutableStateOf(""),
    val path: MutableState<String?> = mutableStateOf(null),
    val description: MutableState<String> = mutableStateOf(""),
    val isIcon: MutableState<Boolean> = mutableStateOf(true)
) {
    companion object {
        val serializer = JsonSerializer<LegendEntry> { value, _, _ ->
            val obj = JsonObject()
            obj.addProperty("abbreviation", value.abbreviation.value)
            obj.addProperty("path", value.path.value)
            obj.addProperty("description", value.description.value)
            obj.addProperty("isIcon", value.isIcon.value)
            obj
        }

        val deserializer = JsonDeserializer { element, _, _ ->
            val obj = element.asJsonObject
            LegendEntry(
                mutableStateOf(obj.get("abbreviation").asString),
                mutableStateOf(obj.get("path")?.asString),
                mutableStateOf(obj.get("description").asString),
                mutableStateOf(obj.get("isIcon").asBoolean)
            )
        }
    }
}