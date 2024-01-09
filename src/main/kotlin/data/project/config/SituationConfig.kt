package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import data.project.config.columns.SingleValueColumn
import java.awt.Color
import java.util.UUID

/**
 * This class represents the configuration of a situation.
 * @param name the name that is given to the situation
 * @param color the color of the situation
 * @param singleValueColumns the columns of the Ngene file that contain the information for this situation
 * @param timeline the timeline entrys
 */
class SituationConfig constructor(
        var name: MutableState<String>,
        var color: MutableState<Color>,
        var singleValueColumns: SnapshotStateMap<UUID, SingleValueColumn>,
        private var timeline: SnapshotStateList<TimelineEntry>
) {
        fun addTimelineEntry(){

        }
        fun removeTimelineEntry(timelineEntry: TimelineEntry){

        }
        fun swapTimelineOrder(indexA: Int, indexB: Int){

        }
        fun getTimeline(): List<TimelineEntry>{
                return timeline
        }
}