package data.project.data

/**
 * This class represents a data scheme option.
 * A data scheme option is a set of fields and a name.
 * If a data scheme is fits to a nGene file, the data scheme option will be applied to the nGene file.
 * Else the data scheme option will be ignored.
 *
 */
class DataSchemeOption constructor(

    var name: String,
    var fields: List<String>
)





