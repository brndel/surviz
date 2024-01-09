package data.io.importer
/**
 * This enum describes the different types of importers.
 */
enum class ImporterVariant {
    Ngene;
    /**
     * This method returns the importer for the given type.
     */
    val importer: Importer?
        get() = null
}
