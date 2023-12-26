package data.project

import data.project.config.ProjectConfiguration
import data.project.data.DataScheme
import data.project.data.IconStorage

/**
 * This class represents a project.

 */
class Project constructor(
    var data : ProjectData,
    var dataScheme: DataScheme,
    var configuration: ProjectConfiguration,
    var iconStorage : IconStorage
){
    /**
     * This method loads the project data.
     */
    fun loadProjectData(data : ProjectData) : Boolean {
        return false
    }
}