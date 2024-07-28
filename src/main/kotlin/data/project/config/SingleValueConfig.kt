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
    val prefix: MutableState<String> = mutableStateOf(""),
    val unit: MutableState<String> = mutableStateOf(""),
    val columnScheme: MutableState<String> = mutableStateOf(""),
    val icon: SingleValueIcon = SingleValueIcon(),
    val showDecimal: MutableState<Boolean> = mutableStateOf(false),
    val isDummy: MutableState<Boolean> = mutableStateOf(false),
    val dummies: SingleValueDummyMap = SingleValueDummyMap(),
) {
    companion object {
        val serializer = JsonSerializer<SingleValueConfig> { value, _, ctx ->
            val obj = JsonObject()

            obj.addProperty("prefix", value.prefix.value)
            obj.addProperty("unit", value.unit.value)
            obj.addProperty("columnScheme", value.columnScheme.value)
            obj.add("icon", ctx.serialize(value.icon))
            obj.addProperty("showDecimal", value.showDecimal.value)
            obj.addProperty("isDummy", value.isDummy.value)
            obj.add("dummyMap", ctx.serialize(value.dummies))

            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject

            val prefix = obj.get("prefix").asString
            val unit = obj.get("unit").asString
            val columnScheme = obj.get("columnScheme").asString
            val icon =
                ctx.deserialize<SingleValueIcon>(obj.get("icon"), SingleValueIcon::class.java)
            val showDecimal = obj.get("showDecimal").asBoolean
            val isDummy = obj.get("isDummy").asBoolean
            val dummies = ctx.deserialize<SingleValueDummyMap>(
                obj.get("dummies"),
                SingleValueDummyMap::class.java
            )

            SingleValueConfig(
                mutableStateOf(prefix),
                mutableStateOf(unit),
                mutableStateOf(columnScheme),
                icon,
                mutableStateOf(showDecimal),
                mutableStateOf(isDummy),
                dummies,
            )
        }
    }
}