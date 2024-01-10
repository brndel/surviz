package data.project.data

/**
 * This class represents a situation option.
 * A situation option is a map of values and a name.
 *
 * @property name The name of the situation option
 * @property values The values of the situation option
 */
class SituationOption (
    var name: String,
    var values: Map<String, Double>
) {
}