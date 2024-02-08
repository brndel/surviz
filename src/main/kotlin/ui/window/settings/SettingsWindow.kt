package ui.window.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import data.project.Project

import ui.Labels
import ui.LocalLanguage
import ui.util.NestedSurface
import ui.window.settings.tabs.GeneralSettingsTab
import ui.window.settings.tabs.HelpTab

@Composable
fun SettingsWindow(onCloseRequest: () -> Unit, project: Project?, openedTab: Int = 0) {
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(openedTab) }

    Window(
        title = LocalLanguage.current.getString(Labels.SETTINGS),
        onCloseRequest = onCloseRequest,
        icon = rememberVectorPainter(Icons.Default.Settings)
    ) {
        NestedSurface {
            Column {
                TabRow(selectedTabIndex = selectedTabIndex) {
                    LeadingIconTab(
                        text = { Text(LocalLanguage.current.getString(Labels.SETTINGS_GENERAL)) },
                        selected = selectedTabIndex == 0,
                        onClick = { setSelectedTabIndex(0) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    )
                    LeadingIconTab(
                        text = { Text(LocalLanguage.current.getString(Labels.SETTINGS_HELP)) },
                        selected = selectedTabIndex == 1,
                        onClick = { setSelectedTabIndex(1) },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.HelpCenter,
                                contentDescription = null
                            )
                        }
                    )
                }

                Box(Modifier.padding(8.dp)) {
                    when (selectedTabIndex) {
                        0 -> GeneralSettingsTab()
                        1 -> HelpTab()
                    }
                }
            }
        }
    }
}