import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.*
import data.project.Project
import data.project.ProjectData
import ui.*
import ui.fields.DirectoryPickerField
import ui.window.settings.SettingsWindow
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.io.path.pathString

fun main() = application {
    var settingsWindowOpen by remember { mutableStateOf(false) }

    var currentProject by remember { mutableStateOf<Project?>(null) }
    var currentProjectPath by remember { mutableStateOf<String?>(null) }

    var filePickerOpen by remember { mutableStateOf(false) }

    val callbacks = remember {
        object : GlobalCallbacks {
            override fun loadProject(filePath: String) {
                val project = Project.loadProjectFromFile(File(filePath))
                currentProject = project
                currentProjectPath = filePath
            }

            override fun createProject(projectData: ProjectData) {
                currentProject = Project.newProjectWithData(projectData)
                currentProjectPath = null
            }

            override fun saveProject() {
                if (currentProjectPath != null) {
                    currentProject?.saveProjectData(currentProjectPath!!)
                } else {
                    filePickerOpen = true
                }
            }

            override fun closeProject() {
                currentProject = null
                currentProjectPath = null
            }

            override fun openSettings() {
                settingsWindowOpen = true
            }

            override fun loadData() {
                TODO()
            }
        }
    }

    fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.type != KeyEventType.KeyDown) return false

        val action = actionsManager.shortcutMap[Shortcut(event)] ?: return false

        action.onClick(callbacks)

        return true
    }

    val language = remember {
        val langCode = System.getProperty("user.language")

        Language.fromCode(langCode)
    }

    CompositionLocalProvider(
        LocalLanguage provides language,
        LocalGlobalCallbacks provides callbacks
    ) {
        MaterialTheme(
            colors = lightColors(
                background = Color(240, 240, 240),
                surface = Color(230, 230, 230),
                primary = Color(64, 147, 138),
                primaryVariant = Color(71, 128, 117),
                secondary = Color(20, 108, 201),
                secondaryVariant = Color(43, 100, 161),
                onSecondary = Color.White,
            )
        ) {

            MainWindow(currentProject, currentProjectPath) {
                onKeyEvent(it)
            }

            if (filePickerOpen) {
                ProjectPathPicker({ filePickerOpen = false }) {
                    currentProjectPath = it.pathString
                    callbacks.saveProject()
                }
            }

            if (settingsWindowOpen) {
                SettingsWindow {
                    settingsWindowOpen = false
                }
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
    val windowState =
        rememberWindowState(width = 1700.dp, height = 900.dp, placement = WindowPlacement.Maximized)

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
        onCloseRequest = ::exitApplication,
        state = windowState,
        title = windowTitle,
        icon = painterResource("logo.png"),
        onKeyEvent = { onKeyEvent(it) }) {

        MainScreen(currentProject)
    }
}

@Composable
fun ProjectPathPicker(
    onCloseRequest: () -> Unit,
    onFilePicked: (Path) -> Unit
) {
    var directory: String by remember { mutableStateOf(Project.defaultSaveDirectory) }

    var filename: String by remember { mutableStateOf(Project.DEFAULT_FILE_NAME) }

    val path by derivedStateOf {
        var p = Path(directory, filename)
        if (p.extension == "") {
            p = Path(p.pathString.removeSuffix(".") + ".svz")
        }

        p
    }

    DialogWindow(onCloseRequest = onCloseRequest) {
        Column(
            Modifier.padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            DirectoryPickerField(directory, { directory = it })
            TextField(filename, { filename = it })
            Text(path.pathString)
            Button({
                onCloseRequest()
                onFilePicked(path)
            }) {
                Label(Labels.ACTION_SAVE)
            }
        }
    }
}

interface GlobalCallbacks {
    fun loadProject(filePath: String)
    fun createProject(projectData: ProjectData)
    fun saveProject()
    fun closeProject()
    fun openSettings()
    fun loadData()

    companion object {
        @Composable
        fun loadProject(filePath: String) = LocalGlobalCallbacks.current?.loadProject(filePath)

        @Composable
        fun createProject(projectData: ProjectData) =
            LocalGlobalCallbacks.current?.createProject(projectData)

        @Composable
        fun saveProject() = LocalGlobalCallbacks.current?.saveProject()

        @Composable
        fun closeProject() = LocalGlobalCallbacks.current?.closeProject()

        @Composable
        fun openSettings() = LocalGlobalCallbacks.current?.openSettings()
    }
}

val LocalGlobalCallbacks = compositionLocalOf<GlobalCallbacks?> { null }