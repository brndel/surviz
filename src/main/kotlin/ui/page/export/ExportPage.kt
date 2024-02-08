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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import ui.LocalLanguage
import ui.fields.GenericField
import ui.fields.OptionsField
import ui.util.NestedSurface

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
        Box(Modifier.padding(10.dp)) {
            Label(Labels.PAGE_EXPORT, style = MaterialTheme.typography.h4)
        }
        ImageConfigCard(project.configuration.imageConfig, Modifier.fillMaxWidth())


        NestedSurface {
            Column(Modifier.padding(10.dp)) {
                Label(Labels.EXPORT_SETTINGS, style = MaterialTheme.typography.h6, modifier = Modifier.padding(bottom = 8.dp))
                OptionsField(
                    selectedExporter,
                    { selectedExporter = it },
                    ExporterVariant.entries,
                    label = {
                        Label(Labels.EXPORTER)
                    }) {
                    Text(it.toString())
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
    val fields = remember(exporter) { exporter.getExporter().getFields(project) }

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

    Box(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val config = getExporterConfig()
                isExporting = true
                DataManager.saveData(project, exporter, config) {
                    exportResult = it
                    isExporting = false
                }
            }, enabled = !isExporting) {
                if (isExporting) {
                    CircularProgressIndicator()
                } else {
                    Text(LocalLanguage.current.getString(Labels.EXPORT_BUTTON))
                }
            }
        }
    }

    exportResult?.let {
        ExportDialog(it, project, onDismissRequest = { exportResult = null })
    }
}
