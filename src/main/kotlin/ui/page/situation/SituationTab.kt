package ui.page.situation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import data.project.config.SituationConfig
import data.project.config.columns.*
import data.project.data.DataSchemeOption
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ui.fields.ColorField
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
    // TODO Fix nested LazyColumn not working
//    LazyColumn(
    Column(
        modifier.padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
//        contentPadding = PaddingValues(4.dp)
    ) {
        var name by config.name
        var color by config.color

//        item {
            OutlinedTextField(
                name,
                { name = it },
                label = { Text("Name") })
//        }

//        item {
            ColorField(color) { color = it }
//        }

//        items(singleValueIds) { id ->
        for(id in singleValueIds) {
            val column = config.singleValueColumns.getOrPut(id) { SchemeColumns } // TODO Move to SituationConfig

            Row {
                IconStorageImage(singleValueIcons[id])
                SingleValueColumnField(column) { config.singleValueColumns[id] = it }
            }
        }

//        item {
            Row {
                Text("Timeline")
                IconButton({
                    config.addTimelineEntry()
                }) {
                    Icon(Icons.Default.Add, null)
                }
            }
//        }

//        item {
            Surface(
                modifier = Modifier.fillMaxWidth(),
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
                    items(config.getTimeline(), { it }) { entry ->
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
//        }
    }
}

@Composable
private fun SituationTimeline() {

}


@Composable
private fun SingleValueColumnField(value: SingleValueColumn, onValueChange: (SingleValueColumn) -> Unit) {
    var dropdownExpanded by remember { mutableStateOf(false) }
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
                onValueChange(ListColumns(columns = listOf()))
                dropdownExpanded = false
            }) {
                Text("Select")
            }
        }
    }
}

