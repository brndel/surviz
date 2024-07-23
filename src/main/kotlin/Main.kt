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
import data.resources.exceptions.CorruptFileException
import data.resources.exceptions.FileTypeException
import data.resources.exceptions.InvalidVersionException
import kotlinx.coroutines.delay
import ui.*
import ui.util.ErrorDialog
import ui.util.LocalStatusLabel
import ui.util.StatusMessage
import ui.util.StatusMessageType
import ui.window.help.HelpEntry
import ui.window.help.HelpWindow
import ui.window.save.ProjectFilePicker
import ui.window.save.ProjectFilePickerTarget
import ui.window.settings.SettingsWindow
import util.platformPath
import java.io.File
import java.io.FileInputStream
import java.util.Properties

const val WINDOW_WIDTH = 700
const val WINDOW_HEIGHT = 500

fun main() = application {
    val screenSize = remember {
        java.awt.Toolkit.getDefaultToolkit().screenSize
    }

    var settingsWindowOpen by remember { mutableStateOf(false) }
    var helpWindowOpen by remember { mutableStateOf(false) }
    var focusedHelpEntry by remember { mutableStateOf<HelpEntry?>(null) }

    var currentProject by remember { mutableStateOf<Project?>(null) }
    var currentProjectPath by remember { mutableStateOf<String?>(null) }

    var statusMessage by remember { mutableStateOf<StatusMessage?>(null) }

    //999 values
    val has999 = remember { mutableStateOf(false) }
    val value999 = remember { mutableStateOf(999.0) }


    LaunchedEffect(statusMessage) {
        val msg = statusMessage ?: return@LaunchedEffect

        delay(msg.type.duration)
        statusMessage = null
    }

    val mainWindowState = rememberWindowState(
        width = WINDOW_WIDTH.dp,
        height = WINDOW_HEIGHT.dp,
        placement = WindowPlacement.Floating,
        position = WindowPosition(
            (screenSize.width.dp - WINDOW_WIDTH.dp) / 2,
            (screenSize.height.dp - WINDOW_HEIGHT.dp) / 2
        )
    )

    fun onProjectLoad() {
        mainWindowState.placement = WindowPlacement.Maximized
    }

    fun onProjectClose() {
        mainWindowState.placement = WindowPlacement.Floating
    }

    var filePickerTarget by remember { mutableStateOf<ProjectFilePickerTarget?>(null) }

    var errorDialogLabel by remember { mutableStateOf<String?>(null) }

    fun catchLabelExceptions(inner: () -> Unit) {
        try {
            inner()
        } catch (e: FileTypeException) {
            errorDialogLabel = Labels.IMPORT_ERROR_INVALID_FILE_TYPE
        } catch (e: CorruptFileException) {
            errorDialogLabel = Labels.IMPORT_ERROR_CORRUPT_FILE
        } catch (e: InvalidVersionException) {
            errorDialogLabel = Labels.IMPORT_ERROR_WRONG_VERSION
        }
    }

    val callbacks = remember {
        object : GlobalCallbacks {
            override fun loadProject(filePath: String?) {
                if (filePath == null) {
                    filePickerTarget = ProjectFilePickerTarget.LoadProjectFile
                } else {
                    catchLabelExceptions {
                        val project = Project.loadProjectFromFile(File(filePath))
                        currentProject = project
                        currentProjectPath = filePath
                        onProjectLoad()
                        setStatusLabel(Labels.STATUS_MSG_PROJECT_LOADED)
                    }
                }

            }

            override fun createProject(filePath: String?) {
                if (filePath == null) {
                    filePickerTarget = ProjectFilePickerTarget.LoadProjectFileFromData
                } else {
                    catchLabelExceptions {
                        val data = DataManager.loadData(File(filePath))
                        currentProject = Project.newProjectWithData(data)
                        onProjectLoad()
                        setStatusLabel(Labels.STATUS_MSG_PROJECT_CREATED)
                    }
                }
            }

            override fun saveProject(filePath: String?) {
                if (filePath != null) {
                    currentProjectPath = filePath
                }

                if (currentProjectPath != null) {
                    catchLabelExceptions {
                        currentProject?.saveProjectData(currentProjectPath!!)
                        setStatusLabel(Labels.STATUS_MSG_PROJECT_SAVED)
                    }
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
                onProjectClose()
                setStatusLabel(Labels.STATUS_MSG_PROJECT_CLOSED)
            }

            override fun openSettings() {
                settingsWindowOpen = true
            }

            override fun openHelp(focusedEntry: HelpEntry?) {
                helpWindowOpen = true
                focusedHelpEntry = focusedEntry
            }

            override fun loadData(filePath: String?): ProjectData? {
                if (currentProject == null) throw IllegalStateException("no project is currently loaded")
                if (filePath == null) {
                    filePickerTarget = ProjectFilePickerTarget.OverrideProjectData
                } else {
                    var data: ProjectData? = null
                    catchLabelExceptions {
                        data = DataManager.loadData(File(filePath))
                    }
                    if (data != null) {
                        val success = currentProject!!.loadProjectData(data!!)
                        if (!success) {
                            return data
                        } else {
                            setStatusLabel(Labels.STATUS_MSG_SIMULATION_DATA_LOADED)
                        }
                    }
                }
                return null
            }

            override fun forceLoadData(projectData: ProjectData) {
                currentProject?.loadProjectData(projectData, true)
                setStatusLabel(Labels.STATUS_MSG_SIMULATION_DATA_LOADED)
            }

            override fun setStatusLabel(label: String, type: StatusMessageType) {
                statusMessage = StatusMessage(label, type)
            }

            override fun has999Value(): Boolean {
                return has999.value
            }

            override fun get999Value(): Double {
                return value999.value
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
        onError = Color(255, 255, 255),
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
        error = Color(197, 74, 68),
        onError = Color(255, 255, 255),
    )

    val isDarkTheme = remember { mutableStateOf(false) }

    // load settings
    loadSettings(
        setLanguage = { language.value = it },
        setDarkMode = { isDarkTheme.value = it },
        setHas999 = {has999.value = it},
        set999Value = {value999.value = it},
    )

    val colors = if (isDarkTheme.value) {
        darkColors
    } else {
        lightColors
    }

    CompositionLocalProvider(
        LocalLanguage provides language.value,
        LocalGlobalCallbacks provides callbacks,
        LocalStatusLabel provides statusMessage
    ) {
        MaterialTheme(
            colors = colors
        ) {

            MainWindow(currentProject, currentProjectPath, mainWindowState) {
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
                    isDarkTheme,
                    has999,
                    value999
                )
            }

            if (helpWindowOpen) {
                HelpWindow(
                    onCloseRequest = {
                        helpWindowOpen = false
                    },
                    focusedHelpEntry
                )
            }

            ErrorDialog(errorDialogLabel) {
                errorDialogLabel = null
            }
        }
    }
}

@Composable
fun ApplicationScope.MainWindow(
    currentProject: Project?,
    currentProjectPath: String?,
    windowState: WindowState,
    onKeyEvent: (KeyEvent) -> Boolean
) {
    var isExitDialogVisible by remember { mutableStateOf(false) }

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
        onCloseRequest = {
            if (currentProject != null) {
                isExitDialogVisible = true
            } else {
                exitApplication()
            }
        },
        state = windowState,
        title = windowTitle,
        icon = painterResource("logo.png"),
        onKeyEvent = { onKeyEvent(it) }) {

        MainScreen(currentProject)

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
    fun openHelp(focusedEntry: HelpEntry? = null)
    fun loadData(filePath: String? = null): ProjectData?
    fun forceLoadData(projectData: ProjectData)
    fun setStatusLabel(label: String, type: StatusMessageType = StatusMessageType.Info)
    fun has999Value(): Boolean
    fun get999Value(): Double
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

private fun loadSettings(
    setLanguage: (Language) -> Unit,
    setDarkMode: (Boolean) -> Unit,
    setHas999: (Boolean) -> Unit,
    set999Value: (Double) -> Unit
) {
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

        val has999 = prop.getProperty("has999")
        if (has999 != null){
            setHas999(has999.toBoolean())
        }

        val value999 = prop.getProperty("value999")
        if (value999 != null) {
            set999Value(value999.toDouble())
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