package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import ui.fields.fromHex
import ui.fields.toHex

data class BatchIconSetting(
    val prefix: MutableState<String> = mutableStateOf(""),
    val unit: MutableState<String> = mutableStateOf(""),
    val icon: SingleValueIcon = SingleValueIcon(),
    val showDecimal: MutableState<Boolean> = mutableStateOf(false),
    val color: MutableState<Color> = mutableStateOf(Color.Black),
    val values: SnapshotStateList<Double> = mutableStateListOf(1.0),
) {
    companion object {
        val serializer =
            JsonSerializer<BatchIconSetting> { value, _, ctx ->
                val obj = JsonObject()

                obj.addProperty("prefix", value.prefix.value)
                obj.addProperty("unit", value.unit.value)
                obj.add("icon", ctx.serialize(value.icon))
                obj.addProperty("showDecimal", value.showDecimal.value)
                obj.add("color", ctx.serialize(value.color.value.toHex()))
                obj.add("values", ctx.serialize(value.values))

                obj
            }

        val deserializer =
            JsonDeserializer { element, _, ctx ->
                val obj = element.asJsonObject

                val prefix = obj.get("prefix").asString
                val unit = obj.get("unit").asString
                val icon = ctx.deserialize<SingleValueIcon>(obj.get("icon"), SingleValueIcon::class.java)
                val showDecimal = obj.get("showDecimal").asBoolean
                val color = Color.fromHex(obj.get("color").asString)!!
                val values = mutableStateListOf<Double>()
                values.addAll(obj.get("values").asJsonArray.map { it.asDouble })

                BatchIconSetting(
                    mutableStateOf(prefix),
                    mutableStateOf(unit),
                    icon,
                    mutableStateOf(showDecimal),
                    mutableStateOf(color),
                    values,
                )
            }
    }
}
