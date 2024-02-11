package data.io.exporter

import data.io.utils.result.ExportResult
import data.io.utils.result.warnings.ExportWarning
import data.project.Project
import data.project.data.Block
import data.project.data.Situation
import data.resources.fields.*
import kotlinx.coroutines.*
import ui.Labels
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import util.platformPath
import java.io.File
import kotlin.math.exp


/**
 * This class implements the [Exporter] interface and exports the project to an HTML file.
 */
object HtmlExporter : Exporter {
    private const val BLOCK_KEY = "block"
    private const val ALL_BLOCK_KEY = "all_blocks"
    private const val SITUATION_KEY = "situation"
    private const val ALL_SITUATION_KEY = "all_situations"
    private const val PATH_KEY = "path"
    private const val SCHEME_KEY = "scheme"
    private const val SEPARATE_OPTION_KEY = "separate_options"

    private const val NEEDS_VERSION_NUMBER_KEY = "needs_version_number"
    private const val VERSION_NUMBER_KEY = "version_number"

    private const val DEFAULT_SCHEME = "block_\$block\$_situation_\$situation\$"
    private val defaultPath: String by lazy {
        platformPath(windows = {
            "C:\\Users\\$it\\Desktop\\SurViz\\"
        }, linux = {
            "/home/$it/surviz/"
        }, mac = {
            "/Users/$it/surviz/"
        })
    }

// TODO("UNIPARK VARIABLE")
//    private const val UNIPARK_VAR = "unipark_variable"


    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields of the exporter.
     */
    override fun getFields(project: Project): List<NamedField> {
        val fields: ArrayList<NamedField> = ArrayList()

        fields.add(
            NamedField(
                ALL_BLOCK_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_BLOCKS)
            )
        )

        fields.add(
            NamedField(
                ALL_SITUATION_KEY,
                BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_SITUATIONS)
            )
        )

        fields.add(NamedField(BLOCK_KEY, IntFieldData(1, Labels.BLOCK, 1, Int.MAX_VALUE)))
        fields.add(NamedField(SITUATION_KEY, IntFieldData(1, Labels.SITUATION, 1, Int.MAX_VALUE)))

        // Field for unipark variable
        fields.add(
            NamedField(
                NEEDS_VERSION_NUMBER_KEY,
                BooleanFieldData(true,Labels.EXPORT_HTML_INCLUDE_VERSION)
            )
        )

        fields.add(NamedField(VERSION_NUMBER_KEY, IntFieldData(10, Labels.EXPORT_HTML_VERSION_NUMBER, 1, Int.MAX_VALUE)))

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
                    arrayListOf("block", "situation")
                )
            )
        )

        return fields
    }

    /**
     * This method exports the project to an HTML file.
     *
     * @param project The project to export.
     * @param exportConfig The export configuration.
     */
    override fun export(project: Project, exportConfig: Map<String, Any>): ExportResult {
        // Configure Export Selection
        val scheme = exportConfig[SCHEME_KEY].toString()
        val path = exportConfig[PATH_KEY].toString()
        val allBlocks = exportConfig[ALL_BLOCK_KEY].toString().toBoolean()
        val allSituations = exportConfig[ALL_SITUATION_KEY].toString().toBoolean()

        val blocks = ArrayList<Block>()
        if (allBlocks) {
            blocks.addAll(project.getData().blocks)
        } else {
            val block = exportConfig[BLOCK_KEY].toString().toInt()
            blocks.add(project.getData().blocks[block - 1])
        }

        val situation = exportConfig[SITUATION_KEY].toString().toInt()

        val needsVersionNumber = exportConfig[NEEDS_VERSION_NUMBER_KEY].toString().toBoolean()
        val versionNumber = exportConfig[VERSION_NUMBER_KEY].toString().toInt()

        // Generate Images with Selection
        val pngExportConfig = exportConfig.toMutableMap()
        pngExportConfig[SCHEME_KEY] = "block_\$block\$_situation_\$situation\$_option_\$option\$"
        pngExportConfig[PATH_KEY] = path + "images"
        pngExportConfig[SEPARATE_OPTION_KEY] = true

        PngExporter.export(project, pngExportConfig)

        // Generate HTML Files with Selection
        val errorList = ArrayList<ExportWarning?>()

        CoroutineScope(Dispatchers.IO).launch {
            val widthList = coroutineScope {
                blocks.map { block ->
                    async {
                        val blockId = project.getData().blocks.indexOf(block) + 1
                        saveBlock(
                            block,
                            blockId,
                            scheme,
                            path,
                            allSituations,
                            situation,
                            needsVersionNumber,
                            versionNumber
                        )
                    }
                }.awaitAll()
            }
            errorList.addAll(widthList.flatten())
        }
        return ExportResult(errorList.filterNotNull())
    }
    private suspend fun saveBlock(
        block: Block,
        blockId: Int,
        scheme: String,
        path: String,
        allSituations: Boolean,
        situationId: Int,
        needsVersionNumber: Boolean,
        versionNumber: Int
    ): List<ExportWarning?> {
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
                    saveSituation(situation, id, blockId, scheme, path, needsVersionNumber, versionNumber)
                }
            }.awaitAll()
        }
        return resultList.flatten()
    }

    private fun saveSituation(
        situation: Situation,
        situationId: Int,
        blockId: Int,
        scheme: String,
        path: String,
        needsVersionNumber: Boolean,
        versionNumber: Int
    ): List<ExportWarning?> {
        // Generate HTML Document
        val htmlContent = buildString {
            appendHTML().html {
                head {
                    getHeader(situationId, blockId)
                }
                body {
                    getOptions(situation, blockId, situationId)
                    if (needsVersionNumber){ getVersionNumber(versionNumber) }
                }
            }
        }

        val fileName = getNameFromScheme(
            scheme,
            "block" to blockId.toString(),
            "situation" to situationId.toString()
        )

        val outputFile = File("$path/$fileName.html")
        outputFile.writeText(htmlContent)
        println("HTML-Datei wurde unter ${outputFile.absolutePath} erstellt.")
//        saveHtmlFile(html, path, fileName)

        return listOfNotNull()
    }

    private fun HEAD.getHeader(situationId: Int, blockId: Int) {
        val blockNumber = "This is block number: $blockId"
        val situationNumber = "This is situation number: $situationId"

        title { +blockNumber }
        title { +situationNumber }
    }

    private fun BODY.getOptions(situation: Situation, blockId: Int, situationId: Int) {
        var optionId = 0
        val radioButtonName = situationId.toString()

        for(option in situation.options) {
            optionId ++

            label {
                style = "display: flex; align-items: center;"
                br()
                input(InputType.radio) {
                    this.id = optionId.toString()
                    this.name = radioButtonName
                    this.value = optionId.toString()
                }

                img {this.src = getImgSrc(blockId, situationId, optionId)}
                br()
            }
        }
    }

    private fun BODY.getVersionNumber(versionNumber: Int) {
        val version: String = "Version: $versionNumber"

        h1 {
            +version
        }
    }

    private fun getImgSrc(blockId: Int, situationId: Int, optionId: Int): String {
        return "images/block_$blockId" + "_situation_$situationId" + "_option_$optionId" + ".png"
    }

    private fun getNameFromScheme(template: String, vararg values: Pair<String, String>): String {
        var result = template
        values.forEach { (placeholder, replacement) ->
            result = result.replace("\$$placeholder\$", replacement)
        }
        return result
    }
}
