package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import data.project.config.columns.SingleValueColumn
import java.awt.Color
import java.util.UUID

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