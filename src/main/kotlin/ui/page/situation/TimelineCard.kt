package ui.page.situation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.generator.resources.LineType
import data.project.config.TimelineEntry
import org.burnoutcrew.reorderable.ReorderableState
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.fields.IconField
import ui.fields.OptionsField
import ui.util.NestedSurface
import ui.util.ReorderHandle

/**
 * In this card the user can edit a [TimelineEntry]
 *
 * @param entry the [TimelineEntry] that can be edited on this card
 * @param onDelete get called when the delete button on this card gets pressed
 * @ui TextField for the column of the entry
 * @ui IconField for the icon of the entry
 */
@Composable
fun TimelineCard(
    entry: TimelineEntry,
    onDelete: () -> Unit,
    columns: List<String>,
    reorderState: ReorderableState<*>
) {
    var icon by entry.icon
    var column by entry.column
    var lineType by entry.lineType

    NestedSurface {
        Row(
            Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ReorderHandle(reorderState)

            IconField(icon) { icon = it }

            OptionsField(
                column,
                { column = it },
                options = columns,
                label = { Label(Labels.COLUMN) },
                modifier = Modifier.width(246.dp)
            ) {
                it
            }

            OptionsField(
                lineType,
                { lineType = it },
                LineType.entries.toList(),
                label = { Label(Labels.FIELD_LINE_TYPE) },
                modifier = Modifier.width(246.dp)
            ) {
                LocalLanguage.current.getString(it.label)
            }

            IconButton(onDelete) {
                Icon(Icons.Default.Delete, null)
            }
        }
    }
}