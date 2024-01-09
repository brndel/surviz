package data.io

import data.io.exporter.ExporterVariant
import data.project.Project
import data.project.ProjectData
import java.io.File

/**
 * This class represents the data manager.
 */
class DataManager {
    /**
     * This method loads the data from the given file.
     * @param file The file to load the data from.
     * @return The loaded data as [ProjectData].
     */
    fun loadData(file: File): ProjectData {
        return null!!
    }

    /**
     * This method saves the given data to the given file.
     * @param project The project to save.
     * @param exporter The exporter to use.
     * @param exportConfig The export configuration.
     */
    fun saveData(project: Project, exporter: ExporterVariant, exportConfig: Map<String, Any>) {

    }
}