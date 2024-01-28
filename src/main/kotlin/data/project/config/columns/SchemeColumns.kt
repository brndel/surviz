package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.config.SituationConfig
import data.project.data.SituationOption

/**
 * This class is a type of SingleValueColumn. It it is used for Ngene columns that were automatically chosen by using the defined column scheme of the corresponding SingleValueConfig.
 */
data object SchemeColumns : SingleValueColumn {
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
        var sum = 0.0
        val scheme = singleValueConfig.columnScheme.value

        if (scheme.endsWith('*')) {
            scheme.dropLast(1)

            for (key in situationOption.values.keys) {
                if (key.startsWith(scheme)) {
                    sum += situationOption.values.getValue(key)
                }
            }
        } else {
            for (key in situationOption.values.keys) {
                if (key.equals(scheme)) {
                    sum += situationOption.values.getValue(key)
                }
            }
        }

        return sum
    }
}