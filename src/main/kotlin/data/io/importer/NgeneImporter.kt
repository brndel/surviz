package data.io.importer

import data.project.ProjectData
import java.io.File

/**
 * This class implements the [Importer] interface and imports the project from an NGene file.
 */
class NgeneImporter : Importer {

    private val extension = "ngd"

    /**
     * This method imports a file and returns the project data.
     * @param file The file to import.
     * @return The project data.
     */
    override fun importFile(file: File): ProjectData {
        TODO("Not yet implemented")
    }

    /**
     * This method returns the type of the importer.
     * @return The type of the importer.
     */
    override fun getType(): String {
        return extension
    }
}
