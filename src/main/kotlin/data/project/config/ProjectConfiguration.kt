package data.project.config

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import data.generator.resources.ImageConfig
import data.project.config.columns.SchemeColumns
import java.util.UUID

/**
 * This class represents the configuration of a project.
 * A project configuration consists of the specific configurations and sequence of single values,
 * as well as the configuration details related to the situations of the project.
 * @param singleValueConfig the configuration of a single value. Each SingleValueConfig is assigned a UUID. The Order of UUID's is stored in a list.
 * @param singleValueConfigOrder the list of UUID's that stores the order.
 * @param situationConfig the configuration of situations. Each situation is assigned a name.
 */
data class ProjectConfiguration(
    private val singleValueConfigOrder: SnapshotStateList<UUID> = mutableStateListOf(),
    private val singleValueConfig: SnapshotStateMap<UUID, SingleValueConfig> = mutableStateMapOf(),
    private val situationConfig: SnapshotStateMap<String, SituationConfig> = mutableStateMapOf(),
    val imageConfig: ImageConfig = ImageConfig.loadFromProperties()
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
        if(indexA >= 0 && indexB >= 0 && indexA < singleValueConfigOrder.size && indexB < singleValueConfigOrder.size) {
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
    fun getSituationConfig(): SnapshotStateMap<String, SituationConfig> {
        return situationConfig
    }

    fun getSituationConfig(name: String): SituationConfig {
        return situationConfig.getOrPut(name) { SituationConfig() }
    }

    fun getSingleValueConfigOrder(): SnapshotStateList<UUID> {
        return singleValueConfigOrder
    }
}