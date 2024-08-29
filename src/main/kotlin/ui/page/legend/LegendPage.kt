package ui.page.legend

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Explore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import data.generator.ImageGenerator
import data.project.Project
import data.project.config.legend.Legend
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable
import ui.Label
import ui.Labels
import ui.fields.ColorField
import ui.util.NestedSurface

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LegendPage(legend: Legend, project: Project) {

    val imageGenerator = remember { ImageGenerator(project.configuration, project.iconStorage) }

    val reorderState = rememberReorderableLazyListState(
        onMove = { a, b -> legend.swapEntryOrder(a.index, b.index) }
    )

    var color by legend.color

    Column(
        Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Label(Labels.PAGE_LEGEND, style = MaterialTheme.typography.h4)
            Icon(Icons.Default.Explore, null)
        }


        Box(Modifier.clip(RoundedCornerShape(10.dp))) {
            Image(
                imageGenerator.generateLegend(legend),
                null,
                modifier = Modifier.fillMaxWidth()
            )
        }



        NestedSurface {
            Box(Modifier.fillMaxWidth().padding(10.dp)) {
                ColorField(color, { color = it }, label = { Label(Labels.COLOR) })
            }
        }
        NestedSurface {
            Column(
                Modifier.padding(10.dp).weight(1F),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(onClick = {
                    legend.addEntry()
                }
                ) {
                    Icon(Icons.Default.Add, null)
                    Label(Labels.NEW)
                }

                LazyColumn(
                    state = reorderState.listState,
                    modifier = Modifier.reorderable(reorderState),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(legend.entries, key = { it.hashCode() }) { entry ->
                        ReorderableItem(reorderState, key = entry.hashCode()) { dragging ->
                            LegendEntryCard(
                                entry, onDelete = { legend.removeEntry(entry) },
                                reorderState, dragging = dragging
                            )
                        }
                    }
                }
            }
        }
    }
}