import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.Language
import ui.LocalLanguage
import ui.MainScreen

fun main() = application {
    val windowState = rememberWindowState(width = 1920.dp, height = 1080.dp, placement = WindowPlacement.Maximized)
    Window(onCloseRequest = ::exitApplication, state = windowState) {
        MaterialTheme(
            colors = lightColors(
                background = Color(240, 240, 240),
                surface = Color(230, 230, 230)
            )
        ) {
            val language = remember {
                val langCode = System.getProperty("user.language")

                Language.fromCode(langCode)
            }

            CompositionLocalProvider(
                LocalLanguage provides language
            ) {
                MainScreen()
            }
        }
    }
}