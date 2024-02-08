package data.project.config.columns

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializer
import data.project.config.SingleValueConfig
import data.project.config.SituationConfig
import data.project.data.SituationOption

/**
 * This interface represents a type of single value columns of the Ngene file.
 */
sealed class SingleValueColumn(
    val nameLabel: String,
    val descriptionLabel: String,
) {
    /**
     * This method returns the value of the column(s).
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situationOption the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s)
     */
    abstract fun getValue(
        singleValueConfig: SingleValueConfig,
        situationConfig: SituationConfig,
        situationOption: SituationOption
    ): Double

    companion object {
        val serializer = JsonSerializer<SingleValueColumn> { value, _, ctx ->
            val obj = JsonObject()

            when (value) {
                is ListColumns -> {
                    obj.addProperty("type", "ListColumns")
                    obj.add("columns", ctx.serialize(value.columns))
                }

                SchemeColumns -> obj.addProperty("type", "SchemeColumns")
                TimelineColumns -> obj.addProperty("type", "TimelineColumns")
                ZeroColumn -> obj.addProperty("type", "ZeroColumn")
            }

            obj
        }

        val deserializer = JsonDeserializer<SingleValueColumn> { element, _, ctx ->
            val obj = element.asJsonObject

            when (val type = obj.get("type").asString) {
                "ListColumns" -> {
                    val columns =
                        ctx.deserialize<SnapshotStateList<String>>(
                            obj.get("columns"),
                            SnapshotStateList::class.java
                        )

                    ListColumns(columns)
                }

                "SchemeColumns" -> SchemeColumns
                "TimelineColumns" -> TimelineColumns
                "ZeroColumn" -> ZeroColumn

                else -> throw JsonParseException("invalid SingleValueColumn type '$type'")
            }
        }
    }
}