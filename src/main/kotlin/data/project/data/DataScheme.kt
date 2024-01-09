package data.project.data

/**
 * This class represents a data scheme.
 * A data scheme of a project describes the structure of a nGene file.
 * It is used to safe current project data and apply it to new loaded nGene file.
 *
 * @param options The list of data scheme options.
 *
 * @constructor Creates a data scheme.
 *
 */
class DataScheme {

    lateinit var options: MutableList<DataSchemeOption>

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
        return false
    }
}