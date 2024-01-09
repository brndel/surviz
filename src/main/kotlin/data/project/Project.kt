package data.project

import data.project.config.ProjectConfiguration
import data.project.data.DataScheme
import data.project.data.IconStorage
import java.io.File

/**
 * This class represents a project,which is the root of every SurViz project.
 * All relevant information like [data] ,[dataScheme] ,project [configuration]
 * and the [iconStorage] of the project is stored here.
 * From here other projects can be accessed by loading or creating them.
 *
 *
 *
 * @param data the data of the project.
 * @param dataScheme the data scheme of the project.
 * @param configuration the configuration of the project.
 * @param iconStorage the icon storage of the project.
 *
 * @constructor Creates a project with the given data, data scheme, configuration and icon storage.
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
         * To access the file path of a project file.
         * @return The path of the project data. Null if not existing.
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



