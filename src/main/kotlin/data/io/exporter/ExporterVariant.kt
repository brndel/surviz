package data.io.exporter

/**
 * This enum describes the different types of exporters.
 */
enum class ExporterVariant {
    Png, Html;

    /**
     * This method returns the exporter for the given type.
     */
    val exporter: ExporterVariant?
        get() = null
}
