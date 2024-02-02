package ui.page.export

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.io.DataManager
import data.io.exporter.ExporterVariant
import data.project.Project
import ui.Label
import ui.Labels
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
        Modifier.fillMaxSize().padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Label(Labels.PAGE_EXPORT, style = MaterialTheme.typography.h4)

        ImageConfigCard(project.configuration.imageConfig, Modifier.fillMaxWidth())

        OptionsField(selectedExporter, { selectedExporter = it }, ExporterVariant.entries) {
            Text(it.toString())
        }

        ExporterConfigCard(selectedExporter, project, Modifier.weight(1F))
    }
}

@Composable
private fun ExporterConfigCard(exporter: ExporterVariant, project: Project, modifier: Modifier = Modifier) {
    val fields = remember(exporter) { exporter.getExporter().getFields(project) }

    fun getExporterConfig() =
        fields.associate {
            it.name to it.field.getValue()
        }


    NestedSurface(modifier) {
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)) {
            items(fields) {
                GenericField(it.field, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    NestedSurface(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val config = getExporterConfig()
                DataManager.saveData(project, exporter, config)
            }) {
                Text("Export")
            }
        }
    }
}