import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.Language
import ui.LocalLanguage
import ui.MainScreen

fun main() = application {
    val windowState =
        rememberWindowState(width = 1700.dp, height = 900.dp, placement = WindowPlacement.Maximized)
    Window(onCloseRequest = ::exitApplication, state = windowState, title = "SurViz", icon = painterResource("logo.png")) {
        MaterialTheme(
            colors = lightColors(
                background = Color(240, 240, 240),
                surface = Color(230, 230, 230),
                primary = Color(64, 147, 138)
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