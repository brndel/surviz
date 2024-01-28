package data.project.config

import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import java.util.UUID

/**
 * This class represents the configuration of a project.
 * A project configuration consists of the specific configurations and sequence of single values,
 * as well as the configuration details related to the situations of the project.
 * @param singleValueConfig the configuration of a single value. Each SingleValueConfig is assigned a UUID. The Order of UUID's is stored in a list.
 * @param singleValueConfigOrder the list of UUID's that stores the order.
 * @param situationConfig the configuration of situations. Each situation is assigned a name.
 */
class ProjectConfiguration {
    private val singleValueConfigOrder: SnapshotStateList<UUID>
    private val singleValueConfig: SnapshotStateMap<UUID, SingleValueConfig>
    private val situationConfig: SnapshotStateMap<String, SituationConfig>

    init {
        singleValueConfigOrder = SnapshotStateList<UUID>()
        singleValueConfig = SnapshotStateMap<UUID, SingleValueConfig>()
        situationConfig = SnapshotStateMap<String, SituationConfig>()
    }

    /**
     * This method adds a single value to the project.
     */
    fun addSingleValue() {
        val newIcon: SingleValueIcon = SingleValueIcon(SnapshotStateList<SingleValueIconLevel?>())
        val newSingleValue: SingleValueConfig = SingleValueConfig(mutableStateOf(""), mutableStateOf(""), newIcon)
        val newUUID: UUID = UUID.randomUUID()
        singleValueConfig.put(newUUID, newSingleValue)
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

    fun getSingleValueConfigOrder(): SnapshotStateList<UUID> {
        return singleValueConfigOrder
    }
}