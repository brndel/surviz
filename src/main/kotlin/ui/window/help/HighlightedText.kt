package ui.window.help

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import ui.Label
import ui.LocalLanguage


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

    Label(
        label,
        style = textStyle.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary),
        modifier = modifier
    )
}