package data.io.exporter

import androidx.compose.ui.graphics.toAwtImage
import data.generator.ImageGenerator
import data.project.config.ProjectConfiguration
import data.project.data.IconStorage
import data.project.data.SituationOption
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.io.File
import javax.imageio.ImageIO

class ImageGeneratorTest {

    @Test
    fun generatePng() {
        val generator = ImageGenerator(ProjectConfiguration(), IconStorage())
        generator.generatePng(ProjectConfiguration(), IconStorage())
    }

    @Test
    fun generateOptionTest() {
        val option = SituationOption(
            "fuss",
            hashMapOf("fz_fuss" to 10.0)
        )
        val generator = ImageGenerator(ProjectConfiguration(), IconStorage())
        val image = generator.generateOption(option)
        val bufferedImage = image.toAwtImage()
        val outputFile = File("src/test/resources/image/test_image.png")
        ImageIO.write(bufferedImage, "png", outputFile)
        println(outputFile.absolutePath)
    }
}