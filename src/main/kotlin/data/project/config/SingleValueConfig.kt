package data.project.config

import androidx.compose.runtime.MutableState

/**
 * This class represents the configuration of a single value.
 * It stores all information about a single value.
 * @param unit the defined unit of the single value
 * @param columnScheme the defined column scheme that is used for finding the right Ngene columns of the single value in the class SchemeColumns.
 * @param icon The icon that is displayed for this single value
 */
data class SingleValueConfig(
        var unit: MutableState<String>,
        var columnScheme: MutableState<String>,
        var icon: SingleValueIcon
)