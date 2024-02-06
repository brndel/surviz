package ui.page.singleValue

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.SingleValueConfig
import org.burnoutcrew.reorderable.ReorderableState
import ui.Label
import ui.Labels
import ui.util.NestedSurface
import ui.util.ReorderHandle

/**
 * In this card the user can edit a [SingleValueConfig]
 *
 * @param config the configuration that can be edited on this card
 * @param onDelete get called when the delete button on this card gets pressed
 * @ui TextField for the unit and the colorScheme of the given [SingleValueConfig]
 * @ui SingleValueIconCard for the icons of the given [SingleValueConfig]
 */
@Composable
fun SingleValueCard(
    config: SingleValueConfig,
    onDelete: () -> Unit,
    reorderState: ReorderableState<*>,
    dragging: Boolean = false
) {

    val elevation by animateDpAsState(
        if (dragging) {
            16.dp
        } else {
            0.dp
        }
    )

    NestedSurface(
        elevation = elevation
    ) {
        Row(
            Modifier.padding(4.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            ReorderHandle(reorderState)

            SingleValueCardContent(config)

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, null)
            }
        }
    }
}

@Composable
private fun RowScope.SingleValueCardContent(config: SingleValueConfig) {
    var unit by config.unit
    var columnScheme by config.columnScheme

    Column(Modifier.weight(1F), verticalArrangement = Arrangement.spacedBy(4.dp)) {
        OutlinedTextField(unit, { unit = it }, label = {
            Label(Labels.FIELD_UNIT)
        })

        OutlinedTextField(columnScheme, { columnScheme = it }, label = {
            Label(Labels.FIELD_COLUMN_SCHEME)
        })

        SingleValueIconCard(config.icon)
    }
}