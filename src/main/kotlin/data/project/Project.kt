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
 */
class Project(
    /**
     *  The data of the project.
     */
    var data: ProjectData,
    /**
     * The data scheme of the project.
     */
    var dataScheme: DataScheme,
    /**
     * The configuration data of the project.
     */
    var configuration: ProjectConfiguration,
    /**
     * The icon storage of the project.
     */
    var iconStorage: IconStorage
) {
    /**
     * This method loads the project data.
     * @param data The project data to load.
     * @param force If true, the project data will be loaded even if the current [DataScheme]
     * differs from the new one.
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
         * Gets the file path from the last saved project. This can allows the user to immediately
         * continue working on their last project.
         * @return The path of the last saved project.
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
         * This method loads an existing project from a file.
         * @param projectFile The project file to load.
         */
        fun loadProjectFromFile(projectFile: File) {


        }
    }

}



