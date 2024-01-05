package ui

import androidx.compose.runtime.*
import data.project.Project

/**
 * TODO
 *
 */
@Composable
fun MainScreen() {
    var project: Project? by remember { mutableStateOf(null) }

    if (project == null) {
        StartingScreen { project = it }
    } else {
        ProjectScreen(project!!)
    }
}