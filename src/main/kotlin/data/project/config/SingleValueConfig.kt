package data.project.config

import androidx.compose.runtime.MutableState

class SingleValueConfig constructor(
        var unit: MutableState<String>,
        var columnScheme: MutableState<String>,
        var icon: SingleValueIcon
) {
}