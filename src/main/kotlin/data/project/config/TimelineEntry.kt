package data.project.config

import androidx.compose.runtime.MutableState
import data.generator.resources.LineType

/**
 * This class represents a single timeline entry of the timeline of a situation.
 * @param icon the path of the icon that will be displayed above this entry in the timeline
 * @param column the column of the Ngene file that contains the value of this timeline entry
 * @param lineType the type of line that will be used to display the timeline entry
 */
class TimelineEntry(
        var icon : MutableState<String?>,
        var column : MutableState<String>,
        var lineType : MutableState<LineType>
)