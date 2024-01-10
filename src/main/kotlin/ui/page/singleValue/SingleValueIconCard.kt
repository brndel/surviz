package ui.page.singleValue

import androidx.compose.runtime.Composable
import data.project.config.SingleValueIcon
import ui.fields.IconField

/**
 * In this card the user can edit a [SingleValueIcon].
 * This card is used by [SingleValueCard].
 *
 * @param icon the icon configuration that can be edited on this card
 * @ui IconField this card shows a [IconField] for every icon in the given [SingleValueIcon].
 * The order of the icons can be changed by drag and drop.
 * @ui IntField for every lowerBound of every level in the given [SingleValueIcon]
 */
@Composable
fun SingleValueIconCard(icon: SingleValueIcon) {
}