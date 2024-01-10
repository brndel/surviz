package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

/**
 * /**
 *  * This class is a type of SingleValueColumn. It contains a zero column with the value 0.
 *  */
 */
class ZeroColumn(): SingleValueColumn {
    /**
     * This method returns the value of a zero column.
     * @param singleValueConfig the config file of the single value that the column refers to
     * @param situation the situation in which the value of the column will be used
     * @return the value 0.0 will be returned.
     */
    override fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double{
        return 0.0
    }
}