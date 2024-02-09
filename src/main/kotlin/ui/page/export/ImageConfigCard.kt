package ui.page.export

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.generator.resources.ImageConfig
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.fields.DoubleField
import ui.fields.IntField
import ui.util.NestedSurface
import kotlin.math.roundToInt

@Composable
fun ImageConfigCard(imageConfig: ImageConfig, modifier: Modifier = Modifier) {
    var width by imageConfig.width
    var timelineScaling by imageConfig.timelineScaling

    NestedSurface(modifier) {
        Column(
            Modifier.padding(10.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Icon(Icons.Default.Tune, contentDescription = null)
                Label(Labels.EXPORT_IMAGE_SETTINGS, style = MaterialTheme.typography.h6, modifier = Modifier.padding(bottom = 10.dp))
            }

            IntField(width, { width = it }) {
                Text(LocalLanguage.current.getString(Labels.EXPORT_IMAGE_CONFIG_WIDTH))
            }
            Slider(
                timelineScaling.toFloat(),
                { timelineScaling = it.roundToInt().toDouble() },
                steps = 24,
                valueRange = 1f..25f
            )
            Text(text = "${LocalLanguage.current.getString(Labels.EXPORT_IMAGE_CONFIG_TIMELINE_SCALING)}: ${timelineScaling.roundToInt()}")
            DoubleField(timelineScaling, { timelineScaling = it }) {
                Text(LocalLanguage.current.getString(Labels.EXPORT_IMAGE_CONFIG_TIMELINE_SCALING))
            }
        }
    }
}