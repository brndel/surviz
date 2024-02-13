import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import data.io.DataManager
import data.project.Project
import data.project.ProjectData
import ui.*
import ui.window.save.ProjectFilePicker
import ui.window.save.ProjectFilePickerTarget
import ui.window.settings.SettingsWindow
import util.platformPath
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties
import kotlin.system.exitProcess

fun main() = application {
    var settingsWindowOpen by remember { mutableStateOf(false) }

    var currentProject by remember { mutableStateOf<Project?>(null) }
    var currentProjectPath by remember { mutableStateOf<String?>(null) }

    var filePickerTarget by remember { mutableStateOf<ProjectFilePickerTarget?>(null) }

    val callbacks = remember {
        object : GlobalCallbacks {
            override fun loadProject(filePath: String?) {
                if (filePath == null) {
                    filePickerTarget = ProjectFilePickerTarget.LoadProjectFile
                } else {
                    val project = Project.loadProjectFromFile(File(filePath))
                    currentProject = project
                    currentProjectPath = filePath
                }

            }

            override fun createProject(filePath: String?) {
                if (filePath == null) {
                    filePickerTarget = ProjectFilePickerTarget.LoadProjectFileFromData
                } else {
                    val data = DataManager.loadData(File(filePath))
                    currentProject = Project.newProjectWithData(data)
                }
            }

            override fun saveProject(filePath: String?) {
                if (filePath != null) {
                    currentProjectPath = filePath
                }

                if (currentProjectPath != null) {
                    currentProject?.saveProjectData(currentProjectPath!!)
                } else {
                    filePickerTarget = ProjectFilePickerTarget.SaveProjectFile
                }
            }

            override fun saveProjectAs() {
                filePickerTarget = ProjectFilePickerTarget.SaveProjectFile
            }

            override fun closeProject() {
                currentProject = null
                currentProjectPath = null
            }

            override fun openSettings() {
                settingsWindowOpen = true
            }

            override fun loadData(filePath: String?): ProjectData? {
                if (currentProject == null) throw IllegalStateException("no project is currently loaded")
                if (filePath == null) {
                    filePickerTarget = ProjectFilePickerTarget.OverrideProjectData
                } else {
                    val data = DataManager.loadData(File(filePath))

                    val success = currentProject!!.loadProjectData(data)
                    if (!success) {
                        return data
                    }
                }
                return null
            }

            override fun forceLoadData(projectData: ProjectData) {
                currentProject?.loadProjectData(projectData, true)
            }
        }
    }

    fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.type != KeyEventType.KeyDown) return false

        val action = actionsManager.shortcutMap[Shortcut(event)] ?: return false

        action.onClick(callbacks)

        return true
    }

    // set language
    val language = remember {
        val langCode = System.getProperty("user.language")

        mutableStateOf(Language.fromCode(langCode))
    }

    // set colors
    val lightColors = lightColors(
        surface = Color(235, 235, 235),
        primary = Color(64, 147, 138),
        primaryVariant = Color(85, 180, 169),
        secondary = Color(34, 47, 89),
        secondaryVariant = Color(50, 70, 133),
        onSecondary = Color.White,
        error = Color(160, 55, 49),
    )
    val darkColors = darkColors(
        background = Color(18, 18, 18),
        surface = Color(31, 31, 31),
        primary = Color(64, 147, 138),
        primaryVariant = Color(85, 180, 169),
        secondary = Color(50, 70, 133),
        secondaryVariant = Color(62, 85, 163),
        onPrimary = Color.White,
        onSecondary = Color.White,
        onSurface = Color.White,
        onBackground = Color.White,
        error = Color(160, 55, 49),
    )

    val isDarkTheme = remember { mutableStateOf(false) }


    // load settings
    loadSettings(
        setLanguage = { language.value = it },
        setDarkMode = { isDarkTheme.value = it }
    )

    val colors = if (isDarkTheme.value) {
        darkColors
    } else {
        lightColors
    }

    CompositionLocalProvider(
        LocalLanguage provides language.value,
        LocalGlobalCallbacks provides callbacks
    ) {
        MaterialTheme(
            colors = colors
        ) {

            MainWindow(currentProject, currentProjectPath) {
                onKeyEvent(it)
            }

            if (filePickerTarget != null) {
                ProjectFilePicker(filePickerTarget!!) {
                    filePickerTarget = null
                }
            }

            if (settingsWindowOpen) {
                SettingsWindow(
                    {
                        settingsWindowOpen = false
                    },
                    SettingsFile,
                    language,
                    isDarkTheme
                )
            }
        }
    }
}

