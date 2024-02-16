package data.io.exporter

import data.io.utils.result.ExportResult
import data.io.utils.result.warnings.ExportWarning
import data.project.Project
import data.project.data.Block
import data.project.data.Situation
import data.resources.fields.*
import kotlinx.coroutines.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import ui.Labels
import util.platformPath
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
    private const val SEPARATE_OPTION_KEY = "separate_options"

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

    private const val HTML_SCRIPT = """<script>
function change(value){
    document.getElementById("PLACEHOLDER").value= value;
}
</script>
    """

    private const val HTML_STYLE = """<style type="text/css">
.radio-toolbar input[type="radio"] {
  display: none;
}

.radio-toolbar label {
  display: inline-block;
  background-color: #ddd;
  padding: 4px 11px;
  font-family: Arial;
  font-size: 16px;
  cursor: pointer;
}

.radio-toolbar input[type="radio"]:checked+label {
  background-color: #bbb;
}

ul {
  list-style-type: none;
}
</style>"""

    private data class Config(
        val scheme: String,
        val path: String,
        val allBlocks: Boolean,
        val allSituations: Boolean,
        val blocks: ArrayList<Block>,
        val situationId: Int
    )

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

        val blockOptionList = ArrayList<String>().apply {
            project.getData().blocks.size.let { blockCount ->
                addAll((1..blockCount).map(kotlin.Int::toString))
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
        val scheme = exportConfig[SCHEME_KEY] as String
        val path = exportConfig[PATH_KEY]as String
        val allBlocks = exportConfig[ALL_BLOCK_KEY] as Boolean
        val allSituations = exportConfig[ALL_SITUATION_KEY] as Boolean

        val blocks = ArrayList<Block>()
        if (allBlocks) {
            blocks.addAll(project.getData().blocks)
        } else {
            val block = exportConfig[BLOCK_KEY].toString().toInt()
            blocks.add(project.getData().blocks[block - 1])
        }

        val situationId = exportConfig[SITUATION_KEY].toString().toInt()

        val config = Config(
            scheme,
            path,
            allBlocks,
            allSituations,
            blocks,
            situationId
        )

        // Generate Images with Selection
        val pngExportConfig = exportConfig.toMutableMap()
        pngExportConfig[SCHEME_KEY] = "block_\$block\$_situation_\$situation\$_option_\$option\$"
        pngExportConfig[PATH_KEY] = path + "images"
        pngExportConfig[SEPARATE_OPTION_KEY] = true

        val pngWarnings = PngExporter.export(project, pngExportConfig)

        val errorList = ArrayList<ExportWarning?>()

        CoroutineScope(Dispatchers.IO).launch {
            val widthList = coroutineScope {
                blocks.map { block ->
                    async {
                        val blockId = project.getData().blocks.indexOf(block) + 1
                        println("Save block: $blockId")
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
        errorList.addAll(pngWarnings.warnings)
        return ExportResult(errorList.filterNotNull())
    }
    private suspend fun saveBlock(
        config: Config,
        block: Block,
        blockId: Int
    ): List<ExportWarning?> {
        val situations = ArrayList<Situation>()

        if (config.allSituations) {
            situations.addAll(block.situations)
        } else {
            situations.add(block.situations[config.situationId - 1])
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

    private fun saveSituation(
        config: Config,
        situation: Situation,
        situationId: Int,
        blockId: Int,
    ): List<ExportWarning?> {
        val lineSeparator = System.lineSeparator()
        // Generate HTML Document
        val htmlContent = buildString {
            append(HTML_SCRIPT)
            append(lineSeparator)
            append(HTML_STYLE)
            append(lineSeparator)
            append(lineSeparator)

            appendHTML(false).div{
                this.classes = setOf("radio-toolbar")

                +lineSeparator
                ul {
                    +lineSeparator
                    +lineSeparator
                    getOption(situation, blockId, situationId)
                }
                +lineSeparator
            }
            append(lineSeparator)
            append(lineSeparator)

            appendHTML(false).form {
                getForm()
            }
        }

        val fileName = getNameFromScheme(
            config.scheme,
            "block" to blockId.toString(),
            "situation" to situationId.toString()
        )

        val filePath = config.path + fileName + ".html"
        val outputFile = File(filePath)
        outputFile.writeText(htmlContent)
        println("HTML-Datei wurde unter ${outputFile.absolutePath} erstellt.")

        return listOfNotNull()
    }

    private fun UL.getOption(situation: Situation, blockId: Int, situationId: Int) {
        var optionId = 0
        val lineSeparator = System.lineSeparator()

        for(option in situation.options) {
            optionId ++
            li {
                +lineSeparator
                input(InputType.radio) {
                    this.id = "x$optionId"
                    this.name = "PLACEHOLDER"
                    this.value = optionId.toString()
                    this.classes = setOf("input-hidden")
                    this.onClick = "change('${option.name}')"
                }
                +lineSeparator
                label {
                    this.htmlFor = "x$optionId"
                    this.id = "x$optionId-label"
                    img {
                        this.src = getImgSrc(blockId, situationId, optionId)
                        this.alt = "Travel by ${option.name}"
                    }
                }
                +lineSeparator
            }
            +lineSeparator
            +lineSeparator
        }
    }

    private fun FORM.getForm() {
        val lineSeparator = System.lineSeparator()

        div {
            +lineSeparator
            input(InputType.text) {
                this.id = "PLACEHOLDER"
                this.name = "PLACEHOLDER"
                this.value = "#PLACEHOLDER#"
            }
            +lineSeparator
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