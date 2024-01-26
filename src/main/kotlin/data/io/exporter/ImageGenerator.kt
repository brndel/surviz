package data.io.exporter

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
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
import java.io.FileInputStream
import java.util.Properties

/**
 * This class describes the image generator.
 * @param config The project configuration.
 * @param iconStorage The icon storage.
 */
class ImageGenerator(
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
     * This method generates an Image for the given situation.
     * @param situation The situation.
     * @return The generated image.
     *
     * @throws NoSuchFieldException if no configuration was found for on of the options
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
     *
     * @throws NoSuchFieldException if no configuration was found for the option
     */
    fun generateOption(option: SituationOption): ImageBitmap {
        // initialize values
        val situationConfig =
            // da könnte man maybe ne custom exception throwen
            config.getSituationConfig()[option.name] ?: throw NoSuchFieldException()

        val width = properties.getProperty("situation_width").toInt()
        val height = properties.getProperty("situation_height").toInt()
        val padding = properties.getProperty("border_padding").toInt()

        val optionColor = situationConfig.color.value

        val image = ImageBitmap(width, height)
        val canvas = Canvas(image)

        //fill background
        val backgroundColor = Paint()
        backgroundColor.color = Color(properties.getProperty("background_color").toLong(16))
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), backgroundColor)

        //draw option title
        drawText(
            canvas,
            situationConfig.name.toString(),
            optionColor,
            Offset(
                properties.getProperty("option_title_x_offset").toFloat() + padding,
                properties.getProperty("option_title_y_offset").toFloat() + padding
            ),
            textType = TextType.Title
        )

        // draw single values
        drawSingleValues(canvas, option.name)

        // draw divider line
        val dividerX = padding + properties.getProperty("max_single_values")
            .toFloat() * properties.getProperty("single_value_size")
            .toFloat() + properties.getProperty(
            "column_padding"
        ).toFloat()

        val linePaint = Paint().apply {
            style = PaintingStyle.Stroke
            strokeWidth = properties.getProperty("divider_weight").toFloat()
            color = Color(properties.getProperty("divider_color").toLong(16))
        }

        canvas.drawLine(
            Offset(dividerX, padding.toFloat()),
            Offset(dividerX, (height - padding).toFloat()),
            linePaint
        )

        // draw timeline
        drawTimeline(canvas)
        return image
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * Draws text on a [canvas]
     *
     * @param canvas base canvas the text should be drawn on
     * @param text the text to draw on canvas
     * @param color color of the text
     * @param position top-left corner of the text
     * @param textType type of text, determines text size
     * @param width
     */
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

    private fun drawSingleValues(canvas: Canvas, optionKey: String) {
        TODO()
    }

    private fun drawTimeline(canvas: Canvas) {
        TODO()
    }
}
