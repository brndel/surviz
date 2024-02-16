package data.io.exporter

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import data.generator.ImageGenerator
import data.io.exporter.Exporter.Companion.getNameFromScheme
import data.io.exporter.Exporter.Companion.isValidSituation
import data.io.utils.result.ExportResult
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.ImageSizeExportWarning
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

    private const val DEFAULT_SCHEME = "block_\$block\$_situation_\$situation\$_option_\$option\$"

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
    override fun getFields(project: Project): List<NamedField> {
        val fields: ArrayList<NamedField> = ArrayList()

        fields.add(
            NamedField(
                ALL_BLOCK_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_BLOCKS)
            )
        )

        // configure situations to export
        val blockOptionList = ArrayList<String>().apply {
            project.getData().blocks.size.let { blockCount ->
                addAll((1..blockCount).map(Int::toString))
            }
        }
        fields.add(NamedField(BLOCK_KEY, OptionsFieldData("1", Labels.BLOCK, blockOptionList)))

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

    override fun export(project: Project, exportConfig: Map<String, Any>): ExportResult {
        imageGenerator = ImageGenerator(project.configuration, project.iconStorage)

        val scheme = exportConfig[SCHEME_KEY] as String
        val path = exportConfig[PATH_KEY] as String

        val blocks = ArrayList<Block>()
        val allBlocks = exportConfig[ALL_BLOCK_KEY] as Boolean

        if (allBlocks) {
            blocks.addAll(project.getData().blocks)
        } else {
            val block = exportConfig[BLOCK_KEY].toString().toInt()
            blocks.add(project.getData().blocks[block - 1])
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
                        val blockId = project.getData().blocks.indexOf(block) + 1
                        saveBlock(
                            config,
                            block,
                            blockId
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
        blockId: Int
    ): List<ExportWarning?> {
        val situations = ArrayList<Situation>()

        if (config.allSituations) {
            situations.addAll(block.situations)
        } else {
            val situationId = config.situationId
            if (!isValidSituation(block, situationId)) {
                return arrayListOf(InvalidSituationWarning(blockId, situationId))
            }
            situations.add(block.situations[situationId - 1])
        }

        val resultList = coroutineScope {
            situations.map { situation ->
                async {
                    val situationId = block.situations.indexOf(situation) + 1
                    saveSituation(config, situation, situationId, blockId)
                }
            }.awaitAll()
        }
        return resultList.flatten()
    }

    private suspend fun saveSituation(
        config: Config,
        situation: Situation,
        situationId: Int,
        blockId: Int
    ): List<ExportWarning?> {
        // check if options need to be separated
        if (!config.separateOptions) {
            // save whole option
            val imageResult = imageGenerator.generateSituation(situation)
            // TODO("maybe exclude the situation placeholder?")
            val fileName = getNameFromScheme(
                config.scheme,
                "block" to blockId.toString(),
                "situation" to situationId.toString()
            )
            saveBitmap(imageResult.image, config.path, fileName)
            if (!imageResult.checkWidth()) {
                return arrayListOf(
                    ImageSizeExportWarning(
                        imageResult.neededWidth,
                        blockId,
                        situationId
                    )
                )
            }
            return arrayListOf()
        }

        val errorList = coroutineScope {
            situation.options.map { option ->
                async {
                    val id = situation.options.indexOf(option) + 1
                    saveOption(option, id, situationId, blockId, config)
                }
            }.awaitAll()
        }
        return errorList
    }

    private fun saveOption(
        option: SituationOption,
        optionId: Int,
        situationId: Int,
        blockId: Int,
        config: Config
    ): ExportWarning? {
        val imageResult = imageGenerator.generateOption(option)
        val fileName = getNameFromScheme(
            config.scheme,
            "block" to blockId.toString(),
            "situation" to situationId.toString(),
            "option" to optionId.toString()
        )
        saveBitmap(imageResult.image, config.path, fileName)
        if (!imageResult.checkWidth()) {
            return ImageSizeExportWarning(imageResult.neededWidth, blockId, situationId, optionId)
        }
        return null
    }

    private fun saveBitmap(bitmap: ImageBitmap, path: String, name: String) {
        val outputPath = Path(path, "$name.png")

        val bufferedImage = bitmap.toAwtImage()
        val file = outputPath.toFile()

        file.parentFile.mkdirs()

        if (!file.exists()) {
            file.createNewFile()
        }

        ImageIO.write(bufferedImage, "png", file)
    }
}
