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
 * @property situation The simulations contained by the block.
 */
class Block(
    var id: Int,
    var situation: List<Situation>
)
