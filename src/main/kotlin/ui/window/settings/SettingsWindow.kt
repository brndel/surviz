package ui.window.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.LeadingIconTab
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import data.project.Project

import ui.Labels
import ui.Language
import ui.LocalLanguage
import ui.util.NestedSurface
import ui.window.settings.tabs.GeneralSettingsTab
import ui.window.settings.tabs.HelpTab
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.Properties

@Composable
fun SettingsWindow(
    onCloseRequest: () -> Unit,
    settingsFilePath: String,
    language: MutableState<Language>,
    isDarkMode: MutableState<Boolean>,
    openedTab: Int = 0
) {
    val (selectedTabIndex, setSelectedTabIndex) = remember { mutableStateOf(openedTab) }

    Window(
        title = LocalLanguage.current.getString(Labels.SETTINGS),
        onCloseRequest = onCloseRequest,
        icon = rememberVectorPainter(Icons.Default.Settings)
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    backgroundColor = MaterialTheme.colors.primary
                ) {
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

                Box(Modifier.padding(horizontal = 10.dp)) {
                    when (selectedTabIndex) {
                        0 -> GeneralSettingsTab(
                            language,
                            isDarkMode,
                            onSettingChanged = {
                                saveSettings(
                                    settingsFilePath,
                                    language.value,
                                    isDarkMode.value
                                )
                            })

                        1 -> HelpTab()
                    }
                }
            }
        }
    }
}

@Composable
fun HighlightedText(label: String) {
    val text = LocalLanguage.current.getString(label)
    val regex = "\\$(.*?)\\$".toRegex()
    val matches = regex.findAll(text)
    var currentIndex = 0
    val annotatedString = buildAnnotatedString {
        matches.forEach { matchResult ->
            val value = matchResult.groupValues[1]
            val startIndex = matchResult.range.first
            val endIndex = matchResult.range.last - 1
            append(text.substring(currentIndex, startIndex))
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append(value)
            }
            currentIndex = endIndex + 2 // Move index past the closing **
        }
        append(text.substring(currentIndex, text.length))
    }
    Text(annotatedString)
}

@Composable
fun HighlightedHeading(
    label: String, style: TextStyle? = null, modifier: Modifier = Modifier
) {
    val textStyle = style
        ?: TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize) // Use MaterialTheme.typography.subtitle1.fontSize if style is null

    Text(
        LocalLanguage.current.getString(label),
        style = textStyle.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary),
        modifier = modifier
    )
}

fun saveSettings(path: String, language: Language, darkMode: Boolean) {
    val file = File(path)

    if (!file.exists()) {
        file.parentFile.mkdirs()
        file.createNewFile()
    }
    val prop = Properties()
    prop.load(FileInputStream(file))

    prop.setProperty("lang", language.toCode())
    prop.setProperty("dark_mode", darkMode.toString())

    FileOutputStream(file).use { output ->
        prop.forEach { (key, value) ->
            output.write("$key=$value\n".toByteArray())
        }
    }
}