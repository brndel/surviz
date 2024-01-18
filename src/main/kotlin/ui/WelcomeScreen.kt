package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import data.io.DataManager
import data.project.Project
import java.io.File

/**
 * This screen is visible when no project is currently loaded.
 * From here the user can open the most recent project or load a project from a file.
 * It is also possible to open the settings window from here
 *
 * @param onProjectLoad gets called when a valid [Project] gets loaded by the user
 */
@Composable
fun WelcomeScreen(onProjectLoad: (Project) -> Unit) {
    var fileExplorerTarget: FileExplorerTarget? by remember { mutableStateOf(null) }

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
                WelcomeScreenButton(
                    Labels.LOAD_LAST_PROJECT,
                    Icons.Default.Refresh,
                    enabled = false
                ) {
                    val projectPath = Project.getLastProjectFilePath()
                    val project = Project.loadProjectFromFile(File(projectPath))
                    onProjectLoad(project)
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

                val openSettingsCallback = LocalSettingsCallback.current

                WelcomeScreenButton(
                    Labels.SETTINGS,
                    Icons.Default.Settings
                ) {
                    openSettingsCallback.invoke()
                }
            }
        }
    }

    FilePicker(fileExplorerTarget != null) {
        if (it == null) return@FilePicker

        val file = File(it.path)

        val project = when (fileExplorerTarget) {
            FileExplorerTarget.NewProject -> {
                val data = DataManager.loadData(file)
                Project.newProjectWithData(data)
            }

            FileExplorerTarget.LoadProject -> {
                Project.loadProjectFromFile(file)
            }

            else -> return@FilePicker
        }

        onProjectLoad(project)

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
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick, contentPadding = PaddingValues(4.dp), modifier = Modifier.size(64.dp), enabled = enabled) {
            Icon(icon, null, modifier = Modifier.size(32.dp))
        }
        Label(label, style = MaterialTheme.typography.caption)
    }
}