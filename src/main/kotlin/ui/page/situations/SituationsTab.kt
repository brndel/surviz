package ui.page.situations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import data.project.config.BlockConfig

@Composable
fun SituationsTab(blockConfig: BlockConfig, blockId: Int) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        for (situationConfig in blockConfig.situationConfigs.toSortedMap()) {
            item {
                SituationConfig(situationConfig.value, situationConfig.key)
            }
        }
    }
}