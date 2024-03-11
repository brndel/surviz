package ui.window.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window

import ui.Labels
import ui.Language
import ui.LocalLanguage
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
) {
    Window(
        title = LocalLanguage.current.getString(Labels.SETTINGS),
        onCloseRequest = onCloseRequest,
        icon = rememberVectorPainter(Icons.Default.Settings)
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column {
                Box(Modifier.padding(horizontal = 10.dp)) {
                    SettingsWindowContent(
                        language,
                        isDarkMode,
                        onSettingChanged = {
                            saveSettings(
                                settingsFilePath,
                                language.value,
                                isDarkMode.value
                            )
                        })
                }
            }
        }
    }
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