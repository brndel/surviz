package data.project

import data.io.importer.NgeneImporter
import kotlinx.coroutines.awaitAll
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class ProjectTest {

    @Test
    fun saveProjectData() {

        val ngenePath = "C:\\Users\\Alex\\Desktop\\ngene.ngd"
        val project = Project.newProjectWithData(NgeneImporter.importFile(File(ngenePath)))
        project.iconStorage.storeIcon("C:\\Users\\Alex\\Desktop\\test.png", true)
        project.iconStorage.storeIcon("C:\\Users\\Alex\\Desktop\\test.png", true)
        project.saveProjectData("C:\\Users\\Alex\\Desktop\\")
        project.saveProjectData("")

        Thread.sleep(200)


    }

    @Test
    fun loadProjectData() {
        val file = File("test.svd")
        val project = Project.loadProjectFromFile(file)
    }
}