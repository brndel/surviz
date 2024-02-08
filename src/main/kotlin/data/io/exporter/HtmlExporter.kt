package data.io.exporter

import data.io.exporter.result.ExportResult
import data.io.exporter.result.errors.ExportError
import data.project.Project
import data.project.data.Block
import data.project.data.Situation
import data.resources.fields.*
import kotlinx.coroutines.*
import ui.Labels
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.File


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

    private const val DEFAULT_SCHEME = "block_\$block\$_situation_\$situation\$"
    private const val SEPARATE_OPTION_KEY = "separate_options"

// TODO("UNIPARK VARIABLE")
//    private const val UNIPARK_VAR = "unipark_variable"


    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields of the exporter.
     */
    override fun getFields(project: Project): List<NamedField> {
        val fields: ArrayList<NamedField> = ArrayList()

        //  Configure situations to export
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

//// Field for unipark variable
////        fields.add(
////            NamedField(
////                UNIPARK_VAR,
////                StringFieldData("v10", "Choose a unipark variable")
////            )
////        )

        // Configure output files
        fields.add(
            NamedField(
                PATH_KEY,
                StringFieldData("/Users/benicio/Desktop/Test1", Labels.EXPORT_OUTPUT_PATH)
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
            blocks.addAll(project.data.blocks)
        } else {
            val block = exportConfig[BLOCK_KEY].toString().toInt()
            blocks.add(project.data.blocks[block - 1])
        }

        val situation = exportConfig[SITUATION_KEY].toString().toInt()

        // Generate Images with Selection
        val pngExportConfig = exportConfig.toMutableMap()
        pngExportConfig[SCHEME_KEY] = "block_\$block\$_situation_\$situation\$_option_\$option\$"
        pngExportConfig[PATH_KEY] = "/Users/benicio/Desktop/Test1/images"
        pngExportConfig[SEPARATE_OPTION_KEY] = "true"

        PngExporter.export(project, pngExportConfig)

        // Generate HTML Files with Selection
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
                            situation
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
        situationId: Int
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
                    saveSituation(situation, id, blockId, scheme, path)
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
        path: String
    ): List<ExportError?> {
        // Generate HTML Document
        val htmlContent = buildString {
            appendHTML().html {
                head {
                    getHeader(situationId, blockId)
                }
                body {
                    getOptions(situation, blockId, situationId)
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
