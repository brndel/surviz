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
        //project.iconStorage.storeIcon("C:\\Users\\Alex\\Desktop\\test.png", true)
        //project.iconStorage.storeIcon("C:\\Users\\Alex\\Desktop\\test.png", true)
        project.saveProjectData("src/test/resources/")
        //project.saveProjectData("")
    }

    @Test
    fun loadProjectData() {
        val file = File("test.svd")
        val project = Project.loadProjectFromFile(file)
    }
}