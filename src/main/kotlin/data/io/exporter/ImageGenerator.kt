package data.io.exporter

import data.project.config.ProjectConfiguration
import data.project.data.IconStorage
import data.project.data.Situation
import data.project.data.SituationOption
import java.awt.Image

/**
 * This class describes the image generator.
 * @param config The project configuration.
 * @param iconStorage The icon storage.
 */
class ImageGenerator constructor(
    private var config: ProjectConfiguration?,
    private var iconStorage: IconStorage?
) {
    /**
     * This method generates a PNG image for the given project configuration.
     * @param config The project configuration.
     * @param iconStorage The icon storage.
     * @return The generated image.
     */
    fun PngGenerator(config: ProjectConfiguration?, iconStorage: IconStorage?): Image? {
        return null
    }

    /**
     * This method generates an Image for the given situation.
     * @param situation The situation.
     * @return The generated image.
     */
    fun generateSituation(situation: Situation?): Image? {
        return null
    }
    /**
     * This method generates an Image for the given situation option.
     * @param option The situation option.
     * @return The generated image.
     */
    fun generateOption(option: SituationOption?): Image? {
        return null
    }
}
