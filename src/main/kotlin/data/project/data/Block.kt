package data.project.data

/**
 * This class represents a block.
 * To split data into smaller parts,and make it more handable
 * ,the raw data in every nGene file is split into blocks.
 * Every block contains an amount of [Situation].
 * From where every situation contains an amount of [SituationOption].
 * A bock has its unique id.
 *
 * @property id The id of the block.
 * @property situations The situations of the block.
 */
class Block(
    var id: Int,
) {
    var situations = ArrayList<Situation>()

    constructor(id: Int, situations: List<Situation>) : this(id) {
        this.situations = ArrayList(situations)
    }

    /**
     * Add situation
     *
     * creates a new situation with the given options and matching id based on length of [situations]
     *
     * @param options list of all options of the situation
     */
    fun addSituation(options: List<SituationOption>) {
        situations.add(Situation(situations.size, options))
    }
}
