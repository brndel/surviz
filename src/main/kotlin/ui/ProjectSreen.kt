package ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import data.project.Project


@Composable
fun ProjectScreen(project: Project) {
    var page: Page by mutableStateOf(Page.SingleValue)

    when (page) {
        Page.SingleValue -> TODO()
        Page.Situation -> TODO()
        Page.Export -> TODO()
    }
}

enum class Page {
    SingleValue,
    Situation,
    Export
}