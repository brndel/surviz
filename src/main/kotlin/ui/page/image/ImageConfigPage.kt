package ui.page.image

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.generator.resources.ImageConfig
import data.project.config.legend.Legend
import ui.Label
import ui.Labels

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageConfigPage(imageConfig: ImageConfig) {
    Column(
        Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Label(Labels.PAGE_IMAGE, style = MaterialTheme.typography.h4)
            Icon(
                Icons.Default.Image,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground
            )
        }

        ImageConfigCard(imageConfig, Modifier.fillMaxWidth())
    }
}