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
     * This method returns a Situation
     * @param block the block id
     * @param situation the situation id
     */
    fun getSituations(block: Int, situation: Int): Situation? {
        return blocks[block].situation[situation]
    }
}