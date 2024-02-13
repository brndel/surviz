package ui.fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import data.io.exporter.Exporter
import data.resources.fields.FileSchemeFieldData
import ui.Label
import ui.Labels

@Composable
fun FileSchemeField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholders: List<String>,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    val filename =
        Exporter.getNameFromScheme(value, *placeholders.map { it to 0.toString() }.toTypedArray())

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
                                    Text("- $placeholder")
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