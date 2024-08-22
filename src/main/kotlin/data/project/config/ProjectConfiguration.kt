package data.project.config

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import data.generator.resources.ImageConfig
import data.project.config.columns.SingleValueColumn
import java.util.*

/**
 * This class represents the configuration of a project.
 * A project configuration consists of the specific configurations and sequence of single values,
 * as well as the configuration details related to the situations of the project.
 * @param singleValueConfig the configuration of a single value. Each SingleValueConfig is assigned a UUID. The Order of UUID's is stored in a list.
 * @param singleValueConfigOrder the list of UUID's that stores the order.
 * @param optionConfig the configuration of situations. Each situation is assigned a name.
 */
data class ProjectConfiguration(
    private val singleValueConfigOrder: SnapshotStateList<UUID> = mutableStateListOf(),
    private val singleValueConfig: SnapshotStateMap<UUID, SingleValueConfig> = mutableStateMapOf(),
    private val optionConfig: SnapshotStateMap<String, OptionConfig> = mutableStateMapOf(),
    val imageConfig: ImageConfig = ImageConfig.loadFromProperties(),
    var blockConfigs: SnapshotStateMap<Int, BlockConfig>? = null,
) {
    /**
     * This method adds a single value to the project.
     */
    fun addSingleValue() {
        val newSingleValue = SingleValueConfig()
        val newUUID = UUID.randomUUID()
        singleValueConfig[newUUID] = newSingleValue
        singleValueConfigOrder.add(newUUID)
    }

    /**
     * This method removes the given single value from the project.
     * @param id the UUID of the single value to be removed
     */
    fun removeSingleValue(id: UUID) {
        singleValueConfig.remove(id)
        singleValueConfigOrder.remove(id)
    }

    /**
     * This method swaps the order of two given single values, if the two index exist in singleValueConfigOrder.
     * @param indexA the index of the first single value to be swapped
     * @param indexB the index of the second single value to be swapped
     */
    fun swapSingleValueOrder(indexA: Int, indexB: Int) {
        if (indexA >= 0 && indexB >= 0 && indexA < singleValueConfigOrder.size && indexB < singleValueConfigOrder.size) {
            val temp = singleValueConfigOrder[indexA]
            singleValueConfigOrder[indexA] = singleValueConfigOrder[indexB]
            singleValueConfigOrder[indexB] = temp
        }
    }

    /**
     * This method returns the single value configuration.
     * @return the single value configuration of this project
     */
    fun getSingleValues(): SnapshotStateMap<UUID, SingleValueConfig> {
        return singleValueConfig
    }

    /**
     * This method returns the configuration of situations.
     * @return the situation configuration of this project
     */
    fun getOptionConfig(): SnapshotStateMap<String, OptionConfig> {
        return optionConfig
    }

    /**
     * This method returns the situation config with the given String key or maps a new SituationConfig to the given key
     * if the key is not yet a key.
     * @param name the key to the situation config
     * @return the situation configuration with the given key
     */
    fun getOptionConfig(name: String): OptionConfig {
        return optionConfig.getOrPut(name) {
            val config = OptionConfig()
            config.addTimelineEntry()

            config
        }
    }

    /**
     * This method returns the order of single values of a project
     * @return the order of single values
     */
    fun getSingleValueConfigOrder(): SnapshotStateList<UUID> {
        return singleValueConfigOrder
    }

    /**
     * Sets the specified SingleValueColumn to the SingleValue with the given id for all situations
     * @param column the specified SingleValueColumn
     * @param id the UUID of the SingleValue
     */
    fun setAllSituationColumns(column: SingleValueColumn, id: UUID) {
        for (sit in optionConfig.values) {
            sit.singleValueColumns[id] = column
        }
    }

    companion object {
        val serializer = JsonSerializer<ProjectConfiguration> { value, _, ctx ->
            val obj = JsonObject()

            obj.add("singleValueConfigOrder", ctx.serialize(value.singleValueConfigOrder))
            obj.add("singleValueConfig", ctx.serialize(value.singleValueConfig))
            obj.add("optionConfig", ctx.serialize(value.optionConfig))
            obj.add("imageConfig", ctx.serialize(value.imageConfig))
            obj.add("blockConfigs", ctx.serialize(value.blockConfigs))

            obj
        }

        val deserializer = JsonDeserializer { element, _, ctx ->
            val obj = element.asJsonObject

            val singleValueConfigOrder = mutableStateListOf<UUID>()
            for (elem in obj.get("singleValueConfigOrder").asJsonArray) {
                singleValueConfigOrder.add(ctx.deserialize(elem, UUID::class.java))
            }

            val singleValueConfig = mutableStateMapOf<UUID, SingleValueConfig>()
            for ((keyElem, entryElem) in obj.get("singleValueConfig").asJsonObject.entrySet()) {
                val key = ctx.deserialize<UUID>(JsonPrimitive(keyElem), UUID::class.java)
                val entry = ctx.deserialize<SingleValueConfig>(entryElem, SingleValueConfig::class.java)

                singleValueConfig[key] = entry
            }

            val optionConfig = mutableStateMapOf<String, OptionConfig>()
            for ((key, entryElem) in obj.get("optionConfig").asJsonObject.entrySet()) {
                val entry = ctx.deserialize<OptionConfig>(entryElem, OptionConfig::class.java)

                optionConfig[key] = entry
            }

            val imageConfig = ctx.deserialize<ImageConfig>(obj.get("imageConfig"), ImageConfig::class.java)

            val blockConfigs = mutableStateMapOf<Int, BlockConfig>()
            for ((key, entryElem) in obj.get("blockConfigs").asJsonObject.entrySet()) {
                val entry = ctx.deserialize<BlockConfig>(entryElem, BlockConfig::class.java)

                blockConfigs[key.toInt()] = entry
            }


            ProjectConfiguration(
                singleValueConfigOrder,
                singleValueConfig,
                optionConfig,
                imageConfig,
                blockConfigs
            )
        }
    }
}