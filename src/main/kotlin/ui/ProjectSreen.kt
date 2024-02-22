package ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import data.project.Project
import data.project.config.ProjectConfiguration
import data.project.data.IconStorage
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
    var currentPage: Page by remember { mutableStateOf(Page.SingleValue) }

    CompositionLocalProvider(
        LocalIconStorage provides project.iconStorage
    ) {
        Column(Modifier.fillMaxSize()) {
            AppBar()

            Row(Modifier.weight(1F)) {
                ProjectPageNavigator(currentPage, { currentPage = it })

                Box(Modifier.weight(1F)) {
                    when (currentPage) {
                        Page.SingleValue -> SingleValuePage(project.configuration)
                        Page.Situation -> SituationPage(project.configuration, project.getDataScheme())
                        Page.Export -> ExportPage(project)
                    }
                }

                Box(Modifier.weight(1F).zIndex(-0.1F)) {
                    Preview(project)
                }
            }
        }
    }
}

val LocalIconStorage = compositionLocalOf<IconStorage?> { null }

@Composable
private fun ProjectPageNavigator(
    currentPage: Page,
    onNavigate: (Page) -> Unit
) {
    Column(modifier = Modifier.width(128.dp)) {
        for (page in Page.entries) {
            NavButton(
                currentPage == page,
                page,
                onNavigate
            )
        }
    }
}

@Composable
private fun ColumnScope.NavButton(
    selected: Boolean,
    page: Page,
    onNavigate: (Page) -> Unit
) {
    Button(
        modifier = Modifier.weight(1F).fillMaxWidth(),
        onClick = { onNavigate(page) },
        colors = ButtonDefaults.buttonColors(
            if (selected) {
                MaterialTheme.colors.primary
            } else {
                MaterialTheme.colors.surface
            }
        ),
        elevation = null,
        shape = AbsoluteRoundedCornerShape(topRightPercent = 5, bottomRightPercent = 5)
    ) {
        Label(page.label, style = TextStyle(textAlign = TextAlign.Center))
    }
}

/**
 * The pages of the [ProjectScreen]
 */
enum class Page(val label: String) {
    /**
     * Will show the [SingleValuePage]
     */
    SingleValue(Labels.PAGE_SINGLE_VALUE),

    /**
     * Will show the [SituationPage]
     */
    Situation(Labels.PAGE_SITUATION),

    /**
     * Will show the [ExportPage]
     */
    Export(Labels.PAGE_EXPORT)
}