package ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
fun MainScreen(project: Project?) {
    Surface(color = MaterialTheme.colors.background) {
        if (project == null) {
            WelcomeScreen()
        } else {
            ProjectScreen(project)
        }
    }
}