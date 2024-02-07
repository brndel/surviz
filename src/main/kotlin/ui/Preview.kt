package ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.generator.ImageGenerator
import data.project.Project
import ui.fields.OptionsField
import ui.util.NestedSurface

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
        var blockId by remember { mutableIntStateOf(0) }
        var situationId by remember { mutableIntStateOf(0) }

        val situation by derivedStateOf {
            project.data.getSituations(blockId, situationId)
        }

        LazyColumn(Modifier.fillMaxSize()) {
            stickyHeader {
                NestedSurface(Modifier.fillMaxWidth().padding(4.dp), elevation = 4.dp) {
                    Row(
                        Modifier.padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        OptionsField(
                            blockId,
                            { blockId = it },
                            (0..<project.data.blocks.size).toList(),
                            label = { Label(Labels.BLOCK) }) {
                            Text((it + 1).toString())
                        }
                        OptionsField(
                            situationId,
                            { situationId = it },
                            (0..<project.data.blocks[blockId].situations.size).toList(),
                            label = { Label(Labels.SITUATION) }) {
                            Text((it + 1).toString())
                        }
                    }
                }
            }

            if (situation != null) {
                items(situation!!.options) { option ->

                    var errorText: String? = null
                    val image = try {
                        imageGenerator.generateOption(option)
                    } catch (e: Throwable) {


                        errorText = e.toString() + " at " + e.stackTrace[0].toString()

                        null
                    }

                    AnimatedContent(image) {
                        if (it != null) {
                            Image(it.image, null)
                        } else {
                            Text("Error while creating image '$errorText'")
                        }
                    }

                }
            } else {
                item {
                    Text("Situation not found")
                }

            }
        }

    }
}