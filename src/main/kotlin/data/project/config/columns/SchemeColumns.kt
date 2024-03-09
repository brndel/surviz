package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.config.SituationConfig
import data.project.data.SituationOption
import ui.Labels
import java.util.regex.PatternSyntaxException

/**
 * This class is a type of SingleValueColumn. It it is used for Ngene columns that were automatically chosen by using the defined column scheme of the corresponding SingleValueConfig.
 */
data object SchemeColumns : SingleValueColumn(
    Labels.SCHEME_COLUMN_NAME,
    Labels.SCHEME_COLUMN_DESC,
) {
    /**
     * This method returns the value of the column(s).
     * @param singleValueConfig the config file of the single value that the column(s) refer to
     * @param situation the situation in which the value of the column(s) will be used
     * @return the sum of the column value(s) that were automatically chosen by using the defined column scheme of the singleValueConfig.
     */
    override fun getValue(
        singleValueConfig: SingleValueConfig,
        situationConfig: SituationConfig,
        situationOption: SituationOption
    ): Double {
        val sum: Double
        val scheme = singleValueConfig.columnScheme.value

        val filteredKeys = filterWithScheme(scheme, situationOption.values.keys.toList())

        sum = filteredKeys.sumOf { situationOption.values.getValue(it) }

        return sum
    }

    fun filterWithScheme(
        scheme: String,
        list: List<String>
    ): List<String> {
        val regex: Regex =
            try {
                Regex(scheme)
            } catch (e: PatternSyntaxException) {
                return listOf()
            }

        val filteredList = list.filter {
            regex.containsMatchIn(it)
        }

        return filteredList
    }
}