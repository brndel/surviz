package ui.fields

import LocalGlobalCallbacks
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import data.io.exporter.Exporter
import ui.Label
import ui.Labels
import ui.window.help.UserGuide

@Composable
fun FileSchemeField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholders: List<String>,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    val globalCallbacks = LocalGlobalCallbacks.current!!
    val filename =
        Exporter.getNameFromScheme(value, *placeholders.map { it to it.uppercase() }.toTypedArray())

    var showPlaceholderPopup by remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

        OutlinedTextField(value, onValueChange, modifier, label = label, trailingIcon = {
            Box {
                IconButton({
                    showPlaceholderPopup = true
                }) {
                    Icon(Icons.Default.Info, null)
                }
                if (showPlaceholderPopup) {
                    Popup(
                        alignment = Alignment.CenterEnd,
                        onDismissRequest = {
                            showPlaceholderPopup = false
                        }) {
                        Surface(
                            color = MaterialTheme.colors.background,
                            shape = RoundedCornerShape(4.dp),
                            elevation = 8.dp
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Label(
                                    Labels.PLACEHOLDERS,
                                    style = TextStyle(fontWeight = FontWeight.Bold)
                                )
                                for (placeholder in placeholders) {
                                    Text("- " + "$" + placeholder + "$")
                                }
                                Button({ globalCallbacks.openHelp(UserGuide.Export.scheme) }) {
                                    Icon(Icons.Default.Help, null)
                                    Label(Labels.OPEN_HElP_INFO_BOX, Modifier.padding(8.dp))
                                }
                            }
                        }
                    }
                }
            }

        })
        Text(filename)
    }
}