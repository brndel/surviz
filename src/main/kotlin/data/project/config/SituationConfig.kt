package data.project.config

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Color
import data.generator.resources.LineType

import data.project.config.columns.SingleValueColumn
import java.util.UUID

/**
 * This class represents the configuration of a situation.
 * @param name the name that is given to the situation
 * @param color the color of the situation
 * @param singleValueColumns the columns of the Ngene file that contain the information for this situation. The single value UUID's will be assigned to a type of SingleValueColumn, that stores the columns in the Ngene file that contain the information of this single value.
 * @param timeline the timeline entrys
 */
class SituationConfig constructor(
        var name: MutableState<String>,
        var color: MutableState<Color>,
        var singleValueColumns: SnapshotStateMap<UUID, SingleValueColumn>,
        private var timeline: SnapshotStateList<TimelineEntry>
) {
        /**
         * this method adds a new timeline entry.
         */
        fun addTimelineEntry(){
                val newTimelineEntry: TimelineEntry = TimelineEntry(mutableStateOf(null), mutableStateOf(""), mutableStateOf(
                    LineType.Solid))
                timeline.add(newTimelineEntry)
        }

        /**
         * This method removes the given timeline entry.
         * @param timelineEntry the timeline entry to be removed
         */
        fun removeTimelineEntry(timelineEntry: TimelineEntry){
                timeline.remove(timelineEntry)
        }

        /**
         * This method removes the given timeline entry.
         * @param index the index of the timeline entry to be removed
         */
        fun removeTimelineEntry(index: Int){
                timeline.remove(timeline[index])
        }

        /**
         * This method swaps the order of two given timeline entrys.
         * @param indexA the index of the first timeline entry to be swapped
         * @param indexB the index of the second timeline entry to be swapped
         */
        fun swapTimelineOrder(indexA: Int, indexB: Int){
                val temp = timeline[indexA]
                timeline[indexA] = timeline[indexB]
                timeline[indexB] = temp
        }

        /**
         * This method returns the timeline of this situation.
         * @return the timeline to be returned
         */
        fun getTimeline(): List<TimelineEntry>{
                return timeline
        }
}