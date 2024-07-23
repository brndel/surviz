package ui.window.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sun.jvm.hotspot.oops.DoubleField
import ui.Label
import ui.Labels
import ui.Language
import ui.LocalLanguage
import ui.fields.DoubleField
import ui.fields.IntField
import ui.fields.OptionsField
import ui.util.NestedSurface
import ui.window.help.HighlightedHeading

@Composable
fun SettingsWindowContent(
    language: MutableState<Language>,
    isDarkMode: MutableState<Boolean>,
    has999: MutableState<Boolean>,
    value999: MutableState<Double>,
    onSettingChanged: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.padding(top = 10.dp)
            ) {
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
                                onValueChange = {
                                    language.value = it
                                    onSettingChanged()
                                },
                                Language.entries
                            ) {
                                LocalLanguage.current.getString(it.label)
                            }
                        }
                    }
                }
                // darkmode
                NestedSurface(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Default.DarkMode, null, tint = MaterialTheme.colors.primary)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            HighlightedHeading(
                                Labels.SETTINGS_CHANGE_DARK_MODE,
                                style = MaterialTheme.typography.h6
                            )
                            Switch(
                                isDarkMode.value, onCheckedChange = {
                                    isDarkMode.value = it
                                    onSettingChanged()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colors.primary,
                                    uncheckedThumbColor = MaterialTheme.colors.primary,
                                )
                            )
                        }
                    }
                }
                // 999 values
                NestedSurface(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Default.MoreHoriz, null, tint = MaterialTheme.colors.primary)
                        Column(
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            HighlightedHeading(
                                Labels.SETTINGS_999_TITLE,
                                style = MaterialTheme.typography.h6
                            )
                            Switch(
                                has999.value, onCheckedChange = {
                                    has999.value = it
                                    onSettingChanged()
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colors.primary,
                                    uncheckedThumbColor = MaterialTheme.colors.primary,
                                )
                            )
                            if (has999.value) {
                                DoubleField(value999.value, onValueChange = {
                                    value999.value = it
                                }, label = { Label(Labels.VALUE) },
                                    modifier = Modifier.width(200.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}