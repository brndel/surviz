package data.project


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.*
import data.project.config.ProjectConfiguration
import data.project.data.DataScheme
import data.project.data.IconStorage
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createParentDirectories

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
    var iconStorage: IconStorage,
    var projectName: MutableState<String?> = mutableStateOf(null)
) {
    /**
     * This method loads the project data.
     * @param data The project data to load.
     * @param force If true, the project data will be loaded even if the current [DataScheme]
     * differs from the new one.
     * @return True if the project data was loaded successfully, false otherwise.
     */
    fun loadProjectData(data: ProjectData, force: Boolean): Boolean {
        if (force || dataScheme.compareTo(data.dataScheme)) {
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
        val file = File(path)
        projectName.value = file.nameWithoutExtension

        val json = gsonSerializer.toJson(this)

        file.writeText(json)

        setLastProjectFilePath(path)
    }

    companion object {

        private val lastProjectFile: String
            get() {
                val os = System.getProperty("os.name").lowercase()
                return if (os.startsWith("win")) {
                    "C:\\Users\\${System.getProperty("user.name")}\\AppData\\Local\\SurViz\\last_project.txt"
                } else {
                    "/home/${System.getProperty("user.name")}/.local/share/SurViz/last_project.txt"
                }
            }

        /**
         * Gets the file path from the last saved project. This can allows the user to immediately
         * continue working on their last project.
         * @return The path of the last saved project.
         */
        fun getLastProjectFilePath(): String? {
            val file = File(lastProjectFile)

            if (file.exists()) {
                return file.readText()
            }
            return null
        }

        private fun setLastProjectFilePath(filePath: String) {
            val absolutePath = File(filePath).absolutePath

            val lastProjectFilePath = Path(lastProjectFile)

            lastProjectFilePath.createParentDirectories()

            lastProjectFilePath.toFile().writeText(absolutePath)
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

            return gsonDeserializer.fromJson(file, Project::class.java)
        }

        private val gsonSerializer: Gson by lazy {
            GsonBuilder()
                .registerTypeAdapter(IconStorage::class.java, IconStorage.SERIALIZER)
                .setPrettyPrinting()
                .create()

        }
        private val gsonDeserializer: Gson by lazy {
            GsonBuilder()
                .registerTypeAdapter(IconStorage::class.java, IconStorage.DESERIALIZER)
                .create()
        }


    }
}



