package ui.page.export

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.io.DataManager
import data.io.exporter.ExporterVariant
import data.io.utils.result.ExportResult
import data.project.Project
import ui.Label
import ui.Labels
import ui.fields.GenericField
import ui.fields.OptionsField
import ui.util.NestedSurface
import java.nio.file.Path

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
    var selectedExporter by remember { mutableStateOf(ExporterVariant.Png) }

    Column(
        Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Label(Labels.PAGE_EXPORT, style = MaterialTheme.typography.h4)
            Icon(Icons.Default.Send, contentDescription = null, tint = MaterialTheme.colors.onBackground)
        }
        ImageConfigCard(project.configuration.imageConfig, Modifier.fillMaxWidth())


        NestedSurface {
            Column(Modifier.padding(10.dp)) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(Icons.Default.Tune, contentDescription = null)
                    Label(Labels.EXPORT_SETTINGS, style = MaterialTheme.typography.h6, modifier = Modifier.padding(bottom = 10.dp))
                }
                OptionsField(
                    selectedExporter,
                    { selectedExporter = it },
                    ExporterVariant.entries,
                    label = {
                        Label(Labels.EXPORTER)
                    }) {
                    it.name
                }

                ExporterConfigCard(selectedExporter, project, Modifier.weight(1F))
            }
        }

    }
}


@Composable
private fun ExporterConfigCard(
    exporter: ExporterVariant,
    project: Project,
    modifier: Modifier = Modifier
) {
    val fields = remember(exporter) { exporter.getExporter().getFields() }

    fun getExporterConfig() =
        fields.associate {
            it.name to it.field.getValue()
        }


    Box(modifier) {
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 10.dp, top = 10.dp)) {
            items(fields) {
                GenericField(it.field, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    var exportResult: ExportResult? by remember { mutableStateOf(null) }
    var isExporting: Boolean by remember { mutableStateOf(false) }
    var exportPath: Path? by remember { mutableStateOf(null) }

    Box(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val config = getExporterConfig()
                isExporting = true
                DataManager.saveData(project, exporter, config, onPathSelected = {exportPath = it}) {
                    exportResult = it
                    isExporting = false
                }
            }, enabled = !isExporting) {
                if (isExporting) {
                    CircularProgressIndicator()
                } else {
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Label(Labels.EXPORT_BUTTON, style = MaterialTheme.typography.h6)
                        Icon(Icons.Default.Send, contentDescription = null)
                    }
                }
            }
        }
    }

    exportResult?.let {
        ExportDialog(it, project, exportPath, onDismissRequest = { exportResult = null })
    }
}
