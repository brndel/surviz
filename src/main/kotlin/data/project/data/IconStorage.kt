package data.project.data

import java.nio.file.Files
import java.nio.file.Path
import java.util.Base64

/**
 * This class represents  icon storage.
 * To ensure flexibility all icons stored in the icon storage.
 * So when accessed on different devices the icons are available.
 * The icons are stored in the project data and can be accessed with getIcon.
 *
 * @property icons The icons of the project
 */
class IconStorage(

) {
    private val icons: LinkedHashMap<String, String> = LinkedHashMap()

    /**
     * This method stores an icon.
     * @param filePath the file path
     */
    fun storeIcon(filePath: String) {
        val encodedImage = encodeIconBase64(filePath)
        icons[filePath] = encodedImage


    }

    private fun encodeIconBase64(imagePath: String): String {
        val imageBytes = Files.readAllBytes(Path.of(imagePath))
        return Base64.getEncoder().encodeToString(imageBytes)
    }

    /**
     * This method returns an icons string by a given icon path.
     * @param filePath the icon path
     * @return the icon as a string
     */
    fun getIcon(filePath: String): String {
        return icons[filePath]!!
    }

}