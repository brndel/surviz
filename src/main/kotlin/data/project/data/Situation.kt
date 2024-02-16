package data.project.data

/**
 * This class represents a situation.
 * A situation is a set of [SituationOption]s which later can be modified by the user.
 * So situation contains a list of situation [options] and an [id].
 *
 * @property id The simulation id of the situation
 * @property options The options of the situation
 */
class Situation(
    val id: Int,
    options: List<SituationOption>
) {
    val options: Map<String, SituationOption> = options.associateBy { it.name }
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Situation

        if (id != other.id) return false
        if (options != other.options) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + options.hashCode()
        return result
    }


}
