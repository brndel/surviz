package ui

import LocalGlobalCallbacks
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import data.generator.ImageGenerator
import data.project.Project
import ui.fields.IntField
import ui.fields.OptionsField
import kotlin.math.absoluteValue

/**
 * This view shows a preview of the current [Project]
 *
 * @param project the [Project] to show a preview for
 * @state selectedBlock Int the index of the block of the situation to show the preview for
 * @state selectedSituation Int the index of the situation to show the preview for
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Preview(project: Project) {
    Box(Modifier.fillMaxSize()) {
        val imageGenerator = remember { ImageGenerator(project.configuration, project.iconStorage) }
        var blockId by remember { mutableIntStateOf(1) }
        var situationId by remember { mutableIntStateOf(1) }

        val situation by derivedStateOf {
            project.getSituation(blockId, situationId)
        }

        val callbacks = LocalGlobalCallbacks.current!!

        val state = rememberLazyListState()
        LazyColumn(Modifier.fillMaxSize().padding(bottom = 10.dp, end = 10.dp), state = state) {
            stickyHeader {
                Surface(
                    Modifier.fillMaxWidth(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Label(Labels.PREVIEW, style = MaterialTheme.typography.h4)
                            Icon(Icons.Default.Preview, null)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(onClick = {
                                if (project.isValidBlockID(blockId) &&
                                    project.isValidSituationID(blockId, situationId) &&
                                    !project.hasReachedMin(blockId, situationId)
                                ) {
                                    if (situationId == 1) {
                                        blockId--
                                        situationId = project.getMaxSituationID(blockId)
                                    } else {
                                        situationId--
                                    }
                                } else {
                                    blockId = 1
                                    situationId = 1
                                }
                            }) {
                                Icon(Icons.Default.ArrowLeft, null)
                            }

                            IntField(
                                blockId,
                                { blockId = it },
                                label = { Label(Labels.BLOCK) },
                                modifier = Modifier.width(200.dp)
                            )
                            IntField(
                                situationId,
                                { situationId = it },
                                label = { Label(Labels.SITUATION) },
                                modifier = Modifier.width(200.dp)
                            )

                            Button(onClick = {
                                if (project.isValidBlockID(blockId) &&
                                    project.isValidSituationID(blockId, situationId) &&
                                    !project.hasReachedMax(blockId, situationId)
                                ) {
                                    if (situationId == project.getMaxSituationID(blockId)) {
                                        blockId++
                                        situationId = 1
                                    } else {
                                        situationId++
                                    }
                                } else {
                                    blockId = project.getMaxBlockID()
                                    situationId = project.getMaxSituationID(blockId)
                                }
                            }) {
                                Icon(Icons.Default.ArrowRight, null)
                            }
                        }
                    }
                }
            }
            if (situation != null) {
                items(situation!!.options.values.toList()) { option ->
                    var errorText: String? = null
                    val image = try {
                        imageGenerator.generateOption(option)
                    } catch (e: Throwable) {
                        errorText = e.toString() + " at " + e.stackTrace[0].toString()
                        null
                    }

                    Column(Modifier.padding(vertical = 5.dp)) { // Add vertical padding between items
                        if (image != null) {
                            Box(modifier = Modifier.clip(RoundedCornerShape(10.dp))) {
                                Image(
                                    image.image,
                                    null,
                                    modifier = Modifier.fillMaxSize()
                                )

                                if (callbacks.has999Value()) {
                                    if (option.containsValue(callbacks.get999Value())) {
                                        Value999Warning(modifier = Modifier.align(Alignment.TopStart).padding(10.dp))
                                    }
                                }

                                if (!image.checkWidth()) {
                                    PreviewWarning(
                                        modifier = Modifier.align(Alignment.TopEnd),
                                        image.neededWidth
                                    ) {
                                        if (state.isScrollInProgress) {
                                            it()
                                        }
                                    }

                                }
                            }
                        } else {
                            Row {
                                Label(Labels.IMAGE_CREATE_ERROR)
                                Text(" '$errorText'")
                            }
                        }
                    }
                }

            } else {
                item {
                    Label(Labels.SITUATION_NOT_FOUND)
                }

            }
        }
    }
}

@Composable
private fun PreviewWarning(modifier: Modifier = Modifier, neededWidth: Int, onScrollStateChange: (() -> Unit) -> Unit) {
    var showPopUp by remember { mutableStateOf(false) }
    onScrollStateChange { showPopUp = false }
    Box(modifier) {
        IconButton({ showPopUp = true }) {
            Icon(Icons.Default.Warning, null, tint = MaterialTheme.colors.error)
        }
        if (showPopUp) {
            Popup(
                alignment = Alignment.CenterEnd,
                onDismissRequest = { showPopUp = false }
            ) {
                Surface(
                    color = MaterialTheme.colors.surface,
                    shape = RoundedCornerShape(4.dp),
                    elevation = 8.dp
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Label(
                            Labels.PREVIEW_WARNING_TITLE,
                            style = TextStyle(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.error)
                        )
                        Label(Labels.PREVIEW_WARNING_DESCRIPTION)
                        Text("$neededWidth px", style = TextStyle(fontWeight = FontWeight.Bold))
                        Label(Labels.PREVIEW_WARNING_FIX)
                    }
                }
            }
        }
    }
}

@Composable
private fun Value999Warning(modifier: Modifier = Modifier) {
    Box(modifier) {
        Icon(Icons.Default.FileDownloadOff, null, tint = MaterialTheme.colors.secondary)
    }
}