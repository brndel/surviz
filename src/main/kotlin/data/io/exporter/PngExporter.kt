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
import data.io.utils.result.warnings.InvalidSegmentWarning
import data.io.utils.result.warnings.InvalidSituationWarning
import data.project.Project
import data.project.config.SituationConfig
import data.project.data.Block
import data.project.data.Situation
import data.project.data.SituationOption
import data.resources.exceptions.InvalidSegmentException
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
    private const val LEGEND_KEY = "legend"

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
        val situationIds: List<*>,
        val separateOptions: Boolean,
        val has999: Boolean,
        val value999: Double,
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
        fields.add(NamedField(BLOCK_KEY, RangedIntFieldData("1", Labels.BLOCK)))

        fields.add(
            NamedField(
                ALL_SITUATION_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_SITUATIONS)
            )
        )

        fields.add(NamedField(SITUATION_KEY, RangedIntFieldData("1", Labels.SITUATION)))

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

        fields.add(
            NamedField(
                LEGEND_KEY,
                BooleanFieldData(false, Labels.EXPORT_LEGEND)
            )
        )

        return fields
    }

    override fun export(
        project: Project,
        exportConfig: Map<String, Any>,
        onPathSelected: ((Path) -> Unit)?
    ): ExportResult {
        val errorList = ArrayList<ExportWarning?>()

        imageGenerator = ImageGenerator(project.configuration, project.iconStorage)

        val scheme = exportConfig[SCHEME_KEY] as String
        val path = exportConfig[PATH_KEY] as String
        onPathSelected?.let { it(Path.of(path)) }

        if (exportConfig[LEGEND_KEY] as Boolean) {
            val legend = project.configuration.legend
            val legendImage = imageGenerator.generateLegend(legend)
            saveBitmap(legendImage, path, "legend.png")
        }

        val blocks = ArrayList<Block>()
        val allBlocks = exportConfig[ALL_BLOCK_KEY] as Boolean

        if (allBlocks) {
            blocks.addAll(project.getAllBlocks())
        } else {
            try {
                val blockList = exportConfig[BLOCK_KEY] as List<*>
                for (blockId in blockList) {
                    if (blockId is Int) {
                        val block = project.getBlock(blockId)
                        if (block != null) {
                            blocks.add(block)
                        } else {
                            errorList.add(InvalidBlockWarning(blockId))
                        }
                    }
                }
            } catch (e: InvalidSegmentException) {
                return ExportResult(
                    arrayListOf(InvalidSegmentWarning(e.segment))
                )
            }
        }

        val allSituations = exportConfig[ALL_SITUATION_KEY] as Boolean

        val situationIds = exportConfig[SITUATION_KEY] as List<*>

        val separateOptions = exportConfig[SEPARATE_OPTION_KEY] as Boolean

        val has999 = exportConfig["has999"] as Boolean
        val value999 = exportConfig["value999"] as Double

        val config = Config(
            scheme,
            path,
            allBlocks,
            allSituations,
            blocks,
            situationIds,
            separateOptions,
            has999,
            value999
        )


        val coroutine = CoroutineScope(Dispatchers.IO).launch {
            val widthList = coroutineScope {
                blocks.map { block ->
                    async {
                        saveBlock(
                            config,
                            block,
                            project
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
        block: Block,
        project: Project
    ): List<ExportWarning?> {
        val resultList = ArrayList<ExportWarning?>()
        val situations = ArrayList<Situation>()
        try {
            if (config.allSituations) {
                situations.addAll(block.getSituations())
            } else {
                for (situationId in config.situationIds) {
                    if (situationId is Int) {
                        val situation = block.getSituation(situationId)
                        if (situation != null) {
                            situations.add(situation)
                        } else {
                            resultList.add(InvalidSituationWarning(block.id, situationId))
                        }
                    }
                }
            }
        } catch (e: InvalidSegmentException) {
            return arrayListOf(InvalidSegmentWarning(e.segment))
        }


        val asyncResultList = coroutineScope {
            situations.map { situation ->
                async {
                    saveSituation(config, situation, block.id, project)
                }
            }.awaitAll()
        }
        resultList.addAll(asyncResultList.flatten())
        return resultList
    }

    private suspend fun saveSituation(
        config: Config,
        situation: Situation,
        blockId: Int,
        project: Project
    ): List<ExportWarning?> {
        // check if options need to be separated
        val situationConfig = project.getSituationConfig(blockId, situation.id)
        if (!config.separateOptions) {
            // save whole option
            val imageResult = imageGenerator.generateSituation(situation, situationConfig)
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
                    if (config.has999 && option.containsValue(config.value999)) {
                        null
                    } else
                        saveOption(option, situation.id, blockId, config, situationConfig)
                }
            }.awaitAll()
        }
        return errorList.filterIsInstance<ExportWarning>()
    }

    private fun saveOption(
        option: SituationOption,
        situationId: Int,
        blockId: Int,
        config: Config,
        situationConfig: SituationConfig
    ): ExportWarning? {
        val imageResult = imageGenerator.generateOption(option, situationConfig)
        val fileName = getNameFromScheme(
            config.scheme,
            "block" to blockId.toString(),
            "situation" to situationId.toString(),
            "option" to option.name
        )
        val saveResult = saveBitmap(imageResult.image, config.path, fileName)
        if (saveResult != null) {
            return saveResult
        }
        if (!imageResult.checkWidth()) {
            return ImageSizeExportWarning(
                imageResult.neededWidth,
                blockId,
                situationId,
                option.name
            )
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
        } catch (e: InvalidPathException) {
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
