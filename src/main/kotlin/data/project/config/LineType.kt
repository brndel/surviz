package data.project.config

import androidx.compose.ui.graphics.PathEffect


/**
 * This enumeration describes all possible types of lines that can be used to display the timeline of a situation.
 */
enum class LineType(val pathEffect: PathEffect?) {
    /**
     * This is a solid, continuous line.
     */
    Solid(null),

    /**
     * This is a dotted line.
     */
    Dotted(PathEffect.dashPathEffect(floatArrayOf(10.0F, 10.0F)))
}