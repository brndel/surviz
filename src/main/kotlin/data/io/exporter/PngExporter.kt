package data.io.exporter

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toAwtImage
import data.generator.ImageGenerator
import data.io.exporter.resources.ExportResult
import data.io.exporter.resources.errors.ExportError
import data.io.exporter.resources.errors.ImageSizeExportError
import data.project.Project
import data.project.data.Block
import data.project.data.Situation
import data.project.data.SituationOption
import data.resources.fields.BooleanFieldData
import data.resources.fields.FileSchemeFieldData
import data.resources.fields.IntFieldData
import data.resources.fields.NamedField
import data.resources.fields.OptionsFieldData
import data.resources.fields.StringFieldData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import ui.Labels
import java.io.File
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

    private val defaultPath: String
        get() {
            val osName = System.getProperty("os.name").lowercase()
            return if (osName.startsWith("win")) {
                "C:\\Users\\${System.getProperty("user.name")}\\Desktop\\SurViz"
            } else {
                "/home/${System.getProperty("user.name")}/surviz"
            }
        }

    private lateinit var imageGenerator: ImageGenerator

    ///////////////////////////////////////////////////////////////////////////////
    // public functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields that can be used in the export configuration.
     */
    override fun getFields(project: Project): List<NamedField> {
        val fields: ArrayList<NamedField> = ArrayList()

        // configure situations to export
        val blockOptionList = ArrayList<String>().apply {
            project.data.blocks.size.let { blockCount ->
                addAll((1..blockCount).map(Int::toString))
            }
        }
        fields.add(NamedField(BLOCK_KEY, OptionsFieldData("1", Labels.BLOCK, blockOptionList)))
        fields.add(
            NamedField(
                ALL_BLOCK_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_BLOCKS)
            )
        )

        fields.add(NamedField(SITUATION_KEY, IntFieldData(1, Labels.SITUATION, 1, Int.MAX_VALUE)))
        fields.add(
            NamedField(
                ALL_SITUATION_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_SITUATIONS)
            )
        )

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
                StringFieldData(defaultPath, Labels.EXPORT_OUTPUT_PATH)
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

        val scheme = exportConfig[SCHEME_KEY].toString()
        val path = exportConfig[PATH_KEY].toString()

        val blocks = ArrayList<Block>()
        val allBlocks = exportConfig[ALL_BLOCK_KEY].toString().toBoolean()

        if (allBlocks) {
            blocks.addAll(project.data.blocks)
        } else {
            val block = exportConfig[BLOCK_KEY].toString().toInt()
            blocks.add(project.data.blocks[block - 1])
        }

        val allSituations = exportConfig[ALL_SITUATION_KEY].toString().toBoolean()
        val situation = exportConfig[SITUATION_KEY].toString().toInt()

        val separateOptions = exportConfig[SEPARATE_OPTION_KEY].toString().toBoolean()

        val errorList = ArrayList<ExportError?>()

        CoroutineScope(Dispatchers.IO).launch {
            val widthList = coroutineScope {
                blocks.map { block ->
                    async {
                        val blockId = project.data.blocks.indexOf(block) + 1
                        saveBlock(
                            block,
                            blockId,
                            scheme,
                            path,
                            allSituations,
                            situation,
                            separateOptions
                        )
                    }
                }.awaitAll()
            }
            errorList.addAll(widthList.flatten())
        }
        return ExportResult(errorList.filterNotNull())
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

    private suspend fun saveBlock(
        block: Block,
        blockId: Int,
        scheme: String,
        path: String,
        allSituations: Boolean,
        situationId: Int,
        separateOptions: Boolean
    ): List<ExportError?> {
        val situations = ArrayList<Situation>()

        if (allSituations) {
            situations.addAll(block.situations)
        } else {
            situations.add(block.situations[situationId - 1])
        }

        val resultList = coroutineScope {
            situations.map { situation ->
                async {
                    val id = block.situations.indexOf(situation) + 1
                    saveSituation(situation, id, blockId, scheme, path, separateOptions)
                }
            }.awaitAll()
        }
        return resultList.flatten()
    }

    private suspend fun saveSituation(
        situation: Situation,
        situationId: Int,
        blockId: Int,
        scheme: String,
        path: String,
        separateOptions: Boolean
    ): List<ExportError?> {
        // check if options need to be separated
        if (!separateOptions) {
            // save whole option
            val imageResult = imageGenerator.generateSituation(situation)
            // TODO("maybe exclude the situation placeholder?")
            val fileName = getNameFromScheme(
                scheme,
                "block" to blockId.toString(),
                "situation" to situationId.toString()
            )
            saveBitmap(imageResult.image, path, fileName)
            if (imageResult.neededWidth > imageResult.image.width) {
                return arrayListOf(ImageSizeExportError(imageResult.neededWidth, blockId, situationId))
            }
            return arrayListOf()
        }

        val errorList = coroutineScope {
            situation.options.map { option ->
                async {
                    val id = situation.options.indexOf(option) + 1
                    saveOption(option, id, situationId, blockId, scheme, path)
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
        scheme: String,
        path: String
    ): ExportError? {
        val imageResult = imageGenerator.generateOption(option)
        val fileName = getNameFromScheme(
            scheme,
            "block" to blockId.toString(),
            "situation" to situationId.toString(),
            "option" to optionId.toString()
        )
        saveBitmap(imageResult.image, path, fileName)
        if (imageResult.neededWidth > imageResult.image.width) {
            return ImageSizeExportError(imageResult.neededWidth, blockId, situationId, optionId)
        }
        return null
    }

    private fun getNameFromScheme(template: String, vararg values: Pair<String, String>): String {
        var result = template
        values.forEach { (placeholder, replacement) ->
            result = result.replace("\$$placeholder\$", replacement)
        }
        return result
    }

    private fun saveBitmap(bitmap: ImageBitmap, path: String, name: String) {
        val outputPath = Path(path, "$name.png")

        val bufferedImage = bitmap.toAwtImage()
        val outputFile = outputPath.toFile()

        if (!outputFile.exists()) {
            outputFile.mkdirs()
        }

        ImageIO.write(bufferedImage, "png", outputFile)
    }
}
