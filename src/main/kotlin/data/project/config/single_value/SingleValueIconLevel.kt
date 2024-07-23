package data.project.config.single_value

import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

/**
 * This class represents a single value icon that is part of an icon level list
 * @param icon the path of the icon that will be displayed
 * @param lowerThreshold the lower threshold of the single value that must be reached for the icon to be displayed
 */
data class SingleValueIconLevel(
    val icon: MutableState<String?> = mutableStateOf(null),
    val lowerThreshold: MutableDoubleState = mutableDoubleStateOf(0.0)
) {
    companion object {
        val serializer = JsonSerializer<SingleValueIconLevel> { value, _, _ ->
            val obj = JsonObject()

            obj.addProperty("icon", value.icon.value)
            obj.addProperty("lowerThreshold", value.lowerThreshold.value)

            obj
        }

        val deserializer = JsonDeserializer { json, _, _ ->
            val obj = json.asJsonObject

            SingleValueIconLevel(
                mutableStateOf(obj.get("icon").asString),
                mutableDoubleStateOf(obj.get("lowerThreshold").asDouble)
            )
        }
    }
}