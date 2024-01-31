package ui.page.situation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.project.config.ProjectConfiguration
import data.project.config.SituationConfig
import data.project.data.DataScheme
import ui.Label
import ui.Labels
import ui.fields.ColorField

/**
 * On this page the user can edit the situations of a [ProjectConfiguration].
 * Each situation is shown in a tab. The selected tab is determined by currentSituation
 *
 * @param projectConfiguration the [ProjectConfiguration] this page should edit
 * @state currentSituation String holds the name of the currently selected situation
 * @ui SituationTab for the currently selected situation
 */
@Composable
fun SituationPage(projectConfiguration: ProjectConfiguration, dataScheme: DataScheme) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize().padding(4.dp)) {
        Label(Labels.PAGE_SITUATION, style = MaterialTheme.typography.h4)

        Surface(
            Modifier.padding(16.dp).weight(1F),
            color = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(4.dp)
        ) {
            Column {
                TabRow(selectedTabIndex = selectedTab, modifier = Modifier.height(42.dp)) {
                    dataScheme.options.forEachIndexed { index, option ->
                        Tab(index == selectedTab, onClick = { selectedTab = index }) {
                            Text(option.name)
                        }
                    }
                }
                val selectedOption = dataScheme.options[selectedTab]

                val selectedSituation = run {
                    projectConfiguration.getSituationConfig()
                        .getOrPut(
                            selectedOption.name
                        ) {
                            SituationConfig()
                        }
                }

                key(selectedTab) {
                    SituationTab(
                        selectedSituation,
                        selectedOption,
                        projectConfiguration.getSingleValueConfigOrder(),
                        projectConfiguration.getSingleValues()
                            .mapValues { it.value.icon.baseIcon.value }, modifier = Modifier.weight(1F)
                    )
                }
            }
        }
    }
}