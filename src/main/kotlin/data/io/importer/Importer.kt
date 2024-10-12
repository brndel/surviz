package data.io.importer

import data.project.ProjectData
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
    fun importFile(file: File): ProjectData
}