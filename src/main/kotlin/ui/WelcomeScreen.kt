package ui

import LocalGlobalCallbacks
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import data.io.DataManager
import data.project.Project
import data.resources.exceptions.CorruptFileException
import data.resources.exceptions.FileTypeException
import ui.util.ErrorDialog
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.name

/**
 * This screen is visible when no project is currently loaded.
 * From here the user can open the most recent project or load a project from a file.
 * It is also possible to open the settings window from here
 */
@Composable
fun WelcomeScreen() {
    var fileExplorerTarget: FileExplorerTarget? by remember { mutableStateOf(null) }
    var errorDialogLabel: String? by remember { mutableStateOf(null) }

    val globalCallbacks = LocalGlobalCallbacks.current!!

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(painterResource("logo.png"), null, modifier = Modifier.size(64.dp))
                Label(Labels.SURVIZ, style = MaterialTheme.typography.h3)
            }

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                val projectPath = remember { Project.getLastProjectFilePath() }

                WelcomeScreenButton(
                    Labels.LOAD_LAST_PROJECT,
                    Icons.Default.Refresh,
                    enabled = projectPath != null,
                    subLabel = projectPath?.let { { Text(Path(it).name) } }
                ) {
                    globalCallbacks.loadProject(projectPath!!)
                }

                WelcomeScreenButton(
                    Labels.NEW_PROJECT,
                    Icons.Default.Add
                ) {
                    fileExplorerTarget = FileExplorerTarget.NewProject
                }

                WelcomeScreenButton(
                    Labels.LOAD_PROJECT,
                    Icons.Default.Create
                ) {
                    fileExplorerTarget = FileExplorerTarget.LoadProject
                }

                WelcomeScreenButton(
                    Labels.SETTINGS,
                    Icons.Default.Settings
                ) {
                    globalCallbacks.openSettings()
                }
            }
        }
    }

    FilePicker(fileExplorerTarget != null) {
        val target = fileExplorerTarget
        fileExplorerTarget = null

        if (it == null) return@FilePicker

        when (target) {
            FileExplorerTarget.NewProject -> {
                val data = try {
                    DataManager.loadData(File(it.path))
                } catch (e: FileTypeException) {
                    errorDialogLabel = Labels.IMPORT_ERROR_INVALID_FILE_TYPE
                    return@FilePicker
                } catch (e: CorruptFileException) {
                    errorDialogLabel = Labels.IMPORT_ERROR_CORRUPT_FILE
                    return@FilePicker
                }
                globalCallbacks.createProject(data)
            }

            FileExplorerTarget.LoadProject -> {
                globalCallbacks.loadProject(it.path)
            }

            else -> return@FilePicker
        }
    }

    ErrorDialog(errorDialogLabel) {
        errorDialogLabel = null
    }
}

private enum class FileExplorerTarget {
    NewProject,
    LoadProject,
}

@Composable
private fun WelcomeScreenButton(
    label: String,
    icon: ImageVector,
    enabled: Boolean = true,
    subLabel: @Composable (() -> Unit)? = null,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick,
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.size(64.dp),
            enabled = enabled
        ) {
            Icon(icon, null, modifier = Modifier.size(32.dp))
        }
        Label(label, style = MaterialTheme.typography.caption)
        subLabel?.invoke()
    }
}