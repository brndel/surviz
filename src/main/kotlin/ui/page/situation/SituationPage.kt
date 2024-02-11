package ui.page.situation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CarCrash
import androidx.compose.material.icons.filled.ModeOfTravel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun SituationPage(projectConfiguration: ProjectConfiguration, dataScheme: DataScheme) {
    var selectedTab by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize().padding(10.dp)) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Label(Labels.PAGE_SITUATION, style = MaterialTheme.typography.h4)
            Icon(Icons.Default.ModeOfTravel, contentDescription = null, tint = MaterialTheme.colors.onBackground)
        }
        NestedSurface {
            Column {
                TabRow(selectedTabIndex = selectedTab, modifier = Modifier.height(42.dp), backgroundColor = MaterialTheme.colors.primary) {
                    dataScheme.options.forEachIndexed { index, option ->
                        Tab(index == selectedTab, onClick = { selectedTab = index }) {
                            Text(option.name)
                        }
                    }
                }
                val selectedOption = dataScheme.options[selectedTab]

                val selectedSituation = projectConfiguration.getSituationConfig(selectedOption.name)

                key(selectedTab) {
                    SituationTab(
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