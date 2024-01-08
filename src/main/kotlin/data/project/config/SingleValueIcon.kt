package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

class SingleValueIcon constructor(
    var baseIcon: MutableState<String?>,
    var levels: SnapshotStateList<SingleValueIconLevel>
) {
    fun getIcon(value: String): String{
        return ""
    }
}