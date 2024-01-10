package data.project.data

/**
 * This class represents a situation option.
 * A situation option is a map of values and a name.
 *
 * @param name The name of the situation option.
 * @param values The values of the situation option.
 *
 */
class SituationOption (
    /**
     * The name of the situation option.
     */
    var name: String,
    /**
     * The values of the situation option.
     */
    var values: Map<String, Double>
) {
}