package ui.fields

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
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
import data.resources.exceptions.InvalidSegmentException
import ui.Label
import ui.Labels

@Composable
fun MultipleDoubleField(
    value: Collection<Double>,
    onValueChange: (Collection<Double>) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null,
) {
    var showPopup by remember { mutableStateOf(false) }

    ParserField(value, onValueChange, parse = {
        if (it.matches(Regex("^\\s*-?\\d+([.,]\\d+)?\\s*(;\\s*-?\\d+([.,]\\d+)?\\s*)*$"))) {
            val doubles: MutableList<Double> = mutableListOf()
            it.split(";").forEach { segment ->
                val double = segment.trim().replace(",", ".").toDoubleOrNull() ?: throw InvalidSegmentException(segment)
                doubles.add(double)
            }
            doubles
        } else {
            null
        }
    }, toString = {
        it.joinToString("; ")
    }, modifier, label) {
        IconButton({ showPopup = true }) {
            Icon(Icons.Default.Info, null)
        }
        if (showPopup) {
            Popup(
                alignment = Alignment.CenterEnd,
                onDismissRequest = {
                    showPopup = false
                },
            ) {
                Surface(
                    color = MaterialTheme.colors.background,
                    shape = RoundedCornerShape(4.dp),
                    elevation = 8.dp,
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Label(
                            Labels.MULTIPLE_DOUBLE_FIELD_INFO_TITLE,
                            style = TextStyle(fontWeight = FontWeight.Bold),
                        )
                        Label(Labels.MULTIPLE_DOUBLE_FIELD_INFO_DESCRIPTION)
                    }
                }
            }
        }
    }
}
