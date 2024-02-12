package data.project

import data.io.importer.NgeneImporter
import kotlinx.coroutines.awaitAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class ProjectTest {

    @Test
    fun saveProjectData() {
        val ngenePath = "src/test/resources/Sample.ngd"
        val project = Project.newProjectWithData(NgeneImporter.importFile(File(ngenePath)))
        project.saveProjectData("src/test/resources/")
    }

    @Test
    fun loadProjectData() {
        val file = File("test.svd")
      //  val project = Project.loadProjectFromFile(file)
    }
}