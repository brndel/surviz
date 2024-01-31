package data.project

import data.io.importer.NgeneImporter
import data.project.data.DataScheme
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

class ProjectTest {

    @Test
    fun saveProjectData() {
        // TODO(add ngene to test resources)
        val ngenePath = "C:\\Users\\Alex\\Desktop\\ngene.ngd"
        val project = Project.newProjectWithData(NgeneImporter.importFile(File(ngenePath)))
        // TODO(change output path to resources or smth like that?!)
        project.saveProjectData("C:\\Users\\Alex\\Desktop\\")
    }
}