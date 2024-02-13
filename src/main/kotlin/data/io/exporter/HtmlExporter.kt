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
//    private fun htmlScript(): String {
//        val newLine = System.lineSeparator()
//
//        return "function change(value) {$newLine" +
//                "document.getElementById(\"PLACEHOLDER\").value = value;$newLine" +
//                "}" + newLine
//    }
//
//    private fun htmlStyle(): String {
//        return """
//        ul {
//        list-style-type: none;
//        }
//
//        .radio-toolbar input[type="radio"] {
//            display: none;
//        }
//
//        .radio-toolbar label {
//          display: inline-block;
//          background-color: #ddd;
//          padding: 4px 11px;
//          font-family: Arial;
//          font-size: 16px;
//          cursor: pointer;
//        }
//
//        .radio-toolbar input[type="radio"]:checked+label {
//          background-color: #bbb;
//        }
//
//        """.trimIndent()
//    }

    private data class Config(
        val scheme: String,
        val path: String,
        val allBlocks: Boolean,
        val allSituations: Boolean,
        val blocks: ArrayList<Block>,
        val situation: Int
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

        fields.add(NamedField(BLOCK_KEY, IntFieldData(1, label = Labels.BLOCK)))

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
        val path = exportConfig[PATH_KEY] as String
        val allBlocks = exportConfig[ALL_BLOCK_KEY] as Boolean
        val allSituations = exportConfig[ALL_SITUATION_KEY] as Boolean

        val blocks = ArrayList<Block>()
        if (allBlocks) {
            blocks.addAll(project.getAllBlocks())
        } else {
            val blockId = exportConfig[BLOCK_KEY] as Int
            val block = project.getBlock(blockId)
            if ( block != null) {
                project.getBlock(blockId)?.let { blocks.add(it) }
            } else {
                // TODO return ExportWarning
            }
        }

        val situation = exportConfig[SITUATION_KEY].toString().toInt()

        val config = Config(
            scheme,
            path,
            allBlocks,
            allSituations,
            blocks,
            situation
        )

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
                        saveBlock(
                            config,
                            block,
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
        config: Config,
        block: Block,
        situationId: Int
    ): List<ExportWarning?> {
        val situations = ArrayList<Situation>()

        if (config.allSituations) {
            situations.addAll(block.getSituations())
        } else {
            val situation = block.getSituation(situationId)
            if(situation != null) {
                situations.add(situation)
            } else {
                // TODO return ExportWarning
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

    private fun saveSituation(
        config: Config,
        situation: Situation,
        blockId: Int,
    ): List<ExportWarning?> {
        val newLine = System.lineSeparator()
        // Generate HTML Document
        val htmlContent = buildString {
            append(HTML_SCRIPT)
            append(newLine)
            append(HTML_STYLE)
            append(newLine)
            append(newLine)

            appendHTML(false).div{
                this.classes = setOf("radio-toolbar")

                +newLine
                ul {
                    +newLine
                    +newLine
                    getOption(situation, blockId, situation.id)
                }
                +newLine
            }
            append(newLine)
            append(newLine)

            appendHTML(false).form {
                getForm()
            }
        }

        val fileName = getNameFromScheme(
            config.scheme,
            "block" to blockId.toString(),
            "situation" to situation.id.toString()
        )

        val filePath = config.path + fileName + ".html"
        val outputFile = File(filePath)
        outputFile.writeText(htmlContent)
        println("HTML-Datei wurde unter ${outputFile.absolutePath} erstellt.")

        return listOfNotNull()
    }

    private fun UL.getOption(situation: Situation, blockId: Int) {

        for((optionId, option) in situation.options.values.withIndex()) {
            li {
                +System.lineSeparator()
                input(InputType.radio) {
                    this.id = "x$optionId"
                    this.name = "PLACEHOLDER"
                    this.value = optionId.toString()
                    this.classes = setOf("input-hidden")
                    this.onClick = "change('${option.name}')"
                }
                +System.lineSeparator()
                label {
                    this.htmlFor = "x$optionId"
                    this.id = "x$optionId-label"
                    img {
                        this.src = getImgSrc(blockId, situation.id, optionId)
                        this.alt = "Travel by ${option.name}"
                    }
                }
                +System.lineSeparator()
            }
            +System.lineSeparator()
            +System.lineSeparator()
        }
    }

    private fun FORM.getForm() {
        div {
            +System.lineSeparator()
            input(InputType.text) {
                this.id = "PLACEHOLDER"
                this.name = "PLACEHOLDER"
                this.value = "#PLACEHOLDER#"
            }
            +System.lineSeparator()
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