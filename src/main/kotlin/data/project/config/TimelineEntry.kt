package data.project.config

import androidx.compose.runtime.MutableState

class TimelineEntry constructor(
        var icon : MutableState<String?>,
        var column : MutableState<String>,
        var lineType : MutableState<LineType>
) {
}