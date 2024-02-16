package data.generator.resources

import ui.Labels


/**
 * This enumeration describes all possible types of lines that can be used to display the timeline of a situation.
 *
 * @property lineKey key to get the line length out of .properties file
 * @property spaceKey key to get the space length out of .properties file
 * @constructor Create empty Line type
 */
enum class LineType(val label: String, val lineKey: String?, val spaceKey: String?) {
    /**
     * This is a solid, continuous line.
     */
    Solid(Labels.LINE_TYPE_SOLID, null, null),

    /**
     * This is a dotted line.
     */
    Dotted(Labels.LINE_TYPE_DOTTED, "line_dotted_line", "line_dotted_space"),

    Dashed(Labels.LINE_TYPE_DASHED, "line_dashed_line", "line_dashed_space")
}