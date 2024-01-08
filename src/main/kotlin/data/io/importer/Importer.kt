package data.io.importer

import data.project.ProjectData
import java.io.File

interface Importer {
    fun importFile(file: File): ProjectData
    fun getType(): String
}