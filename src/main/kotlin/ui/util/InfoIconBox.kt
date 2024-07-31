package ui.util

import LocalGlobalCallbacks
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import ui.Label
import ui.Labels
import ui.window.help.HelpEntry

/**
 * Info icon box
 * Clickable info icon that shows a popup with title, description and a button to open help entry
 *
 * @param title title of the info box
 * @param desc description of the info box
 * @param helpEntry link to help entry
 */
@Composable
fun InfoIconBox(title: String, desc: String?, helpEntry: HelpEntry?) {
    val globalCallbacks = LocalGlobalCallbacks.current!!
    var showTimelineInfoPopup by remember { mutableStateOf(false) }
    Box {
        IconButton({ showTimelineInfoPopup = true }) {
            Icon(Icons.Default.Info, null)
        }
        if (showTimelineInfoPopup) {
            Popup(
                alignment = Alignment.CenterEnd,
                onDismissRequest = { showTimelineInfoPopup = false }
            ) {
                Surface(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(4.dp),
                    elevation = 8.dp
                ) {
                    Column(
                        Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Label(
                            title,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                        // not showing description and button to help entry if values are null
                        // some info icons don't need description
                        if (desc != null) {
                            Label(desc)
                        }
                        if (helpEntry != null) {
                            Button(
                                { globalCallbacks.openHelp(helpEntry) }
                            ) {
                                Icon(Icons.Default.Help, null)
                                Label(Labels.OPEN_HElP_INFO_BOX, Modifier.padding(8.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}