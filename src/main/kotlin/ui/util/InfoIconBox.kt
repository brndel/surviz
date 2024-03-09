package ui.util

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
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

@Composable
fun InfoIconBox(title : String, desc : String) {
    var showTimelineInfoPopup by remember { mutableStateOf(false) }
    Box {
        IconButton({ showTimelineInfoPopup = true }) {
            Icon(Icons.Default.Info, null)
        }
        if (showTimelineInfoPopup) {
            Popup(
                alignment = Alignment.CenterEnd,
                onDismissRequest = {showTimelineInfoPopup = false}
            ) {
                Surface(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(4.dp),
                    elevation = 8.dp
                ) {
                    Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Label(
                            title,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                        Label(desc)
                    }
                }
            }
        }
    }
}