package ui.page.singleValue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.SingleValueIcon
import ui.fields.DoubleField
import ui.fields.IconField

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

    Surface(
        color = MaterialTheme.colors.background,
        shape = RoundedCornerShape(4.dp),
    ) {
        Column(
            Modifier.padding(4.dp).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconField(
                baseIcon
            ) { baseIcon = it }

            for (level in icon.levels) {
                Row {
                    IconField(level.icon.value) { level.icon.value = it }
                    DoubleField(level.lowerThreshold.value, { level.lowerThreshold.value = it })
                    IconButton({ icon.removeLevel(level) }) {
                        Icon(Icons.Default.Delete, null)
                    }
                }
            }

            IconButton({ icon.addLevel() }) {
                Icon(Icons.Default.Add, null)
            }
        }
    }
}