package data.io.importer

import data.project.ProjectData
import data.resources.exceptions.CorruptFileException
import java.io.File

/**
 * Contract for file importers that produce ProjectData.
 *
 * Implementations should declare supported file extensions and provide a robust
 * readFile implementation. importFile wraps readFile and converts unexpected
 * exceptions into CorruptFileException to keep error handling consistent.
 */
interface Importer {
    val extensions : List<String>

    /**
     * This method imports the given file and returns the project data.
     * @param file The file to import.
     */
    fun importFile(file: File): ProjectData {
        return try {
            readFile(file)
        } catch (e: Exception) {
            throw CorruptFileException()
        }
    }

    fun readFile(file: File): ProjectData
}