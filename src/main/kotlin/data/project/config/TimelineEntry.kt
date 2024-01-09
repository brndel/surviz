package data.project.config

import androidx.compose.runtime.MutableState

/**
 * This class represents a single timeline entry of the timeline of a situation.
 * @param icon the icon that is displayed above this entry in the timeline
 * @param column the column of the Ngene file that contains the value of this timeline entry
 * @param lineType the type of line that is used to display the timeline entry
 */
class TimelineEntry constructor(
        var icon : MutableState<String?>,
        var column : MutableState<String>,
        var lineType : MutableState<LineType>
) {
}