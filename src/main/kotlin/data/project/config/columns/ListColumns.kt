package data.project.config.columns

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import data.project.config.SingleValueConfig
import data.project.config.SituationConfig
import data.project.data.SituationOption
import ui.Labels

/**
 * This class is a type of SingleValueColumn. It contains a list of columns of the Ngene file that were chosen by hand.
 * @param columns the columns that were chosen.
 */
class ListColumns(
        var columns: SnapshotStateList<String> = mutableStateListOf()
): SingleValueColumn(
    nameLabel,
    descLabel,
) {
    /**
     * This method returns the value of the column(s).
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situation the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s)
     */
    override fun getValue(
        singleValueConfig: SingleValueConfig,
        situationConfig: SituationConfig,
        situationOption: SituationOption
    ): Double {
        var sum = 0.0

        situationOption.values.keys.forEach { key ->
            if (columns.contains(key)) {
                sum += situationOption.values.getValue(key)
            }
        }

        return sum
    }

    companion object {
        const val nameLabel = Labels.SELECT_COLUMN_NAME
        const val descLabel = Labels.SELECT_COLUMN_DESC
    }
}