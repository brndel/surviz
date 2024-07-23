package data.io.exporter

import data.io.exporter.Exporter.Companion.getNameFromScheme
import data.io.utils.result.ExportResult
import data.io.utils.result.warnings.ExportWarning
import data.io.utils.result.warnings.InvalidBlockWarning
import data.io.utils.result.warnings.InvalidSituationWarning
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
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.pathString
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

    private const val DEFAULT_SCHEME = "block_\$block\$_situation_\$situation\$.html"
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
        val situationId: Int,
        val has999: Boolean,
        val value999: Double,
    )

    /**
     * This method returns the fields that the UI uses to configure the export.
     * @return The fields of the exporter.
     */
    override fun getFields(): List<NamedField> {
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
    override fun export(
        project: Project,
        exportConfig: Map<String, Any>,
        onPathSelected: ((Path) -> Unit)?
    ): ExportResult {
        // Configure Export Selection
        val scheme = exportConfig[SCHEME_KEY] as String
        val path = exportConfig[PATH_KEY] as String
        onPathSelected?.let { it(Path.of(path)) }
        val allBlocks = exportConfig[ALL_BLOCK_KEY] as Boolean
        val allSituations = exportConfig[ALL_SITUATION_KEY] as Boolean

        val blocks = ArrayList<Block>()
        if (allBlocks) {
            blocks.addAll(project.getAllBlocks())
        } else {
            val blockId = exportConfig[BLOCK_KEY] as Int
            val block = project.getBlock(blockId)
            if (block != null) {
                project.getBlock(blockId)?.let { blocks.add(it) }
            } else {
                return ExportResult(arrayListOf(InvalidBlockWarning(blockId)))
            }
        }

        val situationId = exportConfig[SITUATION_KEY] as Int

        val has999 = exportConfig["has999"] as Boolean
        val value999 = exportConfig["value999"] as Double

        val config = Config(
            scheme,
            path,
            allBlocks,
            allSituations,
            blocks,
            situationId,
            has999,
            value999,
        )

        // Generate Images with Selection
        val pngExportConfig = exportConfig.toMutableMap()
        pngExportConfig[SCHEME_KEY] = PngExporter.DEFAULT_SCHEME
        pngExportConfig[PATH_KEY] = Path(path, "images").pathString
        pngExportConfig[SEPARATE_OPTION_KEY] = true

        val pngWarnings = PngExporter.export(project, pngExportConfig, null)

        val errorList = ArrayList<ExportWarning?>()

        CoroutineScope(Dispatchers.IO).launch {
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
        errorList.addAll(pngWarnings.warnings)
        return ExportResult(errorList.filterNotNull())
    }

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

    private fun saveSituation(
        config: Config,
        situation: Situation,
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

            appendHTML(false).div {
                this.classes = setOf("radio-toolbar")

                +lineSeparator
                ul {
                    +lineSeparator
                    +lineSeparator
                    getOption(situation, blockId, config)
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
            "situation" to situation.id.toString()
        )

        val filePath = Path(config.path, fileName).pathString
        val outputFile = File(filePath)
        outputFile.writeText(htmlContent)
        println("HTML-Datei wurde unter ${outputFile.absolutePath} erstellt.")

        return listOfNotNull()
    }

    private fun UL.getOption(situation: Situation, blockId: Int, config: Config) {
        val lineSeparator = System.lineSeparator()

        for (option in situation.options.values) {
            if (config.has999 && !option.containsValue(config.value999)) {
                li {
                    +lineSeparator
                    input(InputType.radio) {
                        this.id = option.name
                        this.name = "PLACEHOLDER"
                        this.value = option.name
                        this.classes = setOf("input-hidden")
                        this.onClick = "change('${option.name}')"
                    }
                    +lineSeparator
                    label {
                        this.htmlFor = option.name
                        this.id = "${option.name}-label"
                        img {
                            this.src = getImgSrc(blockId, situation.id, option.name)
                            this.alt = "Travel by ${option.name}"
                        }
                    }
                    +lineSeparator
                }
                +lineSeparator
                +lineSeparator
            }
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

    private fun getImgSrc(blockId: Int, situationId: Int, optionName: String): String {
        val fileName = getNameFromScheme(
            PngExporter.DEFAULT_SCHEME,
            "block" to blockId.toString(),
            "situation" to situationId.toString(),
            "option" to optionName
        )

        return Path("images", "$fileName").pathString
    }
}