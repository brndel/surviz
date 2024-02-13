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
}
