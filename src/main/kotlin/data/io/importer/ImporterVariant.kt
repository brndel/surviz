package data.io.importer

/**
 * This enum describes the different types of importers.
 * @param importer The importer of this variant.
 */
enum class ImporterVariant(private val importer: Importer) {
    /**
     * The importer variant for [NgeneImporter]
     *
     */
    Ngene(NgeneImporter);

    /**
     * This method returns the importer for the given type.
     * @return The importer of this variant
     */
    fun getImporter(): Importer {
        return importer
    }
}
