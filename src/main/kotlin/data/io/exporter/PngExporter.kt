package data.io.exporter

import data.project.Project
import data.resources.fields.BooleanFieldData
import data.resources.fields.FileSchemeFieldData
import data.resources.fields.IntFieldData
import data.resources.fields.NamedField
import data.resources.fields.OptionsFieldData
import data.resources.fields.StringFieldData
import ui.Labels

/**
 * This class implements the [Exporter] interface and exports the project to a PNG file.
 */
object PngExporter : Exporter {
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
        fields.add(NamedField("block", OptionsFieldData("1", Labels.BLOCK, blockOptionList)))
        fields.add(NamedField("all blocks", BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_BLOCKS)))

        fields.add(NamedField("situation", IntFieldData(1, Labels.SITUATION, 1, Int.MAX_VALUE)))
        fields.add(NamedField("all situations", BooleanFieldData(true, Labels.EXPORT_SELECT_ALL_SITUATIONS)))

        fields.add(NamedField("separate options", BooleanFieldData(true, Labels.EXPORT_SEPARATE_OPTIONS)))

        // configure output files
        fields.add(NamedField("path", StringFieldData("C:\\Users\\user\\Desktop", Labels.EXPORT_OUTPUT_PATH)))
        fields.add(
            NamedField(
                "scheme",
                FileSchemeFieldData(
                    "block_\$block\$_situation_\$situation\$_option_\$option\$",
                    Labels.EXPORT_FILE_NAME_SCHEME,
                    arrayListOf("block", "situation", "option")
                )
            )
        )
        return fields
    }

    override fun export(project: Project, exportConfig: Map<String, Any>) {

    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////
}
