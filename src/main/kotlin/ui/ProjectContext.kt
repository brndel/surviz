package ui

import androidx.compose.runtime.staticCompositionLocalOf
import data.project.Project

val LocalProject =
    staticCompositionLocalOf<Project> {
        error("Project is not available in the current composition")
    }
