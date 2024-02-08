package data.project


import com.google.gson.*
import data.generator.resources.ImageConfig
import data.project.config.*
import data.project.config.columns.*
import data.project.data.DataScheme
import data.project.data.IconStorage
import util.platformPath
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
    val iconStorage: IconStorage
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

        val json = GSON.toJson(this)

        file.writeText(json)

        setLastProjectFilePath(path)
    }

    companion object {

        const val DEFAULT_FILE_NAME = "project.svz"

        val defaultSaveDirectory by lazy {
            platformPath(windows = {
                "C:\\Users\\$it\\surviz"
            }, linux = {
                "/home/$it/surviz"
            }, mac = {
                "/Users/$it/surviz"
            })
        }

        private val lastProjectFile: String by lazy {
            platformPath(windows = {
                "C:\\Users\\$it\\AppData\\Local\\SurViz\\last_project.txt"
            }, linux = {
                "/home/$it/.local/share/SurViz/last_project.txt"
            }, mac = {
                "/Users/$it/.local/share/SurViz/last_project.txt"
            })
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

            return GSON.fromJson(file, Project::class.java)
        }

        private val GSON: Gson by lazy {
            GsonBuilder()
                .registerTypeAdapter(IconStorage::class.java, IconStorage.serializer)
                .registerTypeAdapter(IconStorage::class.java, IconStorage.deserializer)
                // Project Config
                .registerTypeAdapter(SingleValueConfig::class.java, SingleValueConfig.serializer)
                .registerTypeAdapter(SingleValueConfig::class.java, SingleValueConfig.deserializer)
                .registerTypeAdapter(SingleValueIcon::class.java, SingleValueIcon.serializer)
                .registerTypeAdapter(SingleValueIcon::class.java, SingleValueIcon.deserializer)
                .registerTypeAdapter(
                    SingleValueIconLevel::class.java,
                    SingleValueIconLevel.serializer
                )
                .registerTypeAdapter(
                    SingleValueIconLevel::class.java,
                    SingleValueIconLevel.deserializer
                )
                .registerTypeAdapter(ImageConfig::class.java, ImageConfig.serializer)
                .registerTypeAdapter(ImageConfig::class.java, ImageConfig.deserializer)
                .registerTypeAdapter(SituationConfig::class.java, SituationConfig.serializer)
                .registerTypeAdapter(SituationConfig::class.java, SituationConfig.deserializer)
                .registerTypeAdapter(TimelineEntry::class.java, TimelineEntry.serializer)
                .registerTypeAdapter(TimelineEntry::class.java, TimelineEntry.deserializer)
                // Columns
                .registerTypeAdapter(ListColumns::class.java, SingleValueColumn.serializer)
                .registerTypeAdapter(SchemeColumns::class.java, SingleValueColumn.serializer)
                .registerTypeAdapter(TimelineColumns::class.java, SingleValueColumn.serializer)
                .registerTypeAdapter(ZeroColumn::class.java, SingleValueColumn.serializer)

                .registerTypeAdapter(SingleValueColumn::class.java, SingleValueColumn.deserializer)
                //
                .setPrettyPrinting()
                .create()
        }
    }
}



