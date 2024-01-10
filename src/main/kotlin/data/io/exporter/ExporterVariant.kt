package data.io.exporter

/**
 * This enum describes the different types of exporters.
 */
enum class ExporterVariant(private val exporter: Exporter) {
    /**
     * The exporter variant for [PngExporter]
     */
    Png(PngExporter()),

    /**
     * The exporter variant for [HtmlExporter]
     *
     */
    Html(HtmlExporter());

    /**
     * This method returns the exporter for the given type.
     * @return the exporter of this exporter variant
     */
    fun getExporter(): Exporter {
        return exporter
    }
}
