package data.io.utils.result.report.generator

/**
 * Report generator variant containing all [ReportGenerator]s that should be used to generate a report that will be shown to the user.
 *
 * @property generator
 * @constructor Create report generator variant with given [ReportGenerator]
 */
enum class ReportGeneratorVariant(private val generator: ReportGenerator) {

    ImageSize(ImageSizeReportGenerator),

    InvalidSituation(InvalidSituationReportGenerator),

    InvalidBlock(InvalidBlockReportGenerator);

    fun getGenerator(): ReportGenerator {
        return generator
    }
}