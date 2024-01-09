package data.project.data

/**
 * This class represents a situation option.
 * A situation option is a map of values and a name.
 *
 * @param name The name of the situation option.
 * @param values The values of the situation option.
 *
 * @constructor Creates a situation option.
 */
class SituationOption constructor(
    var name: String,
    var values: Map<String, Double>
) {
}