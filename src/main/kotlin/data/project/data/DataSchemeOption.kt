package data.project.data

/**
 * This class represents a data scheme option.
 * A data scheme option is a set of fields and a name.
 * If a data scheme is fits to a nGene file, the project data will be applied to the new loaded nGene file.
 * Else the data scheme option will be ignored.
 *
 * @param name The name of the data scheme option.
 * @param fields The fields of the data scheme option.
 */
class DataSchemeOption (
    /**
     * The name of the data scheme option.
     */
    var name: String,
    /**
     * The fields of the data scheme option.
     */
    var fields: List<String>
)





