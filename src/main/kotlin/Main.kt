import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.Language
import ui.LocalLanguage
import ui.MainScreen

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        MaterialTheme {
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