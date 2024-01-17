package ui

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun Label(label: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current) {
    Text(LocalLanguage.current.getString(label) ?: "<MISSING LABEL '$label'>", modifier = modifier, style = style)
}