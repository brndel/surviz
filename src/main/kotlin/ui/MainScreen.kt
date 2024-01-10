package ui

import androidx.compose.runtime.*
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

    if (project == null) {
        WelcomeScreen { project = it }
    } else {
        ProjectScreen(project!!)
    }
}