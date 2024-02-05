package ui.page.export

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import data.io.exporter.result.ExportResult
import data.project.Project
import ui.Labels
import ui.LocalLanguage

@Composable
fun ExportDialog(exportResult: ExportResult, project: Project, onDismissRequest: () -> Unit) {
    if (exportResult.errors.isEmpty()) {
        Dialog(onDismissRequest = onDismissRequest) {
            Box(Modifier.background(Color.White).wrapContentSize()) {
                Text(
                    text = LocalLanguage.current.getString(Labels.EXPORT_SUCCESS),
                    modifier = Modifier.padding(6.dp)
                )
            }
        }
    } else {
        val reportList = exportResult.getReportList()
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { "${Text(LocalLanguage.current.getString(Labels.EXPORT_WARNING))}:" },
            confirmButton = {
                Button(
                    onClick = {
                        for (report in reportList) {
                            report.applyFix(project)
                        }
                        onDismissRequest()
                    }
                ) {
                    Text(LocalLanguage.current.getString(Labels.APPLY_FIX))
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        onDismissRequest()
                    }) {
                    Text(LocalLanguage.current.getString(Labels.OK))
                }
            },
            text = {
                for (report in reportList) {
                    val language = LocalLanguage.current

                    val blockId = report.id.block.toString()
                    val situationId = report.id.situation.toString()
                    val optionId = report.id.option.toString()

                    val label = language.getString(report.label)
                    val info = report.info
                    val unit = report.unit

                    Text(
                        "${language.getString(Labels.BLOCK)} $blockId, ${
                            language.getString(
                                Labels.SITUATION
                            )
                        } $situationId, ${language.getString(Labels.OPTION)} $optionId: $label $info $unit"
                    )
                }
            })
    }
}