package data.io.importer

import data.project.ProjectData
import java.io.File

/**
 * This interface describes the necessary methods for an importer.
 */
interface Importer {
    /**
     * This method imports the given file and returns the project data.
     * @param file The file to import.
     */
    fun importFile(file: File): ProjectData

    /**
     * This method returns the type of the importer.
     *
     * Please return extension without "."
     * @see NgeneImporter
     */
    fun getType(): String
}