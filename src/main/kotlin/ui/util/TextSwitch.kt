package ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import ui.Label
import ui.window.help.HelpEntry

@Composable
public fun TextSwitch(
    label: String,
    state: MutableState<Boolean>,
    infoTitleLabel: String,
    infoDescriptionLabel: String,
    helpEntry: HelpEntry?
) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy((10.dp))) {
        Label(label)
        Switch(
            state.value, onCheckedChange = {
                state.value = it
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.primary,
                uncheckedThumbColor = MaterialTheme.colors.primary,
            )
        )
        InfoIconBox(infoTitleLabel, infoDescriptionLabel, helpEntry)
    }
}