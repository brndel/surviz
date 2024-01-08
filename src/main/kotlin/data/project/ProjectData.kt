package data.project

import data.project.data.Block
import data.project.data.DataScheme
import data.project.data.Situation

/**
 * This class represents a project data.
 * It contains a data scheme and a list of blocks.
 */
class ProjectData constructor(
    var dataScheme: DataScheme,
    var blocks: List<Block>
)
{
    /**
     * This method returns a Situation
     * @param block the block id
     * @param situation the situation id
     */
    fun getSituations(block: Int, situation: Situation): Situation? {
        return null

    }
}