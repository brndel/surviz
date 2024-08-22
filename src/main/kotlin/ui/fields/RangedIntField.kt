package ui.fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
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
import ui.Label
import ui.Labels
import ui.window.help.UserGuide

@Composable
fun RangedIntField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
    ) {
    var showPopup by remember { mutableStateOf(false) }

    ParserField(value, onValueChange, parse = {
        var convertError = false
        if (it.matches(Regex("^\\d+(-\\d+)?(,\\d+(-\\d+)?)*$"))) {
            for(segment in it.split(",")) {
                if(segment.toIntOrNull() == null) {
                    convertError = true
                }
            }
        }
        if (convertError) {
            null
        } else {
            it
        }

    }, toString = { it }, modifier, label) {
        IconButton({
            showPopup = true
        }) {
            Icon(Icons.Default.Info, null)
        }
        if (showPopup) {
            Popup(
                alignment = Alignment.CenterEnd,
                onDismissRequest = {
                    showPopup = false
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
                            Labels.RANGED_FIELD_INFO_TITLE,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                        Label(Labels.RANGED_FIELD_INFO_DESCRIPTION)
                    }
                }
            }
        }
    }
}