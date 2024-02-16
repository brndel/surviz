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
    val id: Int,
    situations: List<Situation> = listOf()
) {
    private val situations: MutableList<Situation> = situations.toMutableList()

    fun getSituations(): List<Situation> {
        return this.situations.toList()
    }

    fun getSituation(id: Int): Situation? {
        return situations.getOrNull(id - 1)
    }

    /**
     * Add situation
     *
     * creates a new situation with the given options and matching id based on length of [situations]
     *
     * @param options list of all options of the situation
     */
    fun addSituation(options: List<SituationOption>) {
        situations.add(Situation(situations.size + 1, options))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Block

        if (id != other.id) return false
        if (situations != other.situations) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + situations.hashCode()
        return result
    }
}
