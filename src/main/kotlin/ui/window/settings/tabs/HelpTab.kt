package ui.window.settings.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.util.NestedSurface

@Composable
fun HelpTab() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                HelpHeading(
                    Labels.USER_GUIDE,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 10.dp)
                )
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HelpHeading(
                            Labels.USER_GUIDE_START_SCREEN,
                            style = MaterialTheme.typography.h5
                        )
                        Image(painterResource("userguide/Start_Screen.png"), null)
                        Label(Labels.USER_GUIDE_START_SCREEN_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT)
                        Label(Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT)
                        Label(Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT)
                        Label(Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_SETTINGS)
                        Label(Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION)
                    }
                }
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HelpHeading(
                            Labels.USER_GUIDE_PROJECT_SCREEN,
                            style = MaterialTheme.typography.h5
                        )

                        Label(Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW)
                        //TODO(ADD GIF)
                        Label(Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION)
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR,
                                    style = MaterialTheme.typography.h6
                                )
                                //TODO(ADD GIF)
                                Label(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION)
                                HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE)
                                Label(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION)
                                HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE)
                                Text(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION)
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE,
                                    style = MaterialTheme.typography.h6,
                                )
                                //TODO(ADD GIF)
                                Label(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION)
                                HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION)
                                HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME)
                                //TODO(ADD GIF)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION)
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION,
                                    style = MaterialTheme.typography.h6,
                                )
                                Label(Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION)
                                HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT)
                                Label(Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION)
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT,
                                    style = MaterialTheme.typography.h6,
                                )
                                Label(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION)
                                HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HelpHeading(
    label: String, style: TextStyle? = null, modifier: Modifier = Modifier
) {
    val textStyle = style
        ?: TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize) // Use MaterialTheme.typography.subtitle1.fontSize if style is null

    Text(
        LocalLanguage.current.getString(label),
        style = textStyle.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary),
        modifier = modifier
    )
}


@Composable
private fun HighlightedText(label: String) {
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
