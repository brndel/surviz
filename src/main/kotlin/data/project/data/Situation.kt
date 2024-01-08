package data.project.data

/**
 * This class represents a situation.
 * A situation is a set of options and has a simulation id.
 * A situation contains a list of situation options.
 *
 */
class Situation  constructor(
    var simulationId : Int,
    var options : List<SituationOption>){
}