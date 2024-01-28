package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.config.SituationConfig
import data.project.data.SituationOption

/**
 * This class is a type of SingleValueColumn. It is used for columns of the Ngene file that were used in the timeline.
 */
data object TimelineColumns : SingleValueColumn {
    /**
     * This method returns the summed value of the column(s) that were used in the given situation.
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situation the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s), that were used in the timeline of the given situation
     */
    override fun getValue(
        singleValueConfig: SingleValueConfig,
        situationConfig: SituationConfig,
        situationOption: SituationOption
    ): Double {

        var sum = 0.0

        for (timelineEntry in situationConfig.getTimeline()) {
            sum += situationOption.values.getValue(timelineEntry.column.value)
        }

        return sum
    }
}