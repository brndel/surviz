package ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import data.io.exporter.ImageGenerator
import data.project.Project
import ui.fields.IntField

/**
 * This view shows a preview of the current [Project]
 *
 * @param project the [Project] to show a preview for
 * @state selectedBlock Int the index of the block of the situation to show the preview for
 * @state selectedSituation Int the index of the situation to show the preview for
 */
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
            item {
                Row {
                    IntField(blockId, onValueChange = { blockId = it }) {
                        Text("Block")
                    }
                    IntField(situationId, onValueChange = { situationId = it }) {
                        Text("Situation")
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
                            Image(it, null)
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