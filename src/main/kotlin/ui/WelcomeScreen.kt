package ui

import androidx.compose.runtime.Composable
import data.project.Project

/**
 * This screen is visible when no project is currently loaded.
 * From here the user can open the most recent project or load a project from a file.
 * It is also possible to open the settings window from here
 *
 * @param onProjectLoad gets called when a valid [Project] gets loaded by the user
 */
@Composable
fun WelcomeScreen(onProjectLoad: (Project) -> Unit) {
}