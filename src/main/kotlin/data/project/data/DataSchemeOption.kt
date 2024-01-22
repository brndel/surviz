package data.project.data

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
    var name: String,
    var fields: List<String>
) {
    @Override
    fun equals(other: DataSchemeOption): Boolean {
        if (this.name != other.name) {
            return false
        }
        return fields.size == other.fields.size
    }
}





