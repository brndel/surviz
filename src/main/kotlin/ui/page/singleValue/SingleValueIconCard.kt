package ui.page.singleValue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.SingleValueIcon
import ui.Label
import ui.Labels
import ui.fields.DoubleField
import ui.fields.IconField
import ui.util.InfoIconBox
import ui.util.NestedSurface

/**
 * In this card the user can edit a [SingleValueIcon].
 * This card is used by [SingleValueCard].
 *
 * @param icon the icon configuration that can be edited on this card
 * @ui IconField this card shows a [IconField] for every icon in the given [SingleValueIcon].
 * The order of the icons can be changed by drag and drop.
 * @ui IntField for every lowerBound of every level in the given [SingleValueIcon]
 */
@Composable
fun SingleValueIconCard(icon: SingleValueIcon) {
    var baseIcon by icon.baseIcon

    NestedSurface {
        Column(
            Modifier.padding(10.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IconField(
                baseIcon,
            ) { baseIcon = it }

            for (level in icon.levels) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    IconField(level.icon.value) { level.icon.value = it }
                    DoubleField(
                        level.lowerThreshold.value,
                        { level.lowerThreshold.value = it },
                        label = { Label(Labels.LOWER_THRESHOLD) })
                    IconButton({ icon.removeLevel(level) }) {
                        Icon(Icons.Default.Delete, null)
                    }
                }
            }

            Button(
                onClick = {
                    icon.addLevel()
                }
            ) {
                Icon(Icons.Default.Add, null)
                Label(Labels.ADD_SINGLE_VALUE_ICON_LEVEL)
                InfoIconBox(Labels.SINGLE_VALUE_LEVEL_INFO_TITLE,Labels.SINGLE_VALUE_LEVEL_INFO_DESCRIPTION)
            }
        }
    }
}