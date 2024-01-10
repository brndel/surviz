package ui.page.situation

import androidx.compose.runtime.Composable
import data.project.config.ProjectConfiguration

/**
 * On this page the user can edit the situations of a [ProjectConfiguration].
 * Each situation is shown in a tab. The selected tab is determined by currentSituation
 *
 * @param projectConfiguration the [ProjectConfiguration] this page should edit
 * @state currentSituation String holds the name of the currently selected situation
 * @ui SituationTab for the currently selected situation
 */
@Composable
fun SituationPage(projectConfiguration: ProjectConfiguration) {

}