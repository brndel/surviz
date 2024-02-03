package ui.page.export

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.generator.resources.ImageConfig
import ui.fields.DoubleField
import ui.fields.IntField
import ui.util.NestedSurface

@Composable
fun ImageConfigCard(imageConfig: ImageConfig, modifier: Modifier = Modifier) {
    var width by imageConfig.width
    var timelineScaling by imageConfig.timelineScaling

    NestedSurface(modifier) {
        Column(
            Modifier.padding(4.dp)
        ) {
            IntField(width, { width = it }) {
                Text("Width")
            }
            DoubleField(timelineScaling, { timelineScaling = it }) {
                Text("Timeline scaling")
            }
        }
    }
}