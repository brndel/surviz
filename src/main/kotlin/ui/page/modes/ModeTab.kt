package ui.page.modes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import data.project.config.SingleValueConfig
import data.project.config.OptionConfig
import data.project.config.columns.ListColumns
import data.project.config.columns.SchemeColumns
import data.project.config.columns.SingleValueColumn
import data.project.config.columns.TimelineColumns
import data.project.config.columns.ZeroColumn
import data.project.data.DataSchemeOption
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ui.Label
import ui.Labels
import ui.fields.ColorField
import ui.fields.IconStorageImage
import ui.fields.OptionsField
import ui.util.NestedSurface
import java.util.UUID

/**
 * This view lets the user edit a [OptionConfig]
 *
 * @param config the [OptionConfig] that can be edited in this view
 * @ui TextField for the name of the situation
 * @ui ColorField for the color of the situation
 * @ui TimelineCard for every entry of the timeline of the situation
 */
@Composable
fun ModeTab(
    config: OptionConfig,
    option: DataSchemeOption,
    singleValueIds: List<UUID>,
    singleValues: Map<UUID, SingleValueConfig>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        var name by config.name
        var color by config.color

        item {
            OutlinedTextField(
                name,
                { name = it },
                label = { Label(Labels.SITUATION_NAME) })
        }

        item {
            ColorField(color, { color = it }, label = { Label(Labels.COLOR) })
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(Icons.Default.ViewWeek, contentDescription = null)
                Label(Labels.SITUATION_SINGLE_VALUE_COLUMNS, style = MaterialTheme.typography.h6)
            }

        }

        items(singleValueIds, key = { it }) { id ->
            val column = config.getColumns(id)

            NestedSurface(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(Modifier.padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    val singleValueConfig = singleValues[id] ?: return@Row
                    IconStorageImage(
                        singleValueConfig.icon.baseIcon.value,
                    )
                    SingleValueColumnField(
                        column,
                        { config.singleValueColumns[id] = it },
                        option.fieldsList,
                        singleValueConfig.columnScheme.value
                    )
                }
            }
        }

        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(Icons.Default.Timeline, contentDescription = null)
                Label(Labels.TIMELINE, style = MaterialTheme.typography.h6)
                IconButton({
                    config.addTimelineEntry()
                }) {
                    Icon(Icons.Default.Add, null)
                }
            }
        }

        item {
            NestedSurface(
                modifier = Modifier.fillMaxWidth().heightIn(min = 32.dp, max = 4069.dp),
            ) {
                val reorderState = rememberReorderableLazyListState({ a, b ->
                    config.swapTimelineOrder(a.index, b.index)
                })

                LazyColumn(
                    state = reorderState.listState,
                    modifier = Modifier.reorderable(reorderState).fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(config.getTimeline(), key = { it }) { entry ->
                        ReorderableItem(reorderState, key = entry) { _ ->
                            TimelineCard(
                                entry,
                                { config.removeTimelineEntry(entry) },
                                option.fieldsList,
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
    columns: List<String>,
    scheme: String,
) {
    var dropdownExpanded by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box {
            Button({ dropdownExpanded = true }) {
                Label(value.nameLabel)
            }

            DropdownMenu(dropdownExpanded, { dropdownExpanded = false }) {

                @Composable
                fun ColumnsMenuItem(
                    nameLabel: String,
                    descriptionLabel: String,
                    createColumn: () -> SingleValueColumn
                ) {
                    DropdownMenuItem(onClick = {
                        onValueChange(createColumn())
                        dropdownExpanded = false
                    }) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(
                                top = 10.dp,
                                bottom = 10.dp,
                                start = 4.dp,
                                end = 4.dp
                            )
                        ) {
                            Label(nameLabel)
                            Label(
                                descriptionLabel,
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.alpha(0.75F)
                            )
                        }
                    }
                }

                @Composable
                fun ColumnsMenuItem(
                    column: SingleValueColumn
                ) {
                    ColumnsMenuItem(
                        column.nameLabel,
                        column.descriptionLabel
                    ) { column }
                }


                ColumnsMenuItem(SchemeColumns)
                ColumnsMenuItem(ZeroColumn)
                ColumnsMenuItem(TimelineColumns)
                ColumnsMenuItem(ListColumns.nameLabel, ListColumns.descLabel) {
                    ListColumns()
                }
            }
        }
        when (value) {
            is SchemeColumns -> {
                SchemeColumnsExtra(scheme, columns)
            }

            is ListColumns -> {
                ListColumnsExtra(value, columns)
            }

            else -> {

            }
        }
    }
}

@Composable
private fun ListColumnsExtra(value: ListColumns, columns: List<String>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Label(Labels.COLUMNS)
            IconButton(onClick = {
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
                    it
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

@Composable
private fun SchemeColumnsExtra(scheme: String, columns: List<String>) {
    Surface(
        color = MaterialTheme.colors.secondary,
        contentColor = MaterialTheme.colors.onSecondary,
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Info, null)
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Label(Labels.FIELD_COLUMN_SCHEME)
                    Text("'$scheme'")
                }
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val result = SchemeColumns.filterWithScheme(scheme, columns)
                    for (string in result) {
                        item {
                            Surface(
                                color = MaterialTheme.colors.secondaryVariant,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(string, modifier = Modifier.padding(10.dp))
                            }
                        }
                    }

                    if (result.isEmpty()) {
                        item {
                            Label(Labels.SCHEME_NO_RESULT_FOUND)
                        }
                    }
                }
            }
        }
    }
}