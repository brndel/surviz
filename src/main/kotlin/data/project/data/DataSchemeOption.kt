package data.project.data

import java.util.SortedSet

/**
 * This class represents a data scheme option.
 * A data scheme option is a set of fields and a name.
 * If a data scheme is fits to a nGene file, the project data will be applied to the new loaded nGene file.
 * Else the data scheme option will be ignored.
 *
 * @property name The name of the data scheme option
 * @property fields The fields of the data scheme option
 */
class DataSchemeOption(
    val name: String,
    fields: Set<String>
) {
    private val fields: SortedSet<String> = fields.toSortedSet()
    val fieldsList: List<String>
        get() = fields.toList()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DataSchemeOption

        if (name != other.name) return false
        if (fields != other.fields) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + fields.hashCode()
        return result
    }


}