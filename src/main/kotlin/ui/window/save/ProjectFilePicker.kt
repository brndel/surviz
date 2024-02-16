package ui.window.save

import LocalGlobalCallbacks
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import data.project.ProjectData
import ui.Label
import ui.Labels
import kotlin.io.path.pathString

@Composable
fun ProjectFilePicker(
    target: ProjectFilePickerTarget,
    onCloseRequest: () -> Unit
) {
    val callbacks = LocalGlobalCallbacks.current!!

    when (target) {
        ProjectFilePickerTarget.SaveProjectFile -> {
            SaveProjectWindow(onCloseRequest) {
                callbacks.saveProject(it.pathString)
            }
        }

        ProjectFilePickerTarget.LoadProjectFile -> {
            FilePicker(true) {
                if (it != null) {
                    callbacks.loadProject(it.path)
                }
                onCloseRequest()
            }
        }

        ProjectFilePickerTarget.LoadProjectFileFromData -> {
            FilePicker(true) {
                if (it != null) {
                    callbacks.createProject(it.path)
                }
                onCloseRequest()
            }
        }

        ProjectFilePickerTarget.OverrideProjectData -> {
            var projectData by remember { mutableStateOf<ProjectData?>(null) }

            FilePicker(projectData == null) {
                if (it == null) {
                    onCloseRequest()
                } else {
                    val data = callbacks.loadData()

                    if (data != null) {
                        projectData = data
                    } else {
                        onCloseRequest()
                    }

                }
            }

            if (projectData != null) {
                DialogWindow(onCloseRequest) {
                    Surface(
                        color = MaterialTheme.colors.background,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            Modifier.padding(4.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Label(Labels.OVERRIDE_DATA_NOT_FITTING)
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Button({ onCloseRequest() }) {
                                    Label(Labels.CANCEL)
                                }
                                Button({
                                    callbacks.forceLoadData(projectData!!)
                                    onCloseRequest()
                                }) {
                                    Label(Labels.OVERRIDE_DATA_ANYWAYS)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}

enum class ProjectFilePickerTarget {
    SaveProjectFile,
    LoadProjectFile,
    LoadProjectFileFromData,
    OverrideProjectData
}