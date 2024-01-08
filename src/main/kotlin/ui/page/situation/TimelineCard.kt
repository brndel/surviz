package ui.page.situation

import androidx.compose.runtime.Composable
import data.project.config.TimelineEntry

/**
 * In this card the user can edit a [TimelineEntry]
 *
 * @param entry the [TimelineEntry] that can be edited on this card
 * @param onDelete get called when the delete button on this card gets pressed
 * @ui TextField for the column of the entry
 * @ui IconField for the icon of the entry
 */
@Composable
fun TimelineCard(entry: TimelineEntry, onDelete: () -> Unit) {
}