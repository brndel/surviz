package data.project


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.gson.*
import data.generator.resources.ImageConfig
import data.project.config.*
import data.project.config.columns.*
import data.project.data.Block
import data.project.data.DataScheme
import data.project.data.IconStorage
import data.project.data.Situation
import data.resources.exceptions.CorruptFileException
import data.resources.exceptions.FileTypeException
import data.resources.exceptions.InvalidVersionException
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
data class Project(
    private val data: MutableState<ProjectData>,
    private val dataScheme: MutableState<DataScheme>,
    val configuration: ProjectConfiguration = ProjectConfiguration(),
    val iconStorage: IconStorage = IconStorage()
) {
    fun getDataScheme(): DataScheme = dataScheme.value

    fun getAllBlocks(): List<Block> {
        return data.value.getBlocks()
    }

    fun getMaxBlockID(): Int {
        return data.value.getMaxBlockID()
    }

    fun getMaxSituationID(id: Int): Int {
        return data.value.getMaxSituationID(id)
    }

    fun isValidBlockID(blockId: Int): Boolean {
        return data.value.getBlock(blockId) != null
    }

    fun isValidSituationID(blockId: Int, situationId: Int): Boolean {
        return data.value.getBlock(blockId)?.getSituation(situationId) != null
    }

    fun hasReachedMax(blockId: Int, situationId: Int): Boolean {
        return blockId == getMaxBlockID() && situationId == getMaxSituationID(blockId)
    }

    fun hasReachedMin(blockId: Int, situationId: Int): Boolean {
        return blockId == 1 && situationId == 1
    }

    fun getBlock(id: Int): Block? {
        return data.value.getBlock(id)
    }

    fun getSituation(block: Int, situation: Int): Situation? {
        return data.value.getSituation(block, situation)
    }

    /**
     * This method loads the project data.
     * @param data The project data to load.
     * @param force If true, the project data will be loaded even if the current [DataScheme]
     * differs from the new one.
     * @return True if the project data was loaded successfully, false otherwise.
     */
    fun loadProjectData(data: ProjectData, force: Boolean = false): Boolean {
        if (force || dataScheme.value.compareTo(data.dataScheme)) {
            this.data.value = data
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

        file.parentFile.mkdirs()

        if (!file.exists()) {
            file.createNewFile()
        }
        file.setWritable(true)

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
            val config = ProjectConfiguration()
            config.addSingleValue()
            return Project(
                mutableStateOf(data),
                mutableStateOf(data.dataScheme),
                config,
                IconStorage()
            )
        }

        /**
         * This method loads an existing project from a file.
         * @param projectFile The project file to load.
         */
        fun loadProjectFromFile(projectFile: File): Project {
            if (projectFile.extension != "svz") {
                throw FileTypeException(projectFile.extension)
            }

            val file = projectFile.readText()

            return GSON.fromJson(file, Project::class.java)
        }

        @Suppress("RemoveRedundantQualifierName")
        private val GSON: Gson by lazy {
            GsonBuilder()
                .registerTypeAdapter(Project::class.java, Project.serializer)
                .registerTypeAdapter(Project::class.java, Project.deserializer)
                .registerTypeAdapter(IconStorage::class.java, IconStorage.serializer)
                .registerTypeAdapter(IconStorage::class.java, IconStorage.deserializer)
                // Project Config
                .registerTypeAdapter(
                    ProjectConfiguration::class.java,
                    ProjectConfiguration.serializer
                )
                .registerTypeAdapter(
                    ProjectConfiguration::class.java,
                    ProjectConfiguration.deserializer
                )
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
                .registerTypeAdapter(
                    SingleValueDummyMap::class.java,
                    SingleValueDummyMap.serializer
                )
                .registerTypeAdapter(
                    SingleValueDummyMap::class.java,
                    SingleValueDummyMap.deserializer
                )
                .registerTypeAdapter(SingleValueDummy::class.java, SingleValueDummy.serializer)
                .registerTypeAdapter(SingleValueDummy::class.java, SingleValueDummy.deserializer)
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

        private val serializer = JsonSerializer<Project> { value, _, ctx ->
            val obj = JsonObject()

            obj.addProperty("version", VERSION)
            obj.add("configuration", ctx.serialize(value.configuration))
            obj.add("dataScheme", ctx.serialize(value.dataScheme.value))
            obj.add("iconStorage", ctx.serialize(value.iconStorage))
            obj.add("data", ctx.serialize(value.data.value))

            obj
        }

        private val deserializer = JsonDeserializer<Project> { element, _, ctx ->
            val obj = element.asJsonObject ?: throw CorruptFileException()

            val version = if (obj.has("version")) obj.get("version").asString else null
            if (version == null || version != VERSION) {
                throw InvalidVersionException(version ?: "null", VERSION)
            }

            val configuration =
                ctx.deserialize<ProjectConfiguration>(
                    obj.get("configuration"),
                    ProjectConfiguration::class.java
                )
            val dataScheme =
                ctx.deserialize<DataScheme>(obj.get("dataScheme"), DataScheme::class.java)
            val iconStorage =
                ctx.deserialize<IconStorage>(obj.get("iconStorage"), IconStorage::class.java)
            val data = ctx.deserialize<ProjectData>(obj.get("data"), ProjectData::class.java)

            Project(
                mutableStateOf(data),
                mutableStateOf(dataScheme),
                configuration,
                iconStorage
            )
        }

        private const val VERSION = "1.4"
    }
}



