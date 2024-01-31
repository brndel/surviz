package data.generator

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.toAwtImage
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import data.generator.resources.ImageConfig
import data.generator.resources.TextType
import data.project.config.LineType
import data.project.config.ProjectConfiguration
import data.project.config.SituationConfig
import data.project.config.columns.ZeroColumn
import data.project.data.IconStorage
import data.project.data.Situation
import data.project.data.SituationOption
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image
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
    private val imageConfig: ImageConfig

    private val height: Int
    private val padding: Int

    init {
        properties.load(FileInputStream("src/main/resources/config/image_generator.properties"))
        height = properties.getProperty("situation_height").toInt()
        padding = properties.getProperty("border_padding").toInt()
        imageConfig = config.imageConfig
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
    fun generateSituation(situation: Situation): ImageBitmap? {
        val optionsCount = situation.options.size

        // check if situation contains options
        if (situation.options.isEmpty()) return null

        val width = imageConfig.width.value
        val height = height * optionsCount

        val image = ImageBitmap(width, height)
        val canvas = Canvas(image)

        for (i in 0..<optionsCount) {
            val option = situation.options[i]
            val optionImageBitmap = generateOption(option)
            canvas.drawImage(
                optionImageBitmap,
                Offset(0F, i * height.toFloat()),
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
            config.getSituationConfig()[option.name] ?: throw NoSuchFieldException()

        // scale image dynamically based on count of single values
        val singleValueCount = config.getSingleValues().size
        val singleValueSectionSize = kotlin.math.max(
            singleValueCount * properties.getProperty("single_value_size").toInt(),
            properties.getProperty("single_value_min_width").toInt()
        )

        val width = imageConfig.width.value
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
            optionConfig.name.value,
            color,
            Offset(
                properties.getProperty("option_title_x_offset").toFloat() + padding,
                properties.getProperty("option_title_y_offset").toFloat() + padding
            ),
            textType = TextType.Title,
            false
        )

        // draw single values
        val centerLine = height / 2F
        drawSingleValues(canvas, color, optionConfig, option, centerLine)

        // draw divider line
        val dividerX =
            padding + singleValueSectionSize + properties.getProperty("column_padding").toFloat()

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
        drawTimeline(canvas, color, option, optionConfig, dividerX, centerLine)
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
     * @param centerX if true center text around x coordinate of offset
     * @param width
     */
    private fun drawText(
        canvas: Canvas,
        text: String,
        color: Color,
        position: Offset,
        textType: TextType = TextType.Label,
        centerX: Boolean,
        width: Int? = null
    ) {
        val style = TextStyle(
            color = color,
            fontSize = textType.fontSize,
            fontWeight = textType.fontWeight,
            textAlign = if (width != null) TextAlign.Center else null
        )

        val measurer = TextMeasurer(createFontFamilyResolver(), Density(1.0F), LayoutDirection.Ltr)

        val textLayoutResult = measurer.measure(
            text,
            style,
            constraints = Constraints(
                minWidth = width ?: 0,
                maxWidth = width ?: Constraints.Infinity
            )
        )

        // center  text if necessary
        var x = position.x
        if (centerX) {
            x -= textLayoutResult.size.width / 2
        }

        canvas.save()
        canvas.translate(x, position.y)
        TextPainter.paint(canvas, textLayoutResult)
        canvas.restore()
    }

    /**
     * Draw icon around given center point
     *
     * @param canvas
     * @param icon
     * @param color
     * @param center
     */
    private fun drawIcon(canvas: Canvas, icon: ImageBitmap?, color: Color, center: Offset) {
        if (icon == null) return

        // color black icons
        val paint = Paint()
        paint.colorFilter = ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    0.0F, 0.0F, 0.0F, 0.0F, color.red * 255,
                    0.0F, 0.0F, 0.0F, 0.0F, color.green * 255,
                    0.0F, 0.0F, 0.0F, 0.0F, color.blue * 255,
                    0.0F, 0.0F, 0.0F, color.alpha, 0.0F
                )
            )
        )
        val width = icon.width
        val height = icon.height
        val position = Offset(center.x - (width / 2), center.y - (height / 2))
        canvas.drawImage(icon, position, paint)
    }

    /**
     * Draw all single values
     *
     * @param canvas
     * @param color
     * @param optionConfig
     * @param option
     * @param centerLine
     */
    private fun drawSingleValues(
        canvas: Canvas,
        color: Color,
        optionConfig: SituationConfig,
        option: SituationOption,
        centerLine: Float
    ) {
        val columns = optionConfig.singleValueColumns.toMap()

        for (id in config.getSingleValueConfigOrder().toList()) {
            // don't draw if wrong config
            val singleValueConfig = config.getSingleValues()[id] ?: continue
            val column = columns[id] ?: continue

            val value = column.getValue(singleValueConfig, optionConfig, option)
            val unit = singleValueConfig.unit.value

            // change alpha if value == 0 and not ZeroColumn
            var newColor = color.copy()
            if (value == 0.0 && column !is ZeroColumn) {
                val alpha = properties.getProperty("single_value_alpha").toFloat()
                newColor = color.copy(alpha = alpha)
            }

            // draw text
            val count = config.getSingleValueConfigOrder().toList().indexOf(id) + 1
            val size = properties.getProperty("single_value_size").toFloat()

            val x = padding.toFloat() + (size * count) - (size / 2)
            drawText(
                canvas, "$value $unit",
                newColor,
                Offset(
                    x,
                    centerLine + properties.getProperty("single_value_text_padding").toFloat()
                ),
                TextType.Label,
                true
            )

            // draw icon
            val iconPath = singleValueConfig.icon.getIcon(value)
            if (iconPath != null) {
                val icon = iconStorage.getIcon(iconPath)
                val iconHeight = icon?.height ?: 0
                drawIcon(
                    canvas,
                    icon,
                    newColor,
                    Offset(
                        x,
                        centerLine - properties.getProperty("single_value_icon_padding")
                            .toFloat() - iconHeight / 2
                    )
                )
            }

        }
    }

    /**
     * Draw timeline
     *
     * @param canvas
     * @param color
     * @param option
     * @param optionConfig
     * @param dividerX
     * @param centerLine
     */
    private fun drawTimeline(
        canvas: Canvas,
        color: Color,
        option: SituationOption,
        optionConfig: SituationConfig,
        dividerX: Float,
        centerLine: Float,
    ) {
        var startX = dividerX + properties.getProperty("column_padding").toFloat()
        val timelinePadding = properties.getProperty("timeline_padding").toFloat()
        val yOffset = properties.getProperty("timeline_y_offset").toFloat()

        // go over every section
        val timelineEntries = optionConfig.getTimeline()
        for (entry in timelineEntries) {
            // draw line
            val timeValue = option.values[entry.column.value]?.toFloat() ?: 0.0F

            // don't draw section if value is 0
            if (timeValue == 0F) continue

            val endX: Float =
                startX + timeValue * imageConfig.timelineScaling.value.toFloat()

            drawTimelineSectionLine(
                canvas,
                Offset(startX, centerLine + yOffset),
                Offset(endX, centerLine + yOffset),
                entry.lineType.value,
                color
            )
            // draw divider
            drawTimelineDivider(canvas, color, startX, centerLine + yOffset)
            drawTimelineDivider(canvas, color, endX, centerLine + yOffset)

            // draw icon
            val midX = startX + ((endX - startX) / 2)

            val icon = entry.icon.value?.let { iconStorage.getIcon(it) }

            val iconSize = properties.getProperty("timeline_icon_size").toInt()
            val resizedIcon = resizeBitmap(icon, iconSize, iconSize)


            val iconHeight = icon?.height ?: 0
            drawIcon(
                canvas,
                resizedIcon,
                color,
                Offset(midX, centerLine - timelinePadding - (iconHeight / 2) + yOffset)
            )


            // draw text
            val unit = properties.getProperty("timeline_time_unit")

            drawText(
                canvas,
                "${timeValue.toInt()} $unit",
                color,
                Offset(midX, centerLine + timelinePadding + yOffset),
                textType = TextType.Label,
                true
            )

            startX = endX
        }
    }

    /**
     * Draw timeline divider
     *
     * @param canvas
     * @param color
     * @param x
     * @param centerLine
     */
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

    /**
     * Draw timeline section line
     *
     * @param canvas
     * @param start
     * @param end
     * @param type
     * @param color
     */
    private fun drawTimelineSectionLine(
        canvas: Canvas,
        start: Offset,
        end: Offset,
        type: LineType,
        color: Color
    ) {
        // setup paint
        val paint = Paint()
        paint.style = PaintingStyle.Stroke
        paint.color = color
        paint.strokeWidth = properties.getProperty("timeline_weight").toFloat()
        paint.pathEffect = type.pathEffect

        // draw line
        canvas.drawLine(start, end, paint)
    }

    private fun resizeBitmap(bitmap: ImageBitmap?, width: Int, height: Int): ImageBitmap? {
        if (bitmap == null) return null
        val image = ImageBitmap(width, height)
        val canvas = Canvas(image)
        canvas.drawImageRect(bitmap, srcSize = IntSize(width, height), paint = Paint())
        return image
    }
}
