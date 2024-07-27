package ui.page.image

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import data.generator.resources.ImageConfig
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.fields.ColorField
import ui.fields.IntField
import ui.util.InfoIconBox
import ui.util.NestedSurface
import ui.window.help.UserGuide
import kotlin.math.roundToInt

@Composable
fun ImageConfigCard(imageConfig: ImageConfig, modifier: Modifier = Modifier) {
    var width by imageConfig.width
    var timelineScaling by imageConfig.timelineScaling
    var backgroundColor by imageConfig.backgroundColor
    var alpha by imageConfig.alpha

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
                InfoIconBox(
                    Labels.TIMELINE_SCALING_INFO_TITLE,
                    Labels.TIMELINE_SCALING_INFO_DESCRIPTION,
                    UserGuide.Export.section
                )
            }
            var scalingSliderPosition by remember { mutableStateOf(timelineScaling.toFloat()) }

            Slider(
                value = scalingSliderPosition,
                onValueChange = {
                    scalingSliderPosition = it
                    timelineScaling = it.roundToInt().toDouble()
                },
                valueRange = 1f..25f,
                steps = 24,
                modifier = Modifier.pointerInput(Unit) {
                    // Add your mouse speed adjustment logic here
                }
            )

            ColorField(backgroundColor, {
                backgroundColor = it
            }, label = {
                Label(Labels.EXPORT_IMAGE_CONFIG_BACKGROUND_COLOR)
            })
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${LocalLanguage.current.getString(Labels.EXPORT_IMAGE_CONFIG_ALPHA)}: ${String.format("%.2f", alpha)}",
                    fontWeight = FontWeight.Bold
                )
                InfoIconBox(
                    Labels.SINGLE_VALUE_ALPHA_INFO_TITLE,
                    Labels.SINGLE_VALUE_ALPHA_INFO_DESCRIPTION,
                    null
                )
            }

            var alphaSliderPosition by remember { mutableStateOf(alpha) }

            Slider(
                value = alphaSliderPosition,
                onValueChange = {
                    alphaSliderPosition = it
                    alpha = it
                },
                valueRange = 0f..1f,
                steps = 19,
                modifier = Modifier.pointerInput(Unit) {}
            )
        }
    }
}
