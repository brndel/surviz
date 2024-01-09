package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * This class represents a single value icon.
 * A singular value icon may comprise a solitary icon or a list of icon levels.
 * the icon levels consist of multiple icons, that will be displayed depending on the value of the single value.
 * @param baseicon the solitary base icon that will be displayed
 * @param levels the list of icons
 */
class SingleValueIcon constructor(
    var baseIcon: MutableState<String?>,
    var levels: SnapshotStateList<SingleValueIconLevel>
) {
    /**
     * This method returns the icon.
     * @param value the value of the single value
     * @return the icon
     */
    fun getIcon(value: String): String{
        return ""
    }
}