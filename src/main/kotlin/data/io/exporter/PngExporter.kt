package data.io.exporter

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import data.generator.ImageGenerator
import data.io.exporter.Exporter.Companion.getNameFromScheme
import data.io.utils.result.ExportResult
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.IllegalCharacterWarning
import data.io.utils.result.warnings.ImageSizeExportWarning
import data.io.utils.result.warnings.InvalidBlockWarning
import data.io.utils.result.warnings.InvalidSituationWarning
import data.project.Project
import data.project.data.Block
import data.project.data.Situation
import data.project.data.SituationOption
import data.resources.fields.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ui.Labels
import util.platformPath
import java.awt.image.BufferedImage
import java.io.File
import java.nio.file.InvalidPathException
import java.nio.file.Path
import javax.imageio.ImageIO
import kotlin.io.path.Path

/**
 * This class implements the [Exporter] interface and exports the project to a PNG file.
 */
object PngExporter : Exporter {

    private const val BLOCK_KEY = "block"
    private const val ALL_BLOCK_KEY = "all_blocks"
    private const val SITUATION_KEY = "situation"
    private const val ALL_SITUATION_KEY = "all_situations"
    private const val SEPARATE_OPTION_KEY = "separate_options"
    private const val PATH_KEY = "path"
    private const val SCHEME_KEY = "scheme"

    const val DEFAULT_SCHEME = "block_\$block\$_situation_\$situation\$_option_\$option\$.png"

    private val defaultPath: String by lazy {
        platformPath(windows = {
            "C:\\Users\\$it\\Desktop\\SurViz\\images"
        }, linux = {
            "/home/$it/surviz/images"
        }, mac = {
            "/Users/$it/surviz/images"
        })
    }

    private lateinit var imageGenerator: ImageGenerator

    private data class Config(
        val scheme: String,
        val path: String,
        val allBlocks: Boolean,
        val allSituations: Boolean,
        val blocks: ArrayList<Block>,
        val situationId: Int,
        val separateOptions: Boolean
    )

    ///////////////////////////////////////////////////////////////////////////////
    // public functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields that can be used in the export configuration.
     */
    override fun getFields(): List<NamedField> {
        val fields: ArrayList<NamedField> = ArrayList()

        fields.add(
            NamedField(
                ALL_BLOCK_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_BLOCKS)
            )
        )

        // configure situations to export
        fields.add(NamedField(BLOCK_KEY, IntFieldData(1, Labels.BLOCK)))

