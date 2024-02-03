package data.project


import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import data.project.config.ProjectConfiguration
import data.project.data.DataScheme
import data.project.data.IconStorage
import java.io.File
import java.io.FileWriter

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
        val gson = GsonBuilder()
            .setExclusionStrategies(object : com.google.gson.ExclusionStrategy {
                override fun shouldSkipField(f: com.google.gson.FieldAttributes): Boolean {
                    return false
                }

                override fun shouldSkipClass(clazz: Class<*>): Boolean {
                    return clazz == SnapshotStateMap::class.java
                }
            })
            .setPrettyPrinting()
            .create()
        val json = gson.toJson(this)

        val filename = "test.svd"

        try {
            // Create a File object for the specified file name
            val file = File(path + filename)

            // Create a FileWriter to write to the file
            val writer = FileWriter(file)

            // Write content to the file
            writer.write(json)

            // Close the FileWriter
            writer.close()

            println("File '$ fileName' has been created with given content.")

            // save path to AppData file
            val savePathFile = File(savePath)
            // mk directories if non existent
            if (!savePathFile.exists()) {
                savePathFile.mkdirs()
            }

            // make file if non existent
            val propertiesFile = File(savePath + SAVE_FILE_NAME)
            if (!propertiesFile.exists()) {
                propertiesFile.createNewFile()
            }

            propertiesFile.writeText(file.absolutePath)

        } catch (e: Exception) {
            println("An error occurred: ${e.message}")
        }


    }

    companion object {

        private val savePath = "C:\\Users\\${System.getProperty("user.name")}\\AppData\\Local\\SurViz\\"
        private const val SAVE_FILE_NAME = "save.txt"

        /**
         * Gets the file path from the last saved project. This can allows the user to immediately
         * continue working on their last project.
         * @return The path of the last saved project.
         */
        fun getLastProjectFilePath(): String {
            val file = File(savePath + SAVE_FILE_NAME)

            if (file.exists()) {
                return file.readText()
            }
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
            val file = projectFile.readText()
            return Gson().fromJson(file, Project::class.java)
        }
    }

}



