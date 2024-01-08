package data.project.config

import androidx.compose.runtime.MutableState

class SingleValueIconLevel constructor(
        var Icon: MutableState<String?>,
        var lowerThreshold: MutableState<Double>
){
}