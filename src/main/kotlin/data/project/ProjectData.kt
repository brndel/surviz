package data.project

import data.project.data.Block
import data.project.data.DataScheme
import data.project.data.Situation

/**
 * This class represents a project data.
 * It contains a data scheme and a list of blocks.
 *
 *
 * @property dataScheme The data scheme of the project
 * @property blocks The blocks of the project
 */
class ProjectData(
    val dataScheme: DataScheme,
    blocks: List<Block>
) {
    private val blocks: Map<Int, Block> = blocks.associateBy { it.id }

    fun getBlocks(): List<Block> {
        return blocks.entries.sortedBy { it.key }.map { it.value }
    }

    fun getBlock(id: Int): Block? {
        return blocks[id]
    }

    fun getMaxBlockID(): Int{
        var max: Int = 0
        for(block in blocks){
            if(block.value.id > max){
                max = block.value.id
            }
        }
        return max
    }
    fun getMaxSituationID(id: Int): Int{
        var max: Int = 0
        for(sit in blocks[id]?.getSituations()!!){
            if(sit.id > max){
                max = sit.id
            }
        }
        return max
    }


    /**
     * This function returns a situation from a block.
     * @param block the block id
     * @param situation the situation id
     */
    fun getSituation(block: Int, situation: Int): Situation? {
        return blocks[block]?.getSituation(situation)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectData

        if (dataScheme != other.dataScheme) return false
        return blocks == other.blocks
    }

    override fun hashCode(): Int {
        var result = dataScheme.hashCode()
        result = 31 * result + blocks.hashCode()
        return result
    }
}