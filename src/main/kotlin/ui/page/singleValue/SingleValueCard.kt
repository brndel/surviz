package ui.page.singleValue

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ViewWeek
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import data.project.config.ProjectConfiguration
import data.project.config.SingleValueConfig
import data.project.config.columns.ListColumns
import data.project.config.columns.SchemeColumns
import data.project.config.columns.SingleValueColumn
import data.project.config.columns.TimelineColumns
import data.project.config.columns.ZeroColumn
import data.project.data.DataScheme
import org.burnoutcrew.reorderable.ReorderableState
import ui.Label
import ui.Labels
import ui.util.InfoIconBox
import ui.util.NestedSurface
import ui.util.ReorderHandle
import ui.window.help.UserGuide
import java.util.UUID
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
    projConfig: ProjectConfiguration,
    id: UUID,
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

            SingleValueCardContent(config, projConfig, id, dataScheme)

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, null)
            }
        }
    }
}

@Composable
private fun RowScope.SingleValueCardContent(config: SingleValueConfig,
                                            projConfig: ProjectConfiguration,
                                            id: UUID,
                                            dataScheme: DataScheme) {
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
            InfoIconBox(Labels.SINGLE_VALUE_SCHEME_INFO_TITLE, Labels.SINGLE_VALUE_SCHEME_INFO_DESCRIPTION, UserGuide.SingleValue.scheme)
        })

        SingleValueIconCard(config.icon)

        ColumnButton(projConfig, id)
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
                    for (option in dataScheme.options) {
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

@Composable
fun ColumnButton(projConfig: ProjectConfiguration, id: UUID){
    var dropdownExpanded by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {

            Button({ dropdownExpanded = true }) {
                Icon(Icons.Default.ViewWeek, contentDescription = null)
                Label(Labels.SINGLE_VALUE_SET_ALL_COLUMNS)
            }
                InfoIconBox(Labels.SINGLE_VALUE_ALL_COLUMNS_INFO_TITLE, Labels.SINGLE_VALUE_ALL_COLUMNS_INFO_DESCRIPTION, UserGuide.SingleValue.setAllColumns)
            }

            DropdownMenu(dropdownExpanded, { dropdownExpanded = false }) {

                @Composable
                fun ColumnsMenuItem(
                    nameLabel: String,
                    descriptionLabel: String,
                    theColumn: () -> SingleValueColumn
                ) {
                    DropdownMenuItem(onClick = {
                        projConfig.setAllSituationColumns(theColumn(),id)
                        dropdownExpanded = false
                    }) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.padding(
                                top = 10.dp,
                                bottom = 10.dp,
                                start = 4.dp,
                                end = 4.dp
                            )
                        ) {
                            Label(nameLabel)
                            Label(
                                descriptionLabel,
                                style = MaterialTheme.typography.subtitle1,
                                modifier = Modifier.alpha(0.75F)
                            )
                        }
                    }
                }

                @Composable
                fun ColumnsMenuItem(
                    column: SingleValueColumn
                ) {
                    ColumnsMenuItem(
                        column.nameLabel,
                        column.descriptionLabel
                    ) { column }
                }


                ColumnsMenuItem(SchemeColumns)
                ColumnsMenuItem(ZeroColumn)
                ColumnsMenuItem(TimelineColumns)
                ColumnsMenuItem(ListColumns.nameLabel, ListColumns.descLabel) {
                    ListColumns()
                }
            }
        }
    }
}