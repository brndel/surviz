package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer

/**
 * This class represents a single value icon.
 * A single value icon may comprise a solitary icon or a list of icon levels.
 * @param levels the list of icons
 */
data class SingleValueIcon(
    val baseIcon: MutableState<String?> = mutableStateOf(null),
    val levels: SnapshotStateList<SingleValueIconLevel> = mutableStateListOf()
) {
    /**
     * This method returns the icon that will be displayed with the given value.
     * @param value the value of the single value
     * @return the icon that will be displayed
     */
    fun getIcon(value: Double): String? {
        var currentLevel: SingleValueIconLevel? = null
        for (level in levels) {
            if (level.lowerThreshold.value > 0 && level.lowerThreshold.value <= value && (currentLevel == null || level.lowerThreshold.value > currentLevel.lowerThreshold.value)) {
                currentLevel = level
            }
        }

        return (currentLevel?.icon ?: baseIcon).value
    }

    fun addLevel() {
        levels.add(SingleValueIconLevel())
    }

    fun removeLevel(level: SingleValueIconLevel) {
        levels.remove(level)
    }

    companion object {
        val serializer = JsonSerializer<SingleValueIcon> { value, _, ctx ->
            val obj = JsonObject()

            obj.addProperty("baseIcon", value.baseIcon.value)
            obj.add("levels", ctx.serialize(value.levels))

            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject

            val baseIcon = if (obj.has("baseIcon")) {
                obj.get("baseIcon").asString
            } else {
                null
            }

            val levels = mutableStateListOf<SingleValueIconLevel>()

            for (level in obj.get("levels").asJsonArray) {
                val iconLevel = ctx.deserialize<SingleValueIconLevel>(level, SingleValueIconLevel::class.java)
                levels.add(iconLevel)
            }

            SingleValueIcon(mutableStateOf(baseIcon), levels)
        }
    }
}