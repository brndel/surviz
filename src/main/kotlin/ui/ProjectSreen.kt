package ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
            AppBarMenu(project)
            Row(Modifier.weight(1F)) {
                ProjectPageNavigator(currentPage, { currentPage = it })

                Box(Modifier.weight(1F)) {
                    when (currentPage) {
                        Page.SingleValue -> SingleValuePage(project.configuration)
                        Page.Situation -> SituationPage(project.configuration, project.dataScheme)
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
private fun AppBarMenu(project: Project) {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            TextButton({
                project.saveProjectData("test.svz")
            }) {
                Text("Save")
            }
        }
    }
}

@Composable
private fun ProjectPageNavigator(
    currentPage: Page,
    onNavigate: (Page) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(2.dp)) {
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
        modifier = Modifier.weight(1F),
        onClick = { onNavigate(page) },
        colors = ButtonDefaults.buttonColors(
            if (selected) {
                MaterialTheme.colors.primarySurface
            } else {
                MaterialTheme.colors.surface
            }
        ),
        elevation = null
    ) {
        Label(page.label)
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