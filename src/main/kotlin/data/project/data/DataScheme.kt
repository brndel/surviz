package data.project.data

/**
 * This class represents a data scheme.
 * A data scheme of a project describes the structure of a nGene file.
 * It is used to safe current project data and apply it to new loaded nGene file.
 *
 * @property options The list of data scheme options.
 */
class DataScheme(
    val options: MutableList<DataSchemeOption>
) {
    /**
     * This method compares two data schemes for compatibility.
     * Method compares given data scheme with current project data scheme.
     * If the data schemes are compatible the method returns true, and the data scheme will be applied to the nGene file.
     *
     * @param scheme the data scheme to compare
     * @return true if the data schemes are equal, false otherwise
     *
     */
    fun compareTo(scheme: DataScheme): Boolean {
        val copyOfOptions = mutableListOf<DataSchemeOption>()
        copyOfOptions.addAll(options)

        if (copyOfOptions.size != scheme.options.size) {
            return false
        }
        for (option in copyOfOptions) {
            for (option2 in scheme.options) {
                if (option.equals(option2)) {
                    copyOfOptions.remove(option2)
                }
            }
        }
        return copyOfOptions.isEmpty()
    }
}