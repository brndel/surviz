import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import data.project.Project
import data.project.ProjectData
import ui.*
import java.io.File

fun main() = application {
    val windowState =
        rememberWindowState(width = 1700.dp, height = 900.dp, placement = WindowPlacement.Maximized)

    var settingsWindowOpen by remember { mutableStateOf(false) }

    var currentProject by remember { mutableStateOf<Project?>(null) }
    var currentProjectPath by remember { mutableStateOf<String?>(null) }

    val callbacks = object : GlobalCallbacks {
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
                throw Exception("no path and shit")
            }
        }

        override fun closeProject() {
            currentProject = null
            currentProjectPath = null
        }

        override fun openSettings() {
            settingsWindowOpen = true
        }

    }

    fun onKeyEvent(event: KeyEvent): Boolean {
        if (event.type != KeyEventType.KeyDown) return false

        val shortcut = KeyShortcut(
            event.key,
            ctrl = event.isCtrlPressed,
            meta = event.isMetaPressed,
            alt = event.isAltPressed,
            shift = event.isAltPressed
        )

        val action = actionsManager.shortcutMap[shortcut] ?: return false

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
                primary = Color(64, 147, 138)
            )
        ) {
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

            if (settingsWindowOpen) {
                Window(title = LocalLanguage.current.getString(Labels.SETTINGS), onCloseRequest = {
                    settingsWindowOpen = false
                }) {
                    Label(Labels.SETTINGS)
                }
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

    companion object {
        @Composable
        fun loadProject(filePath: String) = LocalGlobalCallbacks.current?.loadProject(filePath)

        @Composable
        fun createProject(projectData: ProjectData) = LocalGlobalCallbacks.current?.createProject(projectData)

        @Composable
        fun saveProject() = LocalGlobalCallbacks.current?.saveProject()

        @Composable
        fun closeProject() = LocalGlobalCallbacks.current?.closeProject()

        @Composable
        fun openSettings() = LocalGlobalCallbacks.current?.openSettings()
    }
}

val LocalGlobalCallbacks = compositionLocalOf<GlobalCallbacks?> { null }