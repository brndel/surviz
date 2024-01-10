package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

/**
 * This interface represents a type of single value columns of the Ngene file.
 */
sealed interface SingleValueColumn {
    /**
     * This method returns the value of the column(s).
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situation the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s)
     */
    fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double
}