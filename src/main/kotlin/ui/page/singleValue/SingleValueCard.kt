package ui.page.singleValue

import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import data.project.config.SingleValueConfig

/**
 * In this card the user can edit a [SingleValueConfig]
 *
 * @param config the configuration that can be edited on this card
 * @param onDelete get called when the delete button on this card gets pressed
 * @ui TextField for the unit and the colorScheme of the given [SingleValueConfig]
 * @ui SingleValueIconCard for the icons of the given [SingleValueConfig]
 */
@Composable
fun SingleValueCard(config: SingleValueConfig, onDelete: () -> Unit) {
}