package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.project.Project
import data.project.config.ProjectConfiguration
import ui.page.export.ExportPage
import ui.page.singleValue.SingleValuePage
import ui.page.situation.SituationPage

/**
 * This screen is visible when a valid [Project] is loaded.
 * From here the user can edit the [ProjectConfiguration] of the loaded [Project].
 * This screen has multiple pages. There is always only one page visible.
 *
 * @param project the [Project] that can be edited on this screen
 * @state currentPage Page the currently visible page
 * @ui SingleValuePage when SingleValue is selected
 * @ui SituationPage when Situation is selected
 * @ui ExportPage when Export is selected
 */
@Composable
fun ProjectScreen(project: Project) {
    var currentPage: Page by mutableStateOf(Page.SingleValue)

    when (currentPage) {
        Page.SingleValue -> TODO()
        Page.Situation -> TODO()
        Page.Export -> TODO()
    }
}

/**
 * The pages of the [ProjectScreen]
 */
enum class Page {
    /**
     * Will show the [SingleValuePage]
     */
    SingleValue,

    /**
     * Will show the [SituationPage]
     */
    Situation,

    /**
     * Will show the [ExportPage]
     */
    Export
}