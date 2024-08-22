package ui.page.situations

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Slider
import androidx.compose.material.Text
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
import data.project.config.SituationConfig
import ui.Labels
import ui.LocalLanguage
import ui.util.InfoIconBox
import ui.util.NestedSurface
import ui.util.TextSwitch
import ui.window.help.UserGuide
import kotlin.math.roundToInt

@Composable
fun SituationConfig(situationConfig: SituationConfig, situationId: Int) {
    var timelineScaling by situationConfig.scale
    NestedSurface {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(
                "${LocalLanguage.current.getString(Labels.SITUATION)}: $situationId",
                fontWeight = FontWeight.Bold
            )
            TextSwitch(
                Labels.SITUATION_OVERRIDE_SCALE,
                situationConfig.overrideScale,
                Labels.SITUATION_OVERRIDE_SCALE_INFO_TITLE,
                null,
                null
            )
            if (situationConfig.overrideScale.value) {
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

                var sliderPosition by remember { mutableStateOf(timelineScaling) }

                Slider(
                    value = sliderPosition,
                    onValueChange = {
                        sliderPosition = it
                        timelineScaling = it
                    },
                    valueRange = 1f..25f,
                    steps = 24,
                    modifier = Modifier.pointerInput(Unit) {}
                )
            }
        }
    }
}