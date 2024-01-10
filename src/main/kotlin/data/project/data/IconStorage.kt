package data.project.data

/**
 * This class represents  icon storage.
 * To ensure flexibility all icons stored in the icon storage.
 * So when accessed on different devices the icons are available.
 * The icons are stored in the project data and can be accessed with getIcon.
 *
 * @property icons The icons of the project
 */
class IconStorage(
    var icons: Map<String, String>
) {
    /**
     * This method stores an icon.
     * @param filePath the file path
     */
    fun storeIcon(filePath: String) {

    }

    /**
     * This method returns an icons string by a given icon path.
     * @param filePath the icon path
     * @return the icon as a string
     */
    fun getIcon(filePath: String): String {
        return ""
    }

}