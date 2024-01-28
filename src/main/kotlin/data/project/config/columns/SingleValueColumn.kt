package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.config.SituationConfig
import data.project.data.Situation
import data.project.data.SituationOption

/**
 * This interface represents a type of single value columns of the Ngene file.
 */
sealed interface SingleValueColumn {
    /**
     * This method returns the value of the column(s).
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situationOption the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s)
     */
    fun getValue(singleValueConfig: SingleValueConfig,
                 situationConfig: SituationConfig,
                 situationOption: SituationOption): Double
}