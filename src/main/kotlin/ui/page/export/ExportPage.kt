package ui.page.export

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.io.DataManager
import data.io.exporter.ExporterVariant
import data.io.exporter.result.ExportResult
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


    NestedSurface(modifier) {
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(4.dp)) {
            items(fields) {
                GenericField(it.field, modifier = Modifier.fillMaxWidth())
            }
        }
    }

    var isExportDialogVisible by remember { mutableStateOf(false) }
    var exportResult by remember { mutableStateOf(ExportResult(arrayListOf())) }

    NestedSurface(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                val config = getExporterConfig()
                exportResult = DataManager.saveData(project, exporter, config)
                isExportDialogVisible = true
            }) {
                Text("Export")
            }
        }
    }

    if (isExportDialogVisible) {
        if (exportResult.errors.isEmpty()) {
            Dialog(onDismissRequest = { isExportDialogVisible = false }) {
                Box(Modifier.background(Color.White).wrapContentSize()) {
                    Text(
                        text = LocalLanguage.current.getString(Labels.EXPORT_SUCCESS),
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
        } else {
            AlertDialog(
                onDismissRequest = { isExportDialogVisible = false },
                title = { "${Text(LocalLanguage.current.getString(Labels.EXPORT_WARNING))}:" },
                confirmButton = {
                    Button(onClick = {
                        isExportDialogVisible = false
                    }) {
                        Text(LocalLanguage.current.getString(Labels.OK))
                    }
                },
                text = {
                    for (report in exportResult.getReportList()) {
                        val language = LocalLanguage.current

                        val blockId = report.id.block.toString()
                        val situationId = report.id.situation.toString()
                        val optionId = report.id.option.toString()

                        val label = language.getString(report.label)

                        val info = report.info

                        Text(
                            "${language.getString(Labels.BLOCK)} $blockId, ${
                                language.getString(
                                    Labels.SITUATION
                                )
                            } $situationId, ${language.getString(Labels.OPTION)} $optionId: $label $info"
                        )
                    }
                })
        }
    }
}