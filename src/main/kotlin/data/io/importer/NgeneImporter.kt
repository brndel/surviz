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
object NgeneImporter : TabularImporter {
    ////////////////////////////////////////////////////////////////////////////////
    // constants
    ///////////////////////////////////////////////////////////////////////////////
    private const val EXTENSION = "ngd"
    private const val SEPARATOR = "\t"
    private const val ALTERNATIVES_KEY = ";alts"
    private const val ROW_COUNT_KEY = ";rows"
    private const val BLOCK_COUNT_KEY = ";block"
    private const val BLOCK_KEY = "Block"
    override val extensions: List<String>
        get() = mutableListOf(EXTENSION)
    override val separator: String
        get() = SEPARATOR
    override val blockKey: String
        get() = BLOCK_KEY

    ///////////////////////////////////////////////////////////////////////////////
    // public functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * Search for all alternatives present in the NGENE file
     *
     * @param lines all the rows of the .ngd file (not only table)
     * @return list of all alternatives
     */
    override fun getAlternatives(lines: List<String>): List<String> {
        // get row containing alternatives
        val row = getRowByKey(ALTERNATIVES_KEY, lines)

        // split alternatives
        val alternatives = row.split("=")[1].split(",")

        // remove whitespace
        return alternatives.map { it.replace("\\s".toRegex(), "") }
    }

    override fun getRowCount(lines: List<String>): Int {
        return getValueByKey(lines, ROW_COUNT_KEY)
    }

    override fun getBlockCount(lines: List<String>): Int {
        return getValueByKey(lines, BLOCK_COUNT_KEY)
    }

    override fun splitLine(line: String): List<String> {
        return line.split(SEPARATOR).dropLast(1)
    }

    override fun isValidLine(entries: List<String>, columnKeys: List<String>): Boolean {
        return (entries.size == columnKeys.size)
    }

    override fun valueFromString(value: String): Double {
        return value.toDouble()
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

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
}
