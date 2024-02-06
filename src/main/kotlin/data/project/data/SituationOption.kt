package data.project.data

/**
 * This class represents a situation option.
 * A situation option is a map of values and a name.
 *
 * @property name The name of the situation option
 * @property values The values of the situation option
 */
data class SituationOption(
    var name: String,
    var values: Map<String, Double>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SituationOption) return false

        if (this.name != other.name) return false

        if (this.values.size != other.values.size) return false

        for (key in this.values.keys) {
            if (!other.values.containsKey(key)) return false
            if (this.values[key] != other.values[key]) return false
        }
        return true
    }
}