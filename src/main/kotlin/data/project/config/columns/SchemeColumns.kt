package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

/**
 * This class is a type of SingleValueColumn. It it is used for Ngene columns that were automatically chosen by using the defined column scheme of the corresponding SingleValueConfig.
 */
class SchemeColumns(): SingleValueColumn {
    /**
     * This method returns the value of the column(s).
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situation the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s) that were automatically chosen by using the defined column scheme of the singleValueConfig.
     */
    override fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double{
        return 0.0
    }
}