package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.config.OptionConfig
import data.project.data.SituationOption
import ui.Labels

/**
 * This class is a type of SingleValueColumn. It contains a zero column with the value 0.
 */
data object ZeroColumn : SingleValueColumn(
    Labels.ZERO_COLUMN_NAME,
    Labels.ZERO_COLUMN_DESC,
) {
    /**
     * This method returns the value of a zero column.
     * @param singleValueConfig the config file of the single value that the column refers to
     * @param situation the situation in which the value of the column will be used
     * @return the value 0.0 will be returned.
     */
    override fun getValue(
        singleValueConfig: SingleValueConfig,
        optionConfig: OptionConfig,
        situationOption: SituationOption
    ): Double {
        return 0.0
    }
}