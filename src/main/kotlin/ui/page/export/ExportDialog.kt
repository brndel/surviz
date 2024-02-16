package ui.page.export

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.io.utils.result.ExportResult
import data.io.utils.result.report.ExportReport
import data.project.Project
import ui.Label
import ui.Labels
import ui.LocalLanguage

@Composable
fun ExportDialog(exportResult: ExportResult, project: Project, onDismissRequest: () -> Unit) {

    val title: String
    val reportList = exportResult.getReportList()
    var confirmClickable = false
    val icon: @Composable () -> Unit

    if (exportResult.warnings.isEmpty()) {
        title = LocalLanguage.current.getString(Labels.EXPORT_SUCCESS)
        icon = {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = Color(58, 114, 71)
            )
        }
    } else {
        title = LocalLanguage.current.getString(Labels.EXPORT_WARNING)
        confirmClickable = true
        icon = {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colors.error
            )
        }
    }

    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                icon()
                Text(title)
            }
        },

        dismissButton = {
            Button(
                onClick = {
                    onDismissRequest()
                }) {
                Label(Labels.OK)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    for (report in reportList) {
                        report.applyFix(project)
                    }
                    onDismissRequest()
                },
                enabled = confirmClickable
            ) {
                Label(Labels.EXPORT_DIALOG_APPLY_FIX)
            }
        },
        text = {
            for (report in reportList) {
                ShowReport(report)
            }
        },
    )
}

@Composable
fun ShowReport(report: ExportReport) {
    val language = LocalLanguage.current

    val blockId = report.id.block
    val situationId = report.id.situation
    val optionId = report.id.option

    val label = language.getString(report.label)
    val info = report.info?: ""
    val unit = report.unit

    Text(
        "${language.getString(Labels.BLOCK)} $blockId, ${
            language.getString(
                Labels.SITUATION
            )
        } $situationId${optionId?.let { ", ${language.getString(Labels.OPTION)} $it" } ?: ""}: $label $info $unit"
    )

}