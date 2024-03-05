package data.io

import data.io.exporter.ExporterVariant
import data.io.utils.result.ExportResult
import data.io.importer.Importer
import data.io.importer.ImporterVariant
import data.project.Project
import data.project.ProjectData
import data.resources.exceptions.FileTypeException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.nio.file.Path

/**
 * This class represents the data manager.
 */
object DataManager {

    ///////////////////////////////////////////////////////////////////////////////
    // public functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * This method loads the data from the given file.
     * @param file The file to load the data from.
     * @return The loaded data as [ProjectData].
     *
     * @throws FileTypeException if no right importer was found
     */
    fun loadData(file: File): ProjectData {
        // Choose right Importer
        val importer = getImporterByExtension(file.extension)

        // import file and return data if importer found, else throw exception
        return importer?.importFile(file) ?: throw FileTypeException(file.extension)
    }

    /**
     * This method saves the given data to the given file.
     * @param project The project to save.
     * @param exporter The exporter to use.
     * @param exportConfig The export configuration.
     * @param onPathSelected gets called if a path is selected in exporting process
     * @param onFinished getsCalled when finished with exporting
     */
    fun saveData(
        project: Project,
        exporter: ExporterVariant,
        exportConfig: Map<String, Any>,
        onPathSelected: (Path) -> Unit,
        onFinished: (ExportResult) -> Unit,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result =  exporter.getExporter().export(project, exportConfig, onPathSelected)

            onFinished(result)
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * Get importer by extension
     *
     * Selects the right importer based on given file extension
     *
     * @param extension file extension that the importer is needed for
     * @return right importer if found, null if no right importer is present
     */
    private fun getImporterByExtension(extension: String): Importer? {
        return ImporterVariant.entries.find { it.getImporter().getFileExtension() == extension }
            ?.getImporter()
    }
}