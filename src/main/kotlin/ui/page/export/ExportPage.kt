package ui.page.export

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import data.project.Project
import ui.Label
import ui.Labels
import ui.fields.DoubleField
import ui.fields.IntField

/**
 * On this page the user can export the current [Project].
 * The user also can choose and configure an exporter before exporting
 *
 * @param project the project this page can export
 * @state currentExporter Exporter the currently selected exporter
 * @ui GenericField for every field of the currently selected exporter
 */
@Composable
fun ExportPage(project: Project) {
    var width by project.configuration.imageConfig.width
    var timelineScaling by project.configuration.imageConfig.timelineScaling
    LazyColumn {
        item {

            Label(Labels.PAGE_EXPORT)
        }
        item {
            IntField(width, { width = it }) {
                Text("Width")
            }

        }
        item {

            DoubleField(timelineScaling, { timelineScaling = it }) {
                Text("Timeline scaling")
            }
        }
    }
}