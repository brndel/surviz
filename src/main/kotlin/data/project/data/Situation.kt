package data.project.data

/**
 * This class represents a situation.
 * A situation is a set of [SituationOption]s which later can be modified by the user.
 * So situation contains a list of situation [options] and an [situationId].
 *
 * @property situationId The simulation id of the situation
 * @property options The options of the situation
 */
data class Situation (
    var situationId: Int,
    var options: List<SituationOption>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Situation) return false

        if (this.situationId != other.situationId) return false
        if (this.options.size != other.options.size) return false
        for (i in options.indices) {
            if (this.options[i] != other.options[i]) return false
        }
        return true
    }
}