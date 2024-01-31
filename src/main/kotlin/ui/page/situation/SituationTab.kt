package ui.page.situation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.SituationConfig
import data.project.config.columns.*
import data.project.data.DataSchemeOption
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ui.fields.ColorField
import ui.fields.OptionsField
import ui.fields.IconStorageImage
import java.util.*

/**
 * This view lets the user edit a [SituationConfig]
 *
 * @param config the [SituationConfig] that can be edited in this view
 * @ui TextField for the name of the situation
 * @ui ColorField for the color of the situation
 * @ui TimelineCard for every entry of the timeline of the situation
 */
@Composable
fun SituationTab(
    config: SituationConfig,
    option: DataSchemeOption,
    singleValueIds: List<UUID>,
    singleValueIcons: Map<UUID, String?>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        var name by config.name
        var color by config.color

        item {
            OutlinedTextField(
                name,
                { name = it },
                label = { Text("Name") })
        }

        item {
            ColorField(color) { color = it }
        }

        item {
            Text("Single value columns")
        }

        items(singleValueIds, key = { it }) { id ->
            val column = config.getColumns(id)

            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(4.dp)
            ) {
                Row(Modifier.padding(4.dp)) {
                    IconStorageImage(singleValueIcons[id])
                    SingleValueColumnField(column, { config.singleValueColumns[id] = it }, option.fields)
                }
            }
        }

        item {
            Row {
                Text("Timeline")
                IconButton({
                    config.addTimelineEntry()
                }) {
                    Icon(Icons.Default.Add, null)
                }
            }
        }

        item {
            Surface(
                modifier = Modifier.fillMaxWidth().heightIn(min = 32.dp, max = 4069.dp),
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(4.dp)
            ) {
                val reorderState = rememberReorderableLazyListState({ a, b ->
                    config.swapTimelineOrder(a.index, b.index)
                })

                LazyColumn(
                    state = reorderState.listState,
                    modifier = Modifier.reorderable(reorderState).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(4.dp)
                ) {
                    items(config.getTimeline(), key = { it }) { entry ->
                        ReorderableItem(reorderState, key = entry) { _ ->
                            TimelineCard(
                                entry,
                                { config.removeTimelineEntry(entry) },
                                option.fields,
                                reorderState
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SingleValueColumnField(
    value: SingleValueColumn,
    onValueChange: (SingleValueColumn) -> Unit,
    columns: List<String>
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    Row {
        Box {
            Button({ dropdownExpanded = true }) {
                Text(value.toString())
            }

            DropdownMenu(dropdownExpanded, { dropdownExpanded = false }) {
                DropdownMenuItem(onClick = {
                    onValueChange(SchemeColumns)
                    dropdownExpanded = false
                }) {
                    Text("Scheme")
                }

                DropdownMenuItem(onClick = {
                    onValueChange(ZeroColumn)
                    dropdownExpanded = false
                }) {
                    Text("Zero")
                }

                DropdownMenuItem(onClick = {
                    onValueChange(TimelineColumns)
                    dropdownExpanded = false
                }) {
                    Text("Timeline")
                }

                DropdownMenuItem(onClick = {
                    onValueChange(ListColumns())
                    dropdownExpanded = false
                }) {
                    Text("Select")
                }
            }
        }
        if (value is ListColumns) {
            Column {
                Row {
                    Text("Columns")
                    Button(onClick = {
                        value.columns.add("")
                    }) {
                        Icon(Icons.Default.Add, null)
                    }
                }
                for (column in value.columns.withIndex()) {
                    Row {
                        OptionsField(column.value, {
                            value.columns[column.index
                            ] = it
                        }, options = columns) {
                            Text(it)
                        }
                        IconButton(onClick = {
                            value.columns.removeAt(column.index)
                        }) {
                            Icon(Icons.Default.Delete, null)
                        }
                    }
                }
            }
        }
    }
}

