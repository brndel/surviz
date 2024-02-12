package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.rememberWindowState
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
fun MainScreen(project: Project?, setWindowState: (WindowState) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        if (project == null) {
            WelcomeScreen()
            setWindowState(
                rememberWindowState(
                    width = 700.dp,
                    height = 500.dp,
                    placement = WindowPlacement.Floating,
                    position = WindowPosition((1920.dp - 700.dp) / 2, (1080.dp - 500.dp) / 2)
                )
            )
        } else {
            ProjectScreen(project)
            setWindowState(rememberWindowState(placement = WindowPlacement.Maximized))
        }
    }
}