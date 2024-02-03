package ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.DialogWindow
import ui.Label
import ui.Labels

@Composable
fun ErrorDialog(label: String?, onCloseRequest: () -> Unit) {
    label?.let {
        DialogWindow(onCloseRequest = onCloseRequest) {
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