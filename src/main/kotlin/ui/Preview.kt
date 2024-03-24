package ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Preview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import data.generator.ImageGenerator
import data.project.Project
import ui.fields.IntField

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

        LazyColumn(Modifier.fillMaxSize().padding(bottom = 10.dp, end = 10.dp)) {
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
                                if(project.isValidBlockID(blockId)&&
                                    project.isValidSituationID(blockId, situationId)&&
                                    !project.hasReachedMin(blockId, situationId)){
                                    if(situationId == 1){
                                        blockId--
                                        situationId = project.getMaxSituationID(blockId)
                                    } else {
                                        situationId--
                                    }
                                } else {
                                    blockId = 1
                                    situationId = 1
                                }
                            }){
                                Icon(Icons.Default.ArrowLeft,null)
                            }

                            IntField(
                                blockId,
                                { blockId = it },
                                label = { Label(Labels.BLOCK) },
                                modifier = Modifier.width(200.dp))
                            IntField(
                                situationId,
                                { situationId = it },
                                label = { Label(Labels.SITUATION) },
                                modifier = Modifier.width(200.dp))

                            Button(onClick = {
                                if(project.isValidBlockID(blockId) &&
                                    project.isValidSituationID(blockId, situationId) &&
                                    !project.hasReachedMax(blockId, situationId)){
                                    if(situationId == project.getMaxSituationID(blockId)){
                                        blockId++
                                        situationId = 1
                                    } else {
                                        situationId++
                                    }
                                } else {
                                    blockId = project.getMaxBlockID()
                                    situationId = project.getMaxSituationID(blockId)
                                }
                            }){
                                Icon(Icons.Default.ArrowRight,null)
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
                            Image(image.image, null, modifier = Modifier.clip(RoundedCornerShape(10.dp)))
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
