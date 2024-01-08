package data.io.exporter

import data.project.config.ProjectConfiguration
import data.project.data.IconStorage
import data.project.data.Situation
import data.project.data.SituationOption
import java.awt.Image

class ImageGenerator constructor(
    private var config: ProjectConfiguration?,
    private var iconStorage: IconStorage?
) {
    fun PngGenerator(config: ProjectConfiguration?, iconStorage: IconStorage?): Image? {
        return null
    }

    fun generateSituation(situation: Situation?): Image? {
        return null
    }

    fun generateOption(option: SituationOption?): Image? {
        return null
    }
}
