package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

/**
 * This class represents the configuration of a single value.
 * It stores all information about a single value.
 * @param unit the defined unit of the single value
 * @param columnScheme the defined column scheme that is used for finding the right Ngene columns of the single value in the class SchemeColumns.
 * @param icon The icon that is displayed for this single value
 */
data class SingleValueConfig(
    val unit: MutableState<String> = mutableStateOf(""),
    val columnScheme: MutableState<String> = mutableStateOf(""),
    val icon: SingleValueIcon = SingleValueIcon()
) {
    companion object {
        val serializer = JsonSerializer<SingleValueConfig> { value, _, ctx ->
            val obj = JsonObject()

            obj.addProperty("unit", value.unit.value)
            obj.addProperty("columnScheme", value.columnScheme.value)
            obj.add("icon", ctx.serialize(value.icon))

            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject

            val unit = obj.get("unit").asString
            val columnScheme = obj.get("columnScheme").asString
            val icon =
                ctx.deserialize<SingleValueIcon>(obj.get("icon"), SingleValueIcon::class.java)

            SingleValueConfig(
                mutableStateOf(unit),
                mutableStateOf(columnScheme),
                icon
            )
        }
    }
}