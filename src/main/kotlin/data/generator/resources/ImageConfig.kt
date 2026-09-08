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

/**
 * ImageConfig is a data class that holds configuration settings for image generation.
 *
 * @property width The width of the generated image.
 * @property timelineScaling The scaling factor for the timeline in the image.
 * @property backgroundColor The background color of the generated image.
 * @property alpha The alpha transparency value for all drawn foreground elements in the image.
 * @property singleValueSize The size of single value icons in the image.
 */
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

        val serializer =
            JsonSerializer<ImageConfig> { value, _, _ ->
                val obj = JsonObject()

                obj.addProperty("width", value.width.value)
                obj.addProperty("timelineScaling", value.timelineScaling.value)
                obj.addProperty("backgroundColor", value.backgroundColor.value.toHex())
                obj.addProperty("alpha", value.alpha.value)
                obj.addProperty("singleValueSize", value.singleValueSize.value)

                obj
            }

        val deserializer =
            JsonDeserializer<ImageConfig> { element, _, _ ->
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
                    mutableIntStateOf(singleValueSize),
                )
            }
    }
}
