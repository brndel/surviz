package ui

import androidx.compose.runtime.*
import androidx.compose.ui.window.Window
import data.project.Project

/**
 * The root of the ui.
 * This view holds the currently loaded [Project]
 *
 * @state project Project
 * @state isSettingsWindowOpen Boolean
 * @ui WelcomeScreen is visible when no project is loaded
 * @ui ProjectScreen is visible when a valid project is loaded
 */
@Composable
fun MainScreen() {
    var project: Project? by remember { mutableStateOf(null) }
    var settingsWindowOpen by remember { mutableStateOf(false) }

    CompositionLocalProvider(
        LocalSettingsCallback provides { settingsWindowOpen = true }
    ) {
        if (project == null) {
            WelcomeScreen { project = it }
        } else {
            ProjectScreen(project!!)
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

val LocalSettingsCallback = compositionLocalOf { {} }