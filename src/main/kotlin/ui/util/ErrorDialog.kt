package ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.DialogWindow
import ui.Label
import ui.Labels
import ui.LocalLanguage

@Composable
fun ErrorDialog(label: String?, onCloseRequest: () -> Unit) {
    label?.let {
        DialogWindow(onCloseRequest = onCloseRequest, title = LocalLanguage.current.getString(Labels.ERROR)) {
            Surface(
                color = MaterialTheme.colors.background,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Label(it)
                    Button(onCloseRequest) {
                        Label(Labels.OK)
                    }
                }
            }
        }
    }
}