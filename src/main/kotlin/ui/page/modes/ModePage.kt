package ui.page.modes

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ModeOfTravel
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.ProjectConfiguration
import data.project.data.DataScheme
import ui.Label
import ui.Labels
import ui.util.NestedSurface

/**
 * On this page the user can edit the situations of a [ProjectConfiguration].
 * Each situation is shown in a tab. The selected tab is determined by currentSituation
 *
 * @param projectConfiguration the [ProjectConfiguration] this page should edit
 * @state currentSituation String holds the name of the currently selected situation
 * @ui SituationTab for the currently selected situation
 */
@Composable
fun ModePage(projectConfiguration: ProjectConfiguration, dataScheme: DataScheme) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize().padding(10.dp)) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Label(Labels.PAGE_MODES, style = MaterialTheme.typography.h4)
            Icon(Icons.Default.ModeOfTravel, contentDescription = null, tint = MaterialTheme.colors.onBackground)
        }
        NestedSurface {
            Column {
                val dataSchemeOptions = dataScheme.options

                TabRow(
                    selectedTabIndex = selectedTab,
                    modifier = Modifier.height(42.dp),
                    backgroundColor = MaterialTheme.colors.primary,
                ) {
                    dataSchemeOptions.forEachIndexed { index, option ->
                        Tab(index == selectedTab, onClick = { selectedTab = index }) {
                            Text(option.name)
                        }
                    }
                }

                val selectedOption = dataSchemeOptions[selectedTab]

                val selectedSituation = projectConfiguration.getOptionConfig(selectedOption.name)

                key(selectedTab) {
                    ModeTab(
                        selectedSituation,
                        selectedOption,
                        projectConfiguration.getSingleValueConfigOrder(),
                        projectConfiguration.getSingleValues(), modifier = Modifier.weight(1F)
                    )
                }
            }
        }
    }
}