        fields.add(
            NamedField(
                ALL_SITUATION_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_SITUATIONS)
            )
        )

        fields.add(NamedField(SITUATION_KEY, IntFieldData(1, Labels.SITUATION, 1, Int.MAX_VALUE)))

        fields.add(
            NamedField(
                SEPARATE_OPTION_KEY,
                BooleanFieldData(true, Labels.EXPORT_SEPARATE_OPTIONS)
            )
        )

        // configure output files
        fields.add(
            NamedField(
                PATH_KEY,
                StringFieldData(defaultPath, Labels.EXPORT_OUTPUT_PATH, StringFieldHint.Directory)
            )
        )
        fields.add(
            NamedField(
                SCHEME_KEY,
                FileSchemeFieldData(
                    DEFAULT_SCHEME,
                    Labels.EXPORT_FILE_NAME_SCHEME,
                    arrayListOf("block", "situation", "option")
                )
            )
        )
        return fields
    }

    override fun export(
        project: Project,
        exportConfig: Map<String, Any>,
        onPathSelected: ((Path) -> Unit)?
    ): ExportResult {
        imageGenerator = ImageGenerator(project.configuration, project.iconStorage)

        val scheme = exportConfig[SCHEME_KEY] as String
        val path = exportConfig[PATH_KEY] as String
        onPathSelected?.let { it(Path.of(path)) }

        val blocks = ArrayList<Block>()
        val allBlocks = exportConfig[ALL_BLOCK_KEY] as Boolean

        if (allBlocks) {
            blocks.addAll(project.getAllBlocks())
        } else {
            val blockId = exportConfig[BLOCK_KEY] as Int
            val block = project.getBlock(blockId)
            if (block != null) {
                blocks.add(block)
            } else {
                return ExportResult(arrayListOf(InvalidBlockWarning(blockId)))
            }
        }

        val allSituations = exportConfig[ALL_SITUATION_KEY] as Boolean
        val situationId = exportConfig[SITUATION_KEY] as Int

        val separateOptions = exportConfig[SEPARATE_OPTION_KEY] as Boolean

        val config = Config(
            scheme,
            path,
            allBlocks,
            allSituations,
            blocks,
            situationId,
            separateOptions
        )

        val errorList = ArrayList<ExportWarning?>()


        val coroutine = CoroutineScope(Dispatchers.IO).launch {
            val widthList = coroutineScope {
                blocks.map { block ->
                    async {
                        saveBlock(
                            config,
                            block
                        )
                    }
                }.awaitAll()
            }
            errorList.addAll(widthList.flatten())
        }

        runBlocking {
            coroutine.join()
        }
        return ExportResult(errorList.filterNotNull())
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

    private suspend fun saveBlock(
        config: Config,
        block: Block
    ): List<ExportWarning?> {
        val situations = ArrayList<Situation>()

        if (config.allSituations) {
            situations.addAll(block.getSituations())
        } else {
            val situation = block.getSituation(config.situationId)
            if (situation != null) {
                situations.add(situation)
            } else {
                return arrayListOf(InvalidSituationWarning(block.id, config.situationId))
            }
        }

        val resultList = coroutineScope {
            situations.map { situation ->
                async {
                    saveSituation(config, situation, block.id)
                }
            }.awaitAll()
        }
        return resultList.flatten()
    }

    private suspend fun saveSituation(
        config: Config,
        situation: Situation,
        blockId: Int
    ): List<ExportWarning?> {
        // check if options need to be separated
        if (!config.separateOptions) {
            // save whole option
            val imageResult = imageGenerator.generateSituation(situation)
            val fileName = getNameFromScheme(
                config.scheme,
                "block" to blockId.toString(),
                "situation" to situation.id.toString()
            )
            saveBitmap(imageResult.image, config.path, fileName)
            if (!imageResult.checkWidth()) {
                return arrayListOf(
                    ImageSizeExportWarning(
                        imageResult.neededWidth,
                        blockId,
                        situation.id
                    )
                )
            }
            return arrayListOf()
        }

        val errorList = coroutineScope {
            situation.options.values.map { option ->
                async {
                    saveOption(option, situation.id, blockId, config)
                }
            }.awaitAll()
        }
        return errorList
    }

    private fun saveOption(
        option: SituationOption,
        situationId: Int,
        blockId: Int,
        config: Config
    ): ExportWarning? {
        val imageResult = imageGenerator.generateOption(option)
        val fileName = getNameFromScheme(
            config.scheme,
            "block" to blockId.toString(),
            "situation" to situationId.toString(),
            "option" to option.name
        )
        val saveResult = saveBitmap(imageResult.image, config.path, fileName)
        if(saveResult != null) {
            return saveResult
        }
        if (!imageResult.checkWidth()) {
            return ImageSizeExportWarning(imageResult.neededWidth, blockId, situationId, option.name)
        }
        return null
    }

    private fun saveBitmap(bitmap: ImageBitmap, path: String, name: String): ExportWarning? {
        val bufferedImage: BufferedImage
        val file: File

        try {
            val outputPath = Path(path, name)
            bufferedImage = bitmap.toAwtImage()
            file = outputPath.toFile()
        }
        catch (e: InvalidPathException) {
            return IllegalCharacterWarning(e.input[e.index].toString())
        }
        file.parentFile.mkdirs()

        if (!file.exists()) {
            file.createNewFile()
        }

            ImageIO.write(bufferedImage, "png", file)
        return null
    }
}
