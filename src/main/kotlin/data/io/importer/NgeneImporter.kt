package data.io.importer

import data.project.ProjectData
import data.project.data.Block
import data.project.data.DataScheme
import data.project.data.DataSchemeOption
import data.project.data.SituationOption
import data.resources.exceptions.CorruptFileException
import java.io.File

/**
 * This class implements the [Importer] interface and imports the project from an NGene file.
 */
object NgeneImporter : Importer {

    ////////////////////////////////////////////////////////////////////////////////
    // constants
    ///////////////////////////////////////////////////////////////////////////////
    private const val EXTENSION = "ngd"
    private const val SEPARATOR = "\t"
    private const val ALTERNATIVES_KEY = ";alts"
    private const val ROW_COUNT_KEY = ";rows"
    private const val BLOCK_COUNT_KEY = ";block"
    private const val BLOCK_KEY = "Block"

    ///////////////////////////////////////////////////////////////////////////////
    // public functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * This method imports a file and returns the project data.
     * @param file The file to import.
     * @return The project data.
     */
    override fun importFile(file: File): ProjectData {
        // get individual lines as string
        val lines = file.readLines(
            charset = Charsets.UTF_8
        )

        // get all keys for columns
        val columnKeys = ArrayList<String>()
        for (key in lines[0].split(SEPARATOR)) {
            if (key.isNotEmpty()) {
                columnKeys.add(key)
            }
        }

        // get alternatives
        val alternatives = getAlternatives(lines)
        val alternativeIndices = getAlternativeIndices(alternatives, columnKeys)

        // get row and block count
        val rowCount = getValueByKey(lines, ROW_COUNT_KEY)
        val blockCount = getValueByKey(lines, BLOCK_COUNT_KEY)

        // Create Data Scheme
        val dataScheme = createDataScheme(alternatives, columnKeys)

        // Create blocks
        val blocks = ArrayList<Block>()
        for (i in 1..blockCount) {
            blocks.add(Block(i))
        }

        // Get Block index
        val blockIndex = columnKeys.indexOf(BLOCK_KEY)

        //create situations
        for (i in 1..rowCount) {
            // split entries and remove last entry (empty string)
            val entries = lines[i].split(SEPARATOR).dropLast(1)

            // create list for options
            val options = ArrayList<SituationOption>()

            // check for validity of row
            if (entries.size != columnKeys.size) throw CorruptFileException()

            for (alternative in alternatives) {
                // get all values and create SituationOption
                val values = getValuesForAlternative(alternativeIndices[alternative], entries)
                options.add(SituationOption(alternative, values))
            }

            // create situation and put in right block
            val block = entries[blockIndex].toInt() - 1
            blocks[block].addSituation(options)
        }
        return ProjectData(dataScheme, blocks)
    }

    /**
     * This method returns the type of the importer.
     * @return The type of the importer.
     */
    override fun getFileExtension(): String {
        return EXTENSION
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * Search for all alternatives present in the NGENE file
     *
     * @param rows all the rows of the .ngd file (not only table)
     * @return list of all alternatives
     */
    private fun getAlternatives(rows: List<String>): List<String> {
        // get row containing alternatives
        val row = getRowByKey(ALTERNATIVES_KEY, rows)

        // split alternatives
        val alternatives = row.split("=")[1].split(",")

        // remove whitespace
        val formattedAlternatives = ArrayList<String>()
        for (alternative in alternatives) {
            alternative.drop(1)
            formattedAlternatives.add(alternative.drop(1))
        }
        return formattedAlternatives
    }

    /**
     * Get row by key
     *
     * searches for the row in the list starting with given key
     *
     * @param key
     * @param rows
     * @return first row starting with key, empty string if not contained
     */
    private fun getRowByKey(key: String, rows: List<String>): String {
        for (row in rows) {
            if (row.startsWith(key)) {
                return row
            }
        }
        throw CorruptFileException()
    }

    /**
     * Gets value from file if stored like "key = value"
     *
     * @param rows
     * @return
     */
    private fun getValueByKey(rows: List<String>, key: String): Int {
        val row = getRowByKey(key, rows)
        return row.split("=")[1].drop(1).toInt()
    }

    /**
     * Create data scheme
     *
     * @param options all the different options a situation can have
     * @param keys all the keys of the columns in the ngene table
     * @return [DataScheme]
     */
    private fun createDataScheme(options: List<String>, keys: List<String>): DataScheme {
        val dataSchemeOptions = mutableListOf<DataSchemeOption>()
        for (option in options) {
            val fields = HashSet<String>()
            for (key in keys) {
                if (key.startsWith(option)) {
                    // i'm not sure if separation here is wanted TODO(Check for format)
                    fields.add(key.split(".")[1])
                }
            }
            dataSchemeOptions.add(DataSchemeOption(option, fields))
        }
        return DataScheme(dataSchemeOptions)
    }

    /**
     * Get all the indices of all the columns corresponding to each alternative
     *
     * @param alternatives list of all alternatives
     * @param keys list of all column keys
     * @return map with following structure alternative --> listOf(columnName --> index)
     */
    private fun getAlternativeIndices(
        alternatives: List<String>,
        keys: List<String>
    ): Map<String, List<Map<String, Int>>> {
        val map = LinkedHashMap<String, ArrayList<LinkedHashMap<String, Int>>>()
        for (alternative in alternatives) {
            // get all indices for the alternative
            val indices = ArrayList<LinkedHashMap<String, Int>>()
            for (key in keys) {
                if (key.startsWith(alternative)) {
                    val index = LinkedHashMap<String, Int>()
                    index[key] = keys.indexOf(key)
                    indices.add(index)
                }
            }
            map[alternative] = indices
        }
        return map
    }

    /**
     * Get all values for the given indices
     *
     * @param indices list of all indices and corresponding column names
     * @param entries all entries that should be searched in
     * @return map of value with corresponding name
     */
    private fun getValuesForAlternative(
        indices: List<Map<String, Int>>?,
        entries: List<String>
    ): LinkedHashMap<String, Double> {
        val values = LinkedHashMap<String, Double>()
        if (indices != null) {
            for (index in indices.iterator()) {
                for (entry in index.entries.iterator()) {
                    val key = entry.key.split(".")[1]
                    val value = entries[entry.value].toDouble()
                    values[key] = value
                }
            }
        }
        return values
    }
}
