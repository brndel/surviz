package ui.page.legend

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.legend.LegendEntry
import org.burnoutcrew.reorderable.ReorderableState
import ui.Label
import ui.Labels
import ui.fields.IconField
import ui.fields.TextField
import ui.util.NestedSurface
import ui.util.ReorderHandle
import ui.util.TextSwitch

@Composable
fun LegendEntryCard(
    entry: LegendEntry,
    onDelete: () -> Unit,
    reorderState: ReorderableState<*>,
    dragging: Boolean = false
) {
    val elevation by animateDpAsState(
        if (dragging) 16.dp else 0.dp
    )

    NestedSurface(elevation = elevation) {
        Row(Modifier.padding(10.dp).fillMaxWidth()) {
            ReorderHandle(reorderState)

            LegendEntryContent(entry)

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, null)
            }
        }
    }
}

@Composable
private fun RowScope.LegendEntryContent(entry: LegendEntry) {
    Column(Modifier.weight(1F).padding(start = 10.dp, end = 10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        TextSwitch(Labels.LEGEND_SWITCH, entry.isIcon, Labels.LEGEND_SWITCH_INFO_TITLE, null, null)

        if (entry.isIcon.value) {
            IconField(entry.path.value) { entry.path.value = it }
        } else {
            TextField(
                entry.abbreviation.value,
                onValueChange = { entry.abbreviation.value = it },
            ) {
                Label(Labels.LEGEND_ABBREVIATION)
            }
        }

        TextField(
            entry.description.value,
            onValueChange = { entry.description.value = it },
        ) {
            Label(Labels.LEGEND_DESCRIPTION)
        }
    }
}
