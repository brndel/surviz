package data.io.exporter

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
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
import data.project.config.LineType
import data.project.config.ProjectConfiguration
import data.project.config.SituationConfig
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
        val optionConfig =
            // da könnte man maybe ne custom exception throwen
            config.getSituationConfig()[option.name] ?: throw NoSuchFieldException()

        val width = properties.getProperty("situation_width").toInt()
        val height = properties.getProperty("situation_height").toInt()
        val padding = properties.getProperty("border_padding").toInt()

        val color = optionConfig.color.value

        val image = ImageBitmap(width, height)
        val canvas = Canvas(image)

        //fill background
        val backgroundColor = Paint()
        backgroundColor.color = Color(properties.getProperty("background_color").toLong(16))
        canvas.drawRect(0F, 0F, width.toFloat(), height.toFloat(), backgroundColor)

        //draw option title
        drawText(
            canvas,
            optionConfig.name.toString(),
            color,
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
            this.color = Color(properties.getProperty("divider_color").toLong(16))
        }

        canvas.drawLine(
            Offset(dividerX, padding.toFloat()),
            Offset(dividerX, (height - padding).toFloat()),
            linePaint
        )

        // draw timeline
        val centerLine = height / 2F
        drawTimeline(canvas, color, option,optionConfig, dividerX, centerLine)
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

        TODO("add ability to center text on x offset")

        canvas.save()
        canvas.translate(position.x, position.y)
        TextPainter.paint(canvas, hello)
        canvas.restore()
    }

    private fun drawIcon(canvas: Canvas, icon: ImageBitmap, color: Color, position: Offset) {
        val paint = Paint()
        paint.colorFilter = ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    0.0F, 0.0F, 0.0F, 0.0F, color.red,
                    0.0F, 0.0F, 0.0F, 0.0F, color.green,
                    0.0F, 0.0F, 0.0F, 0.0F, color.blue,
                    0.0F, 0.0F, 0.0F, color.alpha, 0.0F
                )
            )
        )

        canvas.drawImage(icon, position, paint)
    }

    private fun drawSingleValues(canvas: Canvas, optionKey: String) {
        TODO()
    }

    private fun drawTimeline(
        canvas: Canvas,
        color: Color,
        option: SituationOption,
        optionConfig: SituationConfig,
        dividerX: Float,
        centerLine: Float,
    ) {
        var startX = dividerX + properties.getProperty("column_padding").toFloat()
        // draw first timeline divider line
        drawTimelineDivider(
            canvas,
            color,
            startX,
            centerLine
        )

        val timelineEntries = optionConfig.getTimeline()
        for (entry in timelineEntries) {
            val icon = iconStorage.getIcon(entry.icon.toString())
            val timeValue = option.values[entry.column.toString()]!!.toFloat()

            val endX : Float = startX + timeValue * properties.getProperty("timeline_scaling").toFloat()
            drawTimelineSectionLine(
                canvas,
                Offset(startX, centerLine),
                Offset(endX, centerLine),
                entry.lineType.value,
                color
                )

            drawTimelineDivider(canvas, color, endX, centerLine)

            val midX = startX + ((endX - startX) /2)

            TODO("draw icon and text")


            startX = endX
        }
    }

    private fun drawTimelineDivider(canvas: Canvas, color: Color, x: Float, centerLine: Float) {
        val linePaint = Paint().apply {
            style = PaintingStyle.Stroke
            strokeWidth = properties.getProperty("timeline_weight").toFloat()
            this.color = color
        }
        val dividerHeight = properties.getProperty("timeline_divider_height").toFloat()
        canvas.drawLine(
            Offset(x, centerLine - dividerHeight / 2),
            Offset(x, centerLine + dividerHeight / 2),
            linePaint
        )
    }

    private fun drawTimelineSectionLine(canvas: Canvas, start: Offset, end: Offset, type: LineType, color: Color) {
        TODO("draw line depending on line type")
    }
}
