package ui.page.singleValue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.ProjectConfiguration
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ui.Label
import ui.Labels


/**
 * On this page the user can edit the single values of a [ProjectConfiguration]
 *
 * @param projectConfig the [ProjectConfiguration] this page should edit
 * @ui SingleValueCard for each single value in the given [ProjectConfiguration] a [SingleValueCard] will be shown.
 * The order of the single values can be edited by dragging and dropping the [SingleValueCard]
 */
@Composable
fun SingleValuePage(projectConfig: ProjectConfiguration) {

    val reorderState = rememberReorderableLazyListState(
        onMove = { a, b -> projectConfig.swapSingleValueOrder(a.index, b.index) }
    )

    Column(
        Modifier.fillMaxSize().padding(10.dp),
    ) {
        Box(Modifier.padding(10.dp)) {
            Label(Labels.PAGE_SINGLE_VALUE, style = MaterialTheme.typography.h4)
        }
        Button(onClick = {
            projectConfig.addSingleValue()
        }) {
            Icon(Icons.Default.Add, null)
            Label(Labels.NEW)
        }

        LazyColumn(
            state = reorderState.listState,
            modifier = Modifier.reorderable(reorderState).weight(1F),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(projectConfig.getSingleValueConfigOrder(), key = { it }) { id ->
                ReorderableItem(reorderState, key = id) { dragging ->
                    SingleValueCard(projectConfig.getSingleValues()[id]!!, onDelete = {
                        projectConfig.removeSingleValue(id)
                    }, reorderState, dragging = dragging)
                }
            }
        }
    }


}