package data.project.data

/**
 * This class represents a situation.
 * A situation is a set of [SituationOption]s which later can be modified by the user.
 * So situation contains a list of situation [options] and an [situationId].
 *
 * @param situationId The simulation id of the situation.
 * @param options The options of the situation.
 *
 * @constructor Creates a situation.
 */
class Situation constructor(
    var situationId: Int,
    var options: List<SituationOption>
) {
}