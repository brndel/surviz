package ui.page.singleValue

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import data.project.config.SingleValueConfig
import data.project.data.DataScheme
import org.burnoutcrew.reorderable.ReorderableState
import ui.Label
import ui.Labels
import ui.util.InfoIconBox
import ui.util.NestedSurface
import ui.util.ReorderHandle
import java.util.regex.PatternSyntaxException

/**
 * In this card the user can edit a [SingleValueConfig]
 *
 * @param config the configuration that can be edited on this card
 * @param onDelete get called when the delete button on this card gets pressed
 * @ui TextField for the unit and the colorScheme of the given [SingleValueConfig]
 * @ui SingleValueIconCard for the icons of the given [SingleValueConfig]
 */
@Composable
fun SingleValueCard(
    config: SingleValueConfig,
    dataScheme: DataScheme,
    onDelete: () -> Unit,
    reorderState: ReorderableState<*>,
    dragging: Boolean = false
) {

    val elevation by animateDpAsState(
        if (dragging) {
            16.dp
        } else {
            0.dp
        }
    )

    NestedSurface(
        elevation = elevation
    ) {
        Row(
            Modifier.padding(10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            ReorderHandle(reorderState)

            SingleValueCardContent(config, dataScheme)

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, null)
            }
        }
    }
}

@Composable
private fun RowScope.SingleValueCardContent(config: SingleValueConfig, dataScheme: DataScheme) {
    var unit by config.unit
    var columnScheme by config.columnScheme

    var showSchemeTooltip by remember { mutableStateOf(false) }

    Column(Modifier.weight(1F), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OutlinedTextField(unit, { unit = it }, label = {
            Label(Labels.FIELD_UNIT)
        })

        OutlinedTextField(columnScheme, { columnScheme = it }, singleLine = true, label = {
            Label(Labels.FIELD_COLUMN_SCHEME)
        }, modifier = Modifier.onFocusChanged {
            showSchemeTooltip = it.isFocused
        }, trailingIcon = {
            Box {
                if (showSchemeTooltip) {
                    SchemeMatchPopup(columnScheme, dataScheme)
                }
            }
            InfoIconBox(Labels.FIELD_COLUMN_SCHEME,Labels.FIELD_COLUMN_SCHEME)
        })

        SingleValueIconCard(config.icon)
    }
}

@Composable
fun SchemeMatchPopup(scheme: String, dataScheme: DataScheme) {
    val focusManager = LocalFocusManager.current

    val regex = try {
        Regex(scheme)
    } catch (e: PatternSyntaxException) {
        null
    }

    Popup(onDismissRequest = {
        focusManager.clearFocus()
    }, alignment = Alignment.TopStart) {
        Surface(
            elevation = 8.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            if (regex != null) {

                val textColor = LocalContentColor.current
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    for (option in dataScheme.options.values) {
                        for (fieldName in option.fieldsList) {
                            val match = regex.matchAt(fieldName, 0)

                            if (match != null) {
                                val string = buildAnnotatedString {
                                    val veryLightSpanStyle = SpanStyle(color = textColor.copy(alpha = 0.25F))
                                    val lightSpanStyle = SpanStyle(color = textColor.copy(alpha = 0.5F))
                                    val spanStyle = SpanStyle(color = textColor.copy(alpha = 1F))
                                    withStyle(veryLightSpanStyle) {
                                        append(option.name)
                                        append(".")
                                    }

                                    val beforeMatch = fieldName.substring(IntRange(0, match.range.first - 1))
                                    val inMatch = fieldName.substring(match.range)
                                    val afterMatch = fieldName.substring(match.range.last + 1)

                                    withStyle(lightSpanStyle) {
                                        append(beforeMatch)
                                    }
                                    withStyle(spanStyle) {
                                        append(inMatch)
                                    }
                                    withStyle(lightSpanStyle) {
                                        append(afterMatch)
                                    }
                                }
                                Text(string)

                            }

                        }
                    }
                }
            } else {
                Label(Labels.ERROR)
            }
        }
    }

}