package data.io.exporter

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import data.io.exporter.resources.TextType
import data.project.config.ProjectConfiguration
import data.project.data.IconStorage
import data.project.data.Situation
import data.project.data.SituationOption
import java.awt.Image
import java.io.FileInputStream
import java.lang.Exception
import java.util.Locale
import java.util.Properties

/**
 * This class describes the image generator.
 * @param config The project configuration.
 * @param iconStorage The icon storage.
 */
class ImageGenerator constructor(
    private var config: ProjectConfiguration,
    private var iconStorage: IconStorage
) {

    private val properties: Properties = Properties()

    init {
        properties.load(FileInputStream("src/main/resources/config/image_generator.properties"))
    }

    ///////////////////////////////////////////////////////////////////////////////
    // public functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * This method generates a PNG image for the given project configuration.
     * @param config The project configuration.
     * @param iconStorage The icon storage.
     * @return The generated image.
     */
    fun generatePng(config: ProjectConfiguration, iconStorage: IconStorage?): Image? {

        // warum übergeben wir hier config und icon storage nochmal?
        return null
    }

    /**
     * This method generates an Image for the given situation.
     * @param situation The situation.
     * @return The generated image.
     */
    fun generateSituation(situation: Situation): ImageBitmap {
        val optionsCount = situation.options.size

        val width = properties.getProperty("situation_width").toInt()
        val height = properties.getProperty("situation_height").toInt() * optionsCount

        val image = ImageBitmap(width, height)
        val canvas = Canvas(image)

        for (i in 0..<optionsCount) {
            val option = situation.options[i]
            val optionImageBitmap = generateOption(option)
            canvas.drawImage(
                optionImageBitmap,
                Offset(0F, i * properties.getProperty("situation_height").toFloat()),
                // muss man hier nochmal color festlegen?
                Paint()
            )
        }
        return image
    }

    /**
     * This method generates an Image for the given situation option.
     * @param option The situation option.
     * @return The generated image.
     */
    fun generateOption(option: SituationOption): ImageBitmap {
        // da könnte man maybe ne custom exception throwen
        val situationConfig = config.getSituationConfig()[option.name] ?: throw NoSuchFieldException()

        val width = properties.getProperty("situation_width").toInt()
        val height = properties.getProperty("situation_height").toInt()
        val padding = properties.getProperty("padding").toInt()

        val image = ImageBitmap(width, height)
        val canvas = Canvas(image)

        //fill background
        val backgroundColor = Paint()
        backgroundColor.color = Color(properties.getValue("background_color").toString().toLong(16))
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), backgroundColor)

        //draw option title
        drawText(
            canvas,
            situationConfig.name.toString(),
            Color.Black,
            Offset(
                properties.getProperty("option_title_x_offset").toFloat() + padding,
                properties.getProperty("option_title_y_offset").toFloat() + padding
            ),
            textType = TextType.Title
        )
        return image
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

    @OptIn(ExperimentalTextApi::class)
    private fun drawText(
        canvas: Canvas,
        text: String,
        color: Color,
        position: Offset,
        textType: TextType = TextType.Label,
        width: Int? = null
    ) {
        val style = TextStyle(
            color = color,
            fontSize = textType.fontSize,
            fontWeight = textType.fontWeight,
            textAlign = if (width != null) TextAlign.Center else null
        )

        val measurer = TextMeasurer(createFontFamilyResolver(), Density(1.0F), LayoutDirection.Ltr)

        val hello = measurer.measure(
            text,
            style,
            constraints = Constraints(
                minWidth = width ?: 0,
                maxWidth = width ?: Constraints.Infinity
            )
        )

        canvas.save()
        canvas.translate(position.x, position.y)
        TextPainter.paint(canvas, hello)
        canvas.restore()
    }

}
