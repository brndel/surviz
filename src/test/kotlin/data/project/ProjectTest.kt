package data.project

import data.io.importer.NgeneImporter
import data.project.data.DataScheme
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class ProjectTest {

    @Test
    fun saveProjectData() {
        val ngenePath = "C:\\Users\\Alex\\Desktop\\ngene.ngd"
        val project = Project.newProjectWithData(NgeneImporter.importFile(File(ngenePath)))
        project.saveProjectData("C:\\Users\\Alex\\Desktop\\")
    }
}