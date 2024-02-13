package data.project

import data.io.importer.NgeneImporter
import data.project.data.DataScheme
import kotlinx.coroutines.awaitAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class ProjectTest {
    val ngenePath = "src/test/resources/data/Sample.ngd"
    val data = NgeneImporter.importFile(File(ngenePath))
    val project = Project.newProjectWithData(data)


    @Test
    fun saveProjectData() {
        project.saveProjectData("src/test/resources/data/projectSafeTest.svz")

    }

    @Test
    fun loadProjectDataWithForce() {
        assertTrue(project.loadProjectData(data, true))
    }

    @Test
    fun loadProjectDataWithoutForce() {
        assertTrue(project.loadProjectData(data, false))
    }

}