package ui.page.situations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.BlockConfig
import ui.Label
import ui.Labels
import ui.util.NestedSurface

@Composable
fun SituationsPage(blockConfigs: SnapshotStateMap<Int, BlockConfig>) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize().padding(10.dp)) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Label(Labels.PAGE_OVERRIDE_OPTIONS, style = MaterialTheme.typography.h4)
            Icon(
                Icons.Default.Tune,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground
            )
        }
        NestedSurface {
            Column {
                TabRow(
                    selectedTabIndex = selectedTab,
                    modifier = Modifier.height(43.dp),
                    backgroundColor = MaterialTheme.colors.primary
                ) {
                    blockConfigs.forEach { entry ->
                        Tab(entry.key - 1 == selectedTab, onClick = { selectedTab = entry.key - 1}) {
                            Text(entry.key.toString())
                        }
                    }
                }

                val selectedBlockConfig = blockConfigs[selectedTab + 1]

                key(selectedTab) {
                    selectedBlockConfig?.let { SituationsTab(it, selectedTab + 1) }
                }
            }
        }
    }
}
