package data.project.data

import java.util.SortedMap

/**
 * This class represents a data scheme.
 * A data scheme of a project describes the structure of a nGene file.
 * It is used to safe current project data and apply it to new loaded nGene file.
 *
 * @property options The list of data scheme options.
 */
class DataScheme(options: List<DataSchemeOption>) {
    val options: SortedMap<String, DataSchemeOption> = options.associateBy { it.name }.toSortedMap()

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
        for (name in options.keys) {
            if (this.options[name] != scheme.options[name]) return false
        }
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataScheme

        return options == other.options
    }

    override fun hashCode(): Int {
        return options.hashCode()
    }


}