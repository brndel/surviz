package data.io.importer

import data.project.ProjectData
import data.project.data.Block
import data.project.data.DataScheme
import data.project.data.DataSchemeOption
import data.project.data.SituationOption
import java.io.File

interface TabularImporter : Importer {

    val separator: String
    val blockKey: String
    override fun importFile(file: File): ProjectData {
        val lines = file.readLines(charset = Charsets.UTF_8)

        val columnKeys = ArrayList<String>()
        for (key in lines[0].split(separator)) {
            if (key.isNotEmpty()) {
                columnKeys.add(key)
            }
        }

        // alternatives
        val alternatives = getAlternatives(lines)
        val alternativeIndices = getAlternativeIndices(alternatives, columnKeys)

        val dataScheme = createDataScheme(alternatives, columnKeys)

        val rowCount = getRowCount(lines)
        val blockCount = getBlockCount(lines)

        val blocks = List(blockCount) { Block(it + 1) }

        val blockIndex = columnKeys.indexOf(blockKey)

        for (i in 1..rowCount) {
            val entries = splitLine(lines[i])

            val options = ArrayList<SituationOption>()

            checkValidity(entries, columnKeys)

            for (alternative in alternatives) {
                val values = getValuesForAlternative(alternativeIndices[alternative], entries)
                options.add(SituationOption(alternative, values))
            }

            val block = if (blockIndex == -1) 0 else entries[blockIndex].toInt() - 1
            blocks[block].addSituation(options)
        }
        return ProjectData(dataScheme, blocks)
    }

    fun getAlternatives(lines: List<String>): List<String>

    fun getRowCount(lines: List<String>): Int

    fun getBlockCount(lines: List<String>): Int

    fun splitLine(line: String): List<String>

    fun checkValidity(entries: List<String>, columnKeys: List<String>)

    fun valueFromString(value: String): Double

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
                if (key.startsWith(alternative.lowercase())) {
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
                if (key.startsWith(option.lowercase())) {
                    fields.add(key.split(".")[1])
                }
            }
            dataSchemeOptions.add(DataSchemeOption(option, fields))
        }
        return DataScheme(dataSchemeOptions)
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
                    val value = valueFromString(entries[entry.value])
                    values[key] = value
                }
            }
        }
        return values
    }
}