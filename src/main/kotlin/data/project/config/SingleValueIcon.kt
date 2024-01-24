package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshots.SnapshotStateList

/**
 * This class represents a single value icon.
 * A single value icon may comprise a solitary icon or a list of icon levels.
 * @param levels the list of icons
 */
class SingleValueIcon constructor(
    var levels: SnapshotStateList<SingleValueIconLevel?>
) {
    /**
     * This method returns the icon that will be displayed with the given value.
     * @param value the value of the single value
     * @return the icon that will be displayed
     */
    fun getIcon(value: Double): String?{
        if(levels.isEmpty()){
            return null
        }
        var i: Int = levels.lastIndex
        while(i>0){
            val valueAsVal: Double = value
            val thresholdAsVal: Double = levels[i]?.lowerThreshold?.value ?: 0.0
            if(valueAsVal >= thresholdAsVal){
                return levels[i]?.icon?.value
            }
            i--
        }
        return levels[0]?.icon?.value
    }
}