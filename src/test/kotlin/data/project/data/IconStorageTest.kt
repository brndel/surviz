package data.project.data

import androidx.compose.ui.graphics.ImageBitmap
import data.io.importer.Importer
import data.io.importer.NgeneImporter
import data.project.Project
import data.resources.exceptions.FileTypeException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.lang.Thread.sleep
import java.nio.file.Path

class IconStorageTest {


    @Test
    fun storeInitialIcons() {

        val iconStorage = IconStorage()
        assert(iconStorage.getInternalIconNames().isNotEmpty())
    }

    val iconStorage = IconStorage()


    @Test
    fun storeIconSvg() {
        val imagePath = "src/main/resources/icons/15_car.svg"
        iconStorage.storeIcon(imagePath)
        assert(iconStorage.getUserIconNames().isNotEmpty())

        val key = iconStorage.getUserIconNames().first()
        assert(iconStorage.getIcon(key) != null)


    }
    @Test
    fun storeSmallSvg() {
        val imagePath = "src/test/resources/images/duck.svg"
        iconStorage.storeIcon(imagePath)
        assert(iconStorage.getUserIconNames().isNotEmpty())

        val key = iconStorage.getUserIconNames().first()
        assert(iconStorage.getIcon(key) != null)
    }

    @Test
    fun storeIconPng() {
        val imagePath = "src/main/resources/icons/autonomous_shuttle_on_demand.png"
        iconStorage.storeIcon(imagePath)
        assert(iconStorage.getUserIconNames().isNotEmpty())

        val key = iconStorage.getUserIconNames().first()
        assert(iconStorage.getIcon(key) != null)
    }
    @Test
    fun storeUnsupportedIcon() {
        val imagePath = "src/test/resources/data/travel_time.wrong"
        assertThrows(FileTypeException::class.java) {
            iconStorage.storeIcon(imagePath)

        }
    }

    @Test
    fun getIcon() {
        val imagePath = "src/main/resources/icons/autonomous_shuttle_on_demand.png"
        iconStorage.storeIcon(imagePath)
        val key = iconStorage.getUserIconNames().first()
        val map = iconStorage.getIcon(key)
        assert(map != null)

        assert(map is ImageBitmap)
    }

    @Test
    fun getIconEmpty() {
        assert(iconStorage.getIcon("test") == null)

    }

    @Test
    fun getInternalIconNames() {
        val imagePath = "src/main/resources/icons/autonomous_shuttle_on_demand.png"
        iconStorage.storeIcon(imagePath)
        val iconNames = iconStorage.getInternalIconNames()
        assert(iconNames.isNotEmpty())


    }

    @Test
    fun getInternalIconNamesEmpty() {
        val iconNames = iconStorage.getInternalIconNames()
        assert(iconNames.isNotEmpty())
    }


    @Test
    fun serializeIcon() {
        val ngenePath = "src/test/resources/data/Sample.ngd"
        val project = Project.newProjectWithData(NgeneImporter.importFile(File(ngenePath)))
        project.iconStorage.storeIcon("src/test/resources/images/duck.svg")
        project.saveProjectData("src/test/resources/data/test.svz" )
        val file = File("src/test/resources/data/test.svz")
        assert(file.exists())
        sleep(100)
        file.delete()
    }
    @Test
    fun  deserializeIcon(){
        val ngenePath = "src/test/resources/data/Sample.ngd"
        val project = Project.newProjectWithData(NgeneImporter.importFile(File(ngenePath)))
        project.iconStorage.storeIcon("src/test/resources/images/duck.svg")
        project.saveProjectData("src/test/resources/data/test.svz" )
        val file = File("src/test/resources/data/test.svz")
        val project2 = Project.loadProjectFromFile(file)
        assert(project2 != null)
        sleep(100)
        file.delete()
    }


}

