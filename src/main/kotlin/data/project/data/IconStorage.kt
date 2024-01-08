package data.project.data

/**
 * This class represents  icon storage.
 * To ensure flexibility all icons stored in the icon storage.
 * So when accessed on different devices the icons are available.
 * The icons are stored in the project date and can be accessed by the file path which can be accessed by the map.
 *

 */
class IconStorage(var  icons: Map<String, String>) {
    /**
     * This method stores an icon.
     * @param filePath the file path
     */
    fun storeIcon(filePath: String) {

    }

    /**
     * This method returns an icon.
     * @param filePath the file path
     */
    fun getIcon(filePath: String): String {
        return ""
    }

}