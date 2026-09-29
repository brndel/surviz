package data.io.importer

import data.project.ProjectData
import java.io.File
import kotlin.math.max

/**
 * Importer for CSV files produced for SurViz data tables.
 *
 * Assumptions:
 * - semicolon (";") is used as separator
 * - first row contains column keys
 * - numeric values may use comma as decimal separator
 *
 * The TabularImporter contract is implemented to allow the generic importer pipeline
 * to parse rows and map them to the internal ProjectData structure.
 */
object CsvImporter : TabularImporter {

    override val extensions: List<String>
        get() = mutableListOf("csv")
    override val separator: String
        get() = ";"
    override val blockKey: String
        get() = "Block"

    override fun getAlternatives(lines: List<String>): List<String> {
        return lines[0].split(separator)
            .mapNotNull { key -> key.takeIf { it.contains(".") }?.split(".")?.first() }
            .distinct()
    }

    override fun getRowCount(lines: List<String>): Int {
        return lines.size - 1
    }

    override fun getBlockCount(lines: List<String>): Int {
        val blockIndex = lines[0].split(separator).indexOf(blockKey)
        return if (blockIndex == -1) {
            1
        } else {
            return lines.maxOf { it.split(separator)[blockIndex].toIntOrNull()?: 1 }
        }
    }

    override fun splitLine(line: String): List<String> {
        return line.split(separator)
    }

    override fun isValidLine(entries: List<String>, columnKeys: List<String>): Boolean {
        return true
    }

    override fun valueFromString(value: String): Double {
        return value.replace(",", ".").toDouble()
    }
}