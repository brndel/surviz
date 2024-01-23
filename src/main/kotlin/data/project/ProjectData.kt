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
    var dataScheme: DataScheme,
    var blocks: List<Block>
) {
    /**
     * This function returns a situation from a block.
     * @param block the block id
     * @param situation the situation id
     */
    fun getSituations(block: Int, situation: Int): Situation? {
        return blocks.getOrNull(block)?.situations?.getOrNull(situation)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProjectData) return false

        // smart casting
        if(!other.dataScheme.compareTo(this.dataScheme)) return false

        // check blocks
        if (this.blocks.size != other.blocks.size) return false
        for (i in 0..<this.blocks.size) {
            if (this.blocks[i] != other.blocks[i]) return false
        }
        return true
    }
}