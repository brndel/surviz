package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

/**
 * This class is a type of SingleValueColumn. It contains a list of columns of the Ngene file that were hosen by hand.
 * @param columns the columns that were chosen.
 */
class ListColumns constructor(
        var columns: List<String>
): SingleValueColumn {
    /**
     * This method returns the value of the column(s).
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situation the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s)
     */
    override fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double{
        return 0.0
    }
}