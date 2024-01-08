package ui.page.singleValue

import androidx.compose.runtime.Composable
import data.project.config.ProjectConfiguration


/**
 * On this page the user can edit the single values of a [ProjectConfiguration]
 *
 * @param projectConfig the [ProjectConfiguration] this page should edit
 * @ui SingleValueCard for each single value in the given [ProjectConfiguration] a [SingleValueCard] will be shown.
 * The order of the single values can be edited by dragging and dropping the [SingleValueCard]
 */
@Composable
fun SingleValuePage(projectConfig: ProjectConfiguration) {
}