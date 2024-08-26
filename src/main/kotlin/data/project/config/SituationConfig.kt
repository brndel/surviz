package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

/**
 * Situation config stores all overriding configurations for a situation
 *
 * @property overrideScale whether the scale is overridden
 * @property scale the overridden scale
 * @constructor Create empty Situation config
 */
data class SituationConfig(
    val overrideScale: MutableState<Boolean> = mutableStateOf(false),
    val scale: MutableState<Float> = mutableStateOf(8f)
)   {

    fun overrides() : Boolean = overrideScale.value
    companion object{
        val serializer = JsonSerializer<SituationConfig> { value, _, _ ->
            val obj = JsonObject()

            obj.addProperty("overrideScale", value.overrideScale.value)
            obj.addProperty("scale", value.scale.value)

            obj
        }

        val deserializer = JsonDeserializer { element, _, _ ->
            val obj = element.asJsonObject

            val overrideScale = obj.get("overrideScale").asBoolean
            val scale = obj.get("scale").asFloat

            SituationConfig(
                mutableStateOf(overrideScale),
                mutableStateOf(scale)
            )
        }
    }
}
