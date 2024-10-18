package data.io.importer

import data.project.ProjectData
import data.resources.exceptions.CorruptFileException
import java.io.File

/**
 * This interface describes the necessary methods for an importer.
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