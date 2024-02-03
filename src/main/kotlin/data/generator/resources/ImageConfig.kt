package data.generator.resources

import androidx.compose.runtime.*
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import java.io.FileInputStream
import java.util.Properties

data class ImageConfig(
    val width: MutableIntState,
    val timelineScaling: MutableDoubleState
) {
    companion object {
        fun loadFromProperties(): ImageConfig {
            val properties = Properties()
            properties.load(FileInputStream("src/main/resources/config/image_generator.properties"))
            val width = mutableIntStateOf(properties.getProperty("situation_default_width").toInt())
            val timelineScaling = mutableDoubleStateOf(properties.getProperty("timeline_default_scaling").toDouble())

            return ImageConfig(width, timelineScaling)
        }

        val serializer = JsonSerializer<ImageConfig> { value, _, _ ->
            val obj = JsonObject()

            obj.addProperty("width", value.width.value)
            obj.addProperty("timelineScaling", value.timelineScaling.value)

            obj
        }

        val deserializer = JsonDeserializer<ImageConfig> { element, _, _ ->
            val obj = element.asJsonObject

            val width = obj.get("width").asInt
            val timelineScaling = obj.get("timelineScaling").asDouble

            ImageConfig(
                mutableIntStateOf(width),
                mutableDoubleStateOf(timelineScaling)
            )
        }
    }
}

