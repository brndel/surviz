package ui.window.settings.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Language
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.Label
import ui.Labels
import ui.Language
import ui.LocalLanguage
import ui.fields.OptionsField
import ui.util.NestedSurface
import ui.window.settings.HighlightedHeading

@Composable
fun GeneralSettingsTab(onLanguageChange: (Language) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(top = 10.dp)) {
                // change language
                NestedSurface(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Default.Language, null, tint = MaterialTheme.colors.primary)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            HighlightedHeading(
                                Labels.SETTINGS_CHANGE_LANGUAGE,
                                style = MaterialTheme.typography.h6
                            )
                            OptionsField(
                                value = LocalLanguage.current,
                                onValueChange = { onLanguageChange(it) },
                                Language.entries
                            ) {
                                Label(it.toString())
                            }
                        }
                    }

                }
            }
        }
    }
}