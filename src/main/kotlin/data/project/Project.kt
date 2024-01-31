package data.project


import com.google.gson.Gson
import data.project.config.ProjectConfiguration
import data.project.data.DataScheme
import data.project.data.IconStorage
import java.io.File
import java.io.FileWriter
import java.util.*

/**
 * This class represents a project,which is the root of every SurViz project.
 * All relevant information like [data] ,[dataScheme] ,project [configuration]
 * and the [iconStorage] of the project is stored here.
 * From here other projects can be accessed by loading or creating them.
 *
 *
 *
 * @property data the data of the project.
 * @property dataScheme the data scheme of the project.
 * @property configuration the configuration of the project.
 * @property iconStorage the icon storage of the project.
 */
class Project(
    var data: ProjectData,
    var dataScheme: DataScheme,
    var configuration: ProjectConfiguration,
    var iconStorage: IconStorage
) {
    /**
     * This method loads the project data.
     * @param data The project data to load.
     * @param force If true, the project data will be loaded even if the current [DataScheme]
     * differs from the new one.
     * @return True if the project data was loaded successfully, false otherwise.
     */
    fun loadProjectData(data: ProjectData, force: Boolean): Boolean {
        if (!dataScheme.compareTo(data.dataScheme)) {
            if (force) {
                this.data = data
                return true
            }
        }
        if (dataScheme.compareTo(data.dataScheme)) {
            this.data = data
            return true
        }
        return false
    }


    /**
     * This method saves the project data.
     * @param path The path to save the project data.
     */
    fun saveProjectData(path: String) {
        val gsonData = Gson()
        val jsonData = gsonData.toJson(data)

        val gsonScheme = Gson()
        val jsonScheme = gsonScheme.toJson(dataScheme)

        val gsonConfig = Gson()
        val jsonConfig = gsonConfig.toJson(configuration)





        val filename = "test.svd"

        try {
            // Create a File object for the specified file name
            val file = File(path + filename)

            // Create a FileWriter to write to the file
            val writer = FileWriter(file)

            // Write content to the file
            writer.write(jsonData + "\n")
            writer.write(jsonScheme + "\n")
            writer.write(jsonConfig + "\n")


            // Close the FileWriter
            writer.close()

            println("File '$ fileName' has been created with given content.")
        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
        }



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
        fun newProjectWithData(data: ProjectData): Project {
            return Project(data, data.dataScheme, ProjectConfiguration(), IconStorage())

        }

        /**
         * This method loads an existing project from a file.
         * @param projectFile The project file to load.
         */
        fun loadProjectFromFile(projectFile: File): Project {
            TODO()
        }
    }

}



