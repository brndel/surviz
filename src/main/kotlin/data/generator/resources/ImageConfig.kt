package data.generator.resources

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.useResource
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import ui.fields.fromHex
import ui.fields.toHex
import java.util.Properties

data class ImageConfig(
    val width: MutableIntState,
    val timelineScaling: MutableDoubleState,
    val backgroundColor: MutableState<Color>,
    val alpha: MutableFloatState,
    val singleValueSize: MutableIntState,
) {
    companion object {
        fun loadFromProperties(): ImageConfig {
            val properties = Properties()
            useResource("config/image_generator.properties") {
                properties.load(it)
            }
            val width = mutableIntStateOf(properties.getProperty("situation_default_width").toInt())
            val timelineScaling =
                mutableDoubleStateOf(properties.getProperty("timeline_default_scaling").toDouble())
            val colorHex = properties.getProperty("background_color")
            val color = mutableStateOf(Color.fromHex(colorHex)!!)
            val alpha = mutableFloatStateOf(properties.getProperty("single_value_alpha").toFloat())
            val singleValueSize = mutableIntStateOf(properties.getProperty("single_value_size").toInt())

            return ImageConfig(width, timelineScaling, color, alpha, singleValueSize)
        }

        val serializer = JsonSerializer<ImageConfig> { value, _, _ ->
            val obj = JsonObject()

            obj.addProperty("width", value.width.value)
            obj.addProperty("timelineScaling", value.timelineScaling.value)
            obj.addProperty("backgroundColor", value.backgroundColor.value.toHex())
            obj.addProperty("alpha", value.alpha.value)
            obj.addProperty("singleValueSize", value.singleValueSize.value)

            obj
        }

        val deserializer = JsonDeserializer<ImageConfig> { element, _, _ ->
            val obj = element.asJsonObject

            val width = obj.get("width").asInt
            val timelineScaling = obj.get("timelineScaling").asDouble
            val colorHex = obj.get("backgroundColor").asString
            val color = Color.fromHex(colorHex)!!
            val alpha = obj.get("alpha").asFloat
            val singleValueSize = obj.get("singleValueSize").asInt

            ImageConfig(
                mutableIntStateOf(width),
                mutableDoubleStateOf(timelineScaling),
                mutableStateOf(color),
                mutableFloatStateOf(alpha),
                mutableIntStateOf(singleValueSize)
            )
        }
    }
}

