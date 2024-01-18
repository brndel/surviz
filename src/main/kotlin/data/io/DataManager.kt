package data.io

import data.io.exporter.ExporterVariant
import data.io.importer.Importer
import data.io.importer.ImporterVariant
import data.project.Project
import data.project.ProjectData
import java.io.File

/**
 * This class represents the data manager.
 */
object DataManager {

    /**
     * This method loads the data from the given file.
     * @param file The file to load the data from.
     * @return The loaded data as [ProjectData].
     *
     * @throws NullPointerException if no right importer was found
     */
    fun loadData(file: File): ProjectData {
        // function probably should expect file path and convert here to File

        // Choose right Importer
        val importer = getImporterByExtension(file.extension)

        // import file and return data if importer found, else return null
        return importer?.importFile(file) ?: null!!
    }

    /**
     * This method saves the given data to the given file.
     * @param project The project to save.
     * @param exporter The exporter to use.
     * @param exportConfig The export configuration.
     */
    fun saveData(project: Project, exporter: ExporterVariant, exportConfig: Map<String, Any>) {
        exporter.getExporter().export(project, exportConfig)
    }

    /**
     * Get importer by extension
     *
     * Selects the right importer based on given file extension
     *
     * @param extension file extension that the importer is needed for
     * @return right importer if found, null if no right importer is present
     */
    private fun getImporterByExtension(extension: String): Importer? {
        for (importer in getImporterList()) {
            if (importer.getType() == extension) {
                return importer
            }
        }
        return null
    }

    /**
     * Get a List of all Importers
     *
     * @return list of all importers
     */
    private fun getImporterList(): ArrayList<Importer> {
        val importers = ArrayList<Importer>()
        val importerArray = ImporterVariant.values()

        for (importer in importerArray) {
            importers.add(importer.getImporter())
        }
        return importers
    }
}