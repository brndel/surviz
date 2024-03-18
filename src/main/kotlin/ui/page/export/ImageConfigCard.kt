package ui.page.export

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import data.generator.resources.ImageConfig
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.fields.DoubleField
import ui.fields.IntField
import ui.util.InfoIconBox
import ui.util.NestedSurface
import ui.window.help.UserGuide
import kotlin.math.roundToInt

@Composable
fun ImageConfigCard(imageConfig: ImageConfig, modifier: Modifier = Modifier) {
    var width by imageConfig.width
    var timelineScaling by imageConfig.timelineScaling

    NestedSurface(modifier) {
        Column(
            Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Icon(Icons.Default.Tune, contentDescription = null)
                Label(
                    Labels.EXPORT_IMAGE_SETTINGS,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            IntField(width, { width = it }, maxValue = 5000) {
                Text(LocalLanguage.current.getString(Labels.EXPORT_IMAGE_CONFIG_WIDTH))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${LocalLanguage.current.getString(Labels.EXPORT_IMAGE_CONFIG_TIMELINE_SCALING)}: ${timelineScaling.roundToInt()}",
                    fontWeight = FontWeight.Bold
                )
                InfoIconBox (Labels.TIMELINE_SCALING_INFO_TITLE, Labels.TIMELINE_SCALING_INFO_DESCRIPTION, UserGuide.Export.section)
            }
            var sliderPosition by remember { mutableStateOf(timelineScaling.toFloat()) }

            Slider(
                value = sliderPosition,
                onValueChange = {
                    sliderPosition = it
                    timelineScaling = it.roundToInt().toDouble()
                },
                valueRange = 1f..25f,
                steps = 24,
                modifier = Modifier.pointerInput(Unit) {
                    // Add your mouse speed adjustment logic here
                }
            )
        }
    }
}
