package data.project

import data.project.config.ProjectConfiguration
import data.project.data.DataScheme
import data.project.data.IconStorage
import java.io.File

/**
 * This class represents a project.
 * It contains all the data of a project like the data scheme, the blocks, the configuration and the icon storage.
 */
class Project constructor(
    var data: ProjectData,
    var dataScheme: DataScheme,
    var configuration: ProjectConfiguration,
    var iconStorage: IconStorage
) {
    /**
     * This method loads the project data.
     * @param data The project data to load.
     * @param force If true, the project data will be loaded even if the project data is not valid.
     * @return True if the project data was loaded successfully, false otherwise.
     * 
     */
    fun loadProjectData(data: ProjectData, force: Boolean): Boolean {
        return false
    }

    /**
     * This method saves the project data.
     * @param path The path to save the project data.
     */
    fun saveProjectData(path: String) {

    }

    companion object {
        /**
         * This method creates a project.
         * @return The path to load the project data.
         */
        fun getLastProjectFilePath(): String {
            return ""
        }

        /**
         * This method creates a project.
         * @param data The project data to load.
         */
        fun newProjectWithData(data: ProjectData) {
        }

        /**
         * This method loads an existing project.
         * @param projectFile The project file to load.
         */
        fun loadProjectFromFile(projectFile: File) {


        }
    }

}



