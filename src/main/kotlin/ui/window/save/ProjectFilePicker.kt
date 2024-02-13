package ui.window.save

import LocalGlobalCallbacks
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.window.DialogWindow
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import data.project.ProjectData
import data.resources.exceptions.CorruptFileException
import data.resources.exceptions.FileTypeException
import ui.Label
import ui.Labels
import ui.util.ErrorDialog
import kotlin.io.path.pathString

@Composable
fun ProjectFilePicker(
    target: ProjectFilePickerTarget,
    onCloseRequest: () -> Unit
) {
    val callbacks = LocalGlobalCallbacks.current!!
    var errorDialogLabel: String? by remember { mutableStateOf(null) }

    when (target) {
        ProjectFilePickerTarget.SaveProjectFile -> {
            SaveProjectWindow(onCloseRequest) {
                callbacks.saveProject(it.pathString)
            }
        }

        ProjectFilePickerTarget.LoadProjectFile -> {
            FilePicker(true) {
                if (it != null) {
                    try {
                        callbacks.loadProject(it.path)
                    } catch (e: FileTypeException) {
                        errorDialogLabel = Labels.IMPORT_ERROR_INVALID_FILE_TYPE
                        return@FilePicker
                    } catch (e: CorruptFileException) {
                        errorDialogLabel = Labels.IMPORT_ERROR_CORRUPT_FILE
                        return@FilePicker
                    }
                }
                onCloseRequest()
            }
        }

        ProjectFilePickerTarget.LoadProjectFileFromData -> {
            FilePicker(true) {
                if (it != null) {
                    try {
                        callbacks.createProject(it.path)
                    } catch (e: FileTypeException) {
                        errorDialogLabel = Labels.IMPORT_ERROR_INVALID_FILE_TYPE
                        return@FilePicker
                    } catch (e: CorruptFileException) {
                        errorDialogLabel = Labels.IMPORT_ERROR_CORRUPT_FILE
                        return@FilePicker
                    }
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
                    val data = try {
                        callbacks.loadData(it.path)
                    } catch (e: FileTypeException) {
                        errorDialogLabel = Labels.IMPORT_ERROR_INVALID_FILE_TYPE
                        return@FilePicker
                    } catch (e: CorruptFileException) {
                        errorDialogLabel = Labels.IMPORT_ERROR_CORRUPT_FILE
                        return@FilePicker
                    }

                    if (data != null) {
                        projectData = data
                    } else {
                        onCloseRequest()
                    }
                }
            }

            if (projectData != null) {
                DialogWindow(onCloseRequest) {
                    Column {
                        Label(Labels.OVERRIDE_DATA_NOT_FITTING)
                        Row {
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

    ErrorDialog(errorDialogLabel) {
        onCloseRequest()
    }
}

enum class ProjectFilePickerTarget {
    SaveProjectFile,
    LoadProjectFile,
    LoadProjectFileFromData,
    OverrideProjectData
}