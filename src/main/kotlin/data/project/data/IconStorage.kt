package data.project.data

/**
 * This class represents  icon storage.
 * To ensure flexibility all icons stored in the icon storage.
 * So when accessed on different devices the icons are available.
 * The icons are stored in the project date and can be accessed by the file path which can be accessed by the map.
 *
 * @param icons The icons of the project.
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
     * This method returns an icons path by a given icon identifier.
     * @param filePath the icon name
     * @return the path of the icon
     */
    fun getIcon(filePath: String): String {
        return ""
    }

}