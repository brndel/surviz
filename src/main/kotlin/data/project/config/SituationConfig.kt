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

        }

        /**
         * This method removes the given timeline entry.
         * @param timelineEntry the timeline entry to be removed
         */
        fun removeTimelineEntry(timelineEntry: TimelineEntry){

        }

        /**
         * This method swaps the order of two given timeline entrys.
         * @param indexA the index of the first timeline entry to be swapped
         * @param indexB the index of the second timeline entry to be swapped
         */
        fun swapTimelineOrder(indexA: Int, indexB: Int){

        }

        /**
         * This method returns the timeline of this situation.
         * @return the timeline to be returned
         */
        fun getTimeline(): List<TimelineEntry>{
                return timeline
        }
}