package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

/**
 * /**
 *  * This class is a type of SingleValueColumn. It is used for columns of the Ngene file that were used in the timeline.
 *  */
 */
class TimelineColumns(): SingleValueColumn {
    /**
     * This method returns the summed value of the column(s) that were used in the given situation.
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situation the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s), that were used in the timeline of the given situation
     */
    override fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double{
        return 0.0
    }
}