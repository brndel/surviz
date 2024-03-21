package data.generator.resources

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import ui.fields.fromHex
import ui.fields.toHex
import java.io.FileInputStream
import java.util.Properties

data class ImageConfig(
    val width: MutableIntState,
    val timelineScaling: MutableDoubleState,
    val backgroundColor: MutableState<Color>
) {
    companion object {
        fun loadFromProperties(): ImageConfig {
            val properties = Properties()
            properties.load(FileInputStream("src/main/resources/config/image_generator.properties"))
            val width = mutableIntStateOf(properties.getProperty("situation_default_width").toInt())
            val timelineScaling =
                mutableDoubleStateOf(properties.getProperty("timeline_default_scaling").toDouble())
            val colorHex = properties.getProperty("background_color")
            val color = mutableStateOf(Color.fromHex(colorHex)!!)

            return ImageConfig(width, timelineScaling, color)
        }

        val serializer = JsonSerializer<ImageConfig> { value, _, _ ->
            val obj = JsonObject()

            obj.addProperty("width", value.width.value)
            obj.addProperty("timelineScaling", value.timelineScaling.value)
            obj.addProperty("backgroundColor", value.backgroundColor.value.toHex())

            obj
        }

        val deserializer = JsonDeserializer<ImageConfig> { element, _, _ ->
            val obj = element.asJsonObject

            val width = obj.get("width").asInt
            val timelineScaling = obj.get("timelineScaling").asDouble
            val colorHex = obj.get("backgroundColor").asString
            val color = Color.fromHex(colorHex)!!
            println("loaded color $color")

            ImageConfig(
                mutableIntStateOf(width),
                mutableDoubleStateOf(timelineScaling),
                mutableStateOf(color)
            )
        }
    }
}

