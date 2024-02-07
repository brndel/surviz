package ui.window

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import ui.Label
import ui.Labels
import ui.LocalLanguage

@Composable
fun SettingsWindow(onCloseRequest: () -> Unit) {
    Window(title = LocalLanguage.current.getString(Labels.SETTINGS), onCloseRequest = onCloseRequest) {
        Label(Labels.SETTINGS)
    }
}