@Composable
fun ApplicationScope.MainWindow(
    currentProject: Project?,
    currentProjectPath: String?,
    onKeyEvent: (KeyEvent) -> Boolean
) {
    var isExitDialogVisible by remember { mutableStateOf(false) }

    var windowState by remember {
        mutableStateOf(
            WindowState(
                width = 700.dp,
                height = 500.dp,
                placement = WindowPlacement.Floating,
                position = WindowPosition((1920.dp - 700.dp) / 2, (1080.dp - 500.dp) / 2)
            )
        )
    }


    val lang = LocalLanguage.current
    val windowTitle by derivedStateOf {
        var title = lang.getString(Labels.SURVIZ)
        if (currentProject != null) {
            val filePath = currentProjectPath ?: lang.getString(Labels.UNNAMED_PROJECT)

            title = "$title - $filePath"
        }

        title
    }

    Window(
        onCloseRequest = { isExitDialogVisible = true },
        state = windowState,
        title = windowTitle,
        icon = painterResource("logo.png"),
        onKeyEvent = { onKeyEvent(it) }) {

        MainScreen(currentProject, setWindowState = { windowState = it })

        if (isExitDialogVisible) {
            CloseDialog(
                onCloseRequest = ::exitApplication,
                onDismissRequest = { isExitDialogVisible = false })
        }
    }


}

interface GlobalCallbacks {
    fun loadProject(filePath: String? = null)
    fun createProject(filePath: String? = null)
    fun saveProject(filePath: String? = null)
    fun saveProjectAs()
    fun closeProject()
    fun openSettings()
    fun loadData(filePath: String? = null): ProjectData?
    fun forceLoadData(projectData: ProjectData)
}

val LocalGlobalCallbacks = compositionLocalOf<GlobalCallbacks?> { null }

val SettingsFile: String by lazy {
    platformPath(windows = {
        "C:\\Users\\$it\\AppData\\Local\\SurViz\\settings.cfg"
    }, linux = {
        "/home/$it/.local/share/SurViz/settings.cfg"
    }, mac = {
        "/Users/$it/.local/share/SurViz/settings.cfg"
    })
}

private fun loadSettings(setLanguage: (Language) -> Unit, setDarkMode: (Boolean) -> Unit) {
    val file = File(SettingsFile)

    if (file.exists()) {
        val prop = Properties()
        prop.load(FileInputStream(file))

        val languageCode = prop.getProperty("lang")
        if (languageCode != null) {
            setLanguage(Language.fromCode(languageCode))
        }

        val isDarkMode = prop.getProperty("dark_mode")
        if (isDarkMode != null) {
            setDarkMode(isDarkMode.toBoolean())
        }
    }
}

@Composable
private fun CloseDialog(onCloseRequest: () -> Unit, onDismissRequest: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Icon(Icons.Default.Warning, null, tint = MaterialTheme.colors.error)
                    Label(Labels.CLOSE_DIALOG_TITLE)
                }
        },
        text = {
               Label(Labels.CLOSE_DIALOG_TEXT)
        },
        dismissButton = {
            Button(onClick = onDismissRequest) {
                Label(Labels.CANCEL)
            }
        },
        confirmButton = {
            Button(onClick = onCloseRequest) {
                Label(Labels.CLOSE_DIALOG_CONFIRM)
            }
        }
    )
}