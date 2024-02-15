package ui.window.settings.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.Label
import ui.Labels
import ui.util.NestedSurface
import ui.window.settings.HighlightedHeading
import ui.window.settings.HighlightedText

@Composable
fun HelpTab() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                HighlightedHeading(
                    Labels.USER_GUIDE,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 10.dp)
                )
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HighlightedHeading(
                            Labels.USER_GUIDE_START_SCREEN,
                            style = MaterialTheme.typography.h5
                        )
                        Image(painterResource("userguide/Start_Screen.png"), null)
                        HighlightedText(Labels.USER_GUIDE_START_SCREEN_DESCRIPTION)
                        HighlightedHeading(Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT)
                        HighlightedText(Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION)
                        HighlightedHeading(Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT)
                        HighlightedText(Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION)
                        HighlightedHeading(Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT)
                        HighlightedText(Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION)
                        HighlightedHeading(Labels.USER_GUIDE_START_SCREEN_SETTINGS)
                        HighlightedText(Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION)
                    }
                }
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HighlightedHeading(
                            Labels.USER_GUIDE_PROJECT_SCREEN,
                            style = MaterialTheme.typography.h5
                        )

                        HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION)
                        HighlightedHeading(Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW)
                        //TODO(ADD GIF)
                        HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION)
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HighlightedHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR,
                                    style = MaterialTheme.typography.h6
                                )
                                //TODO(ADD GIF)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION)
                                HighlightedHeading(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION)
                                HighlightedHeading(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION)
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HighlightedHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE,
                                    style = MaterialTheme.typography.h6,
                                )
                                //TODO(ADD GIF)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION)
                                HighlightedHeading(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION)
                                HighlightedHeading(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME)
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
                                HighlightedHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION,
                                    style = MaterialTheme.typography.h6,
                                )
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION)
                                HighlightedHeading(Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT)
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION)
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HighlightedHeading(
                                    Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT,
                                    style = MaterialTheme.typography.h6,
                                )
                                HighlightedText(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION)
                                HighlightedHeading(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME)
                                Label(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION)
                            }
                        }
                    }
                }
            }
        }
    }
}