package ui.page.singleValue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.ProjectConfiguration
import data.project.config.single_value.SingleValueConfig
import data.project.data.DataScheme
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ui.Label
import ui.Labels


/**
 * On this page the user can edit the single values of a [ProjectConfiguration]
 *
 * @param projectConfig the [ProjectConfiguration] this page should edit
 * @ui SingleValueCard for each single value in the given [ProjectConfiguration] a [SingleValueItemCard] will be shown.
 * The order of the single values can be edited by dragging and dropping the [SingleValueItemCard]
 */
@Composable
fun SingleValuePage(projectConfig: ProjectConfiguration, dataScheme: DataScheme) {

    val reorderState = rememberReorderableLazyListState(
        onMove = { a, b -> projectConfig.swapSingleValueOrder(a.index, b.index) }
    )

    Column(
        Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Label(Labels.PAGE_SINGLE_VALUE, style = MaterialTheme.typography.h4)
            Icon(Icons.Default.ViewWeek, contentDescription = null, tint = MaterialTheme.colors.onBackground)
        }
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(onClick = {
                projectConfig.addSingleValue()
            }
            ) {
                Icon(Icons.Default.Add, null)
                Label(Labels.NEW)
            }
            Button(onClick = {
                projectConfig.addDivider()
            }
            ) {
                Icon(Icons.Default.Add, null)
                Label("")
            }
        }
        LazyColumn(
            state = reorderState.listState,
            modifier = Modifier.reorderable(reorderState).weight(1F),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(projectConfig.getSingleValueConfigOrder(), key = { it }) { id ->
                ReorderableItem(reorderState, key = id) { dragging ->
                    val singleValueItem = projectConfig.getSingleValues()[id]
                    SingleValueItemCard(
                        singleValueItem,
                        projectConfig,
                        id,
                        dataScheme,
                        onDelete = {
                            projectConfig.removeSingleValue(id)
                        },
                        reorderState,
                        dragging = dragging
                    )

                }
            }
        }
    }


}