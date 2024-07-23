package data.project.config.single_value

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

class Divider(
    val height: MutableState<Float> = mutableStateOf(80f),
    val widthScale: MutableState<Float> = mutableStateOf(0.5f)
) : SingleValueItem() {
    companion object {
        val serializer = JsonSerializer<Divider> { value, _, ctx ->
            val obj = JsonObject()

            obj.addProperty("height", value.height.value)
            obj.addProperty("widthScale", value.widthScale.value)

            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject

            val height = obj.get("height").asFloat
            val widthScale = obj.get("widthScale").asFloat

            Divider(
                mutableStateOf(height),
                mutableStateOf(widthScale)
            )
        }
    }

    override fun getWidthScale(): Float {
        return widthScale.value
    }
}
