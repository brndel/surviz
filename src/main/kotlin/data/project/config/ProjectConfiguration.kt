package data.project.config

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import java.util.UUID

/**
 * This class represents a project configuration.
 * A project configuration consists of the specific configurations and sequence of single values,
 * as well as the configuration details related to the situations of the project.
 * Each configuration of a single value is stored as a SingleValueConfig
 * Each SingleValueConfig is assigned a UUID. The Order of UUID's is stored in a List.
 * Each configuration of a situation is stored as a SituationConfig.
 * Each SituationConfig is assigned a name.
 */
class ProjectConfiguration constructor(
        private var singleValueConfigOrder: SnapshotStateList<UUID>,
        private var singleValueConfig: SnapshotStateMap<UUID, SingleValueConfig>,
        private var situationConfig: SnapshotStateMap<String, SituationConfig>
)

{
        fun addSingleValue(){

        }
        fun removeSingleValue(id: UUID){

        }
        fun swapSingleValueOrder(indexA: Int, indexB: Int){

        }
        fun getSingleValues(): SnapshotStateMap<UUID, SingleValueConfig>{
                return singleValueConfig
        }
        fun getSituationConfig(): SnapshotStateMap<String, SituationConfig>{
                return situationConfig
        }
}