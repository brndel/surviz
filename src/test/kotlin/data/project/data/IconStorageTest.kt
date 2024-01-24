package data.project.data

import androidx.compose.ui.graphics.Path
import org.junit.jupiter.api.Assertions.*
import java.nio.file.Paths

/**
 * Unfertige Testklasse für die Klasse IconStorage
 */
class IconStorageTest

fun main() {
    loadIcon()
}

fun loadIcon()  {
    val iconStorage = IconStorage()
    val path = "C:\\Users\\Alex\\Desktop\\test.png"


    iconStorage.storeIcon(path)


}

