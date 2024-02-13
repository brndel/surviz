package data.project.data

/**
 * This class represents a situation option.
 * A situation option is a map of values and a name.
 *
 * @property name The name of the situation option
 * @property values The values of the situation option
 */
data class SituationOption(
    val name: String,
    val values: Map<String, Double>
)