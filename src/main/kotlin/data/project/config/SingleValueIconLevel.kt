package data.project.config

import androidx.compose.runtime.MutableState

/**
 * This class represents a single value icon that is part of an icon level list
 * @param icon the path of the icon that will be displayed
 * @param lowerThreshold the lower threshold of the single value that must be reached for the icon to be displayed
 */
class SingleValueIconLevel constructor(
        var icon: MutableState<String?>,
        var lowerThreshold: MutableState<Double>
){
}