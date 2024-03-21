package data.generator

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import data.generator.resources.ImageConfig
import data.generator.resources.ImageResult
import data.generator.resources.LineType
import data.generator.resources.TextType
import data.project.config.ProjectConfiguration
import data.project.config.SituationConfig
import data.project.config.columns.ZeroColumn
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
    private val cachedIcons = LinkedHashMap<String, ImageBitmap>()

    private val properties: Properties = Properties()
    private val imageConfig: ImageConfig

    private val height: Int
    private val padding: Int

    init {
        useResource("config/image_generator.properties") {
            properties.load(it)
        }
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
    fun generateSituation(situation: Situation): ImageResult {
        var neededWidth = 0
        var maxWidth = 0
        var maxNeededWidth = 0
        val imageList = ArrayList<ImageBitmap>()
        val optionCount = situation.options.size

        for (option in situation.options.values) {
            val imageResult = generateOption(option)

            imageList.add(imageResult.image)

            if (imageResult.image.width > maxWidth) {
                maxWidth = imageResult.image.width
            }

            if (imageResult.neededWidth > maxNeededWidth) {
                maxNeededWidth = imageResult.neededWidth
            }
        }

        val situationHeight = height * optionCount

        val bitmap = ImageBitmap(maxWidth, situationHeight)
        val canvas = Canvas(bitmap)

        for (i in 0..<optionCount) {
            val image = imageList[i]
            canvas.drawImage(image, Offset(0F, i * height.toFloat()), Paint())
        }
        return ImageResult(bitmap, maxNeededWidth)
    }

    /**
     * This method generates an Image for the given situation option.
     * @param option The situation option.
     * @return The generated image.
     *
     * @throws NoSuchFieldException if no configuration was found for the option
     */
    fun generateOption(option: SituationOption): ImageResult {
        // initialize values
        var neededWidth = 0
        val optionConfig =
            config.getSituationConfig(option.name)

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
        backgroundColor.color = imageConfig.backgroundColor.value;
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
        val dividerLength = properties.getProperty("divider_length").toFloat()
        val dividerX =
            padding + singleValueSectionSize + properties.getProperty("column_padding").toFloat()

        val linePaint = Paint().apply {
            style = PaintingStyle.Stroke
            strokeWidth = properties.getProperty("divider_weight").toFloat()
            this.color = Color(properties.getProperty("divider_color").toLong(16))
        }

        canvas.drawLine(
            Offset(dividerX, centerLine - dividerLength / 2),
            Offset(dividerX, centerLine + dividerLength / 2),
            linePaint
        )

        // draw timeline
        val timelineWidth =
            drawTimeline(canvas, color, option, optionConfig, dividerX, centerLine, neededWidth)

        // calculate needed width
        neededWidth += singleValueSectionSize
        neededWidth += timelineWidth
        neededWidth += 2 * padding
        neededWidth += 2 * properties.getProperty("column_padding").toInt()

        return ImageResult(image, neededWidth)
    }

    ///////////////////////////////////////////////////////////////////////////////
    // private functions
    ///////////////////////////////////////////////////////////////////////////////

    /**
     * Draws text on a [canvas]
     *
     * @param canvas base [Canvas] the text should be drawn on
     * @param text the text to draw on canvas
     * @param color [Color] of the text
     * @param position top-left corner of the text
     * @param textType type of text, determines text size
     * @param centerX if true center text around x coordinate of [position]
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
        // configure text style
        val fontSize: TextUnit = properties.getProperty(textType.fontSizeKey).toFloat().sp
        val fontFamily = FontFamily(
            fonts = listOf(
                Font(
                    resource = "fonts/Poppins-Regular.ttf",
                    weight = FontWeight.W400,
                    style = FontStyle.Normal
                )
            )
        )

        val style = TextStyle(
            color = color,
            fontSize = fontSize,
            fontWeight = textType.fontWeight,
            fontFamily = fontFamily,
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
     * @param canvas [Canvas] to draw on
     * @param icon icon that should be drawn on the [canvas]
     * @param color [Color] of the [icon]
     * @param center center point the [icon] should be drawn on
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
     * @param canvas [Canvas] to draw on
     * @param color [Color] of the single values
     * @param optionConfig config of the [SituationOption]
     * @param option [SituationOption] the single values should be drawn for
     * @param centerLine y-position of the center line
     */
    private fun drawSingleValues(
        canvas: Canvas,
        color: Color,
        optionConfig: SituationConfig,
        option: SituationOption,
        centerLine: Float
    ) {
        for ((index, id) in config.getSingleValueConfigOrder().withIndex()) {
            val yOffset = properties.getProperty("single_value_y_offset").toFloat()

            // don't draw if wrong config
            val singleValueConfig = config.getSingleValues()[id] ?: continue
            val column = optionConfig.getColumns(id)

            val value = column.getValue(singleValueConfig, optionConfig, option)
            val unit = singleValueConfig.unit.value

            // change alpha if value == 0 and not ZeroColumn
            var newColor = color.copy()
            if (value == 0.0 && column !is ZeroColumn) {
                val alpha = properties.getProperty("single_value_alpha").toFloat()
                newColor = color.copy(alpha = alpha)
            }

            // draw text
            val count = index + 1
            val size = properties.getProperty("single_value_size").toFloat()

            val x = padding.toFloat() + (size * count) - (size / 2)
            drawText(
                canvas, "$value $unit",
                newColor,
                Offset(
                    x,
                    centerLine + properties.getProperty("single_value_text_padding")
                        .toFloat() + yOffset
                ),
                TextType.Label,
                true
            )

            // draw icon
            val iconPath = singleValueConfig.icon.getIcon(value)
            if (iconPath != null) {
                val icon = getIcon(iconPath)
                val iconHeight = icon?.height ?: 0
                drawIcon(
                    canvas,
                    icon,
                    newColor,
                    Offset(
                        x,
                        centerLine - properties.getProperty("single_value_icon_padding")
                            .toFloat() - (iconHeight / 2) + yOffset
                    )
                )
            }

        }
    }

    /**
     * Draw timeline
     *
     * @param canvas [Canvas] to draw on
     * @param color [Color] of the timeline
     * @param option [SituationOption] the timeline should be drawn for
     * @param optionConfig config of the [SituationOption]
     * @param dividerX x-position of the line dividing single value section and timeline section
     * @param centerLine y-position of the center line
     */
    private fun drawTimeline(
        canvas: Canvas,
        color: Color,
        option: SituationOption,
        optionConfig: SituationConfig,
        dividerX: Float,
        centerLine: Float,
        neededWidth: Int
    ): Int {
        var startX = dividerX + properties.getProperty("column_padding").toFloat()
        val timelinePadding = properties.getProperty("timeline_padding").toFloat()
        val yOffset = properties.getProperty("timeline_y_offset").toFloat()
        var width = neededWidth

        // go over every section
        val timelineEntries = optionConfig.getTimeline()
        for (entry in timelineEntries) {
            // draw line
            val timeValue = option.values[entry.column.value]?.toFloat() ?: 0.0F

            // don't draw section if value is 0
            if (timeValue == 0F) continue

            // calculate length of section
            val timelineLength = timeValue * imageConfig.timelineScaling.value.toFloat()
            width += timelineLength.toInt()

            val endX: Float = startX + timelineLength

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

            val icon = entry.icon.value?.let { getIcon(it) }

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
        return width
    }

    /**
     * Draw timeline divider
     *
     * @param canvas [Canvas] to draw on
     * @param color [Color] of the divider
     * @param x x-position of the divider
     * @param centerLine y-position of the center line
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
     * @param canvas [Canvas] to draw on
     * @param start start point of the line
     * @param end end point of the line
     * @param type [LineType] of the line
     * @param color [Color] of the line
     */
    private fun drawTimelineSectionLine(
        canvas: Canvas,
        start: Offset,
        end: Offset,
        type: LineType,
        color: Color
    ) {
        // create path effect
        val lineKey = type.lineKey
        val spaceKey = type.spaceKey

        var pathEffect: PathEffect? = null

        if (lineKey != null && spaceKey != null) {
            val lineLength = properties.getProperty(lineKey).toFloat()
            val spaceLength = properties.getProperty(spaceKey).toFloat()

            pathEffect = PathEffect.dashPathEffect(floatArrayOf(lineLength, spaceLength))
        }

        // setup paint
        val paint = Paint()
        paint.style = PaintingStyle.Stroke
        paint.color = color
        paint.strokeWidth = properties.getProperty("timeline_weight").toFloat()
        paint.pathEffect = pathEffect

        // draw line
        canvas.drawLine(start, end, paint)
    }

    /**
     * Resizes the given bitmap to given size
     *
     * @param bitmap [ImageBitmap] to resize
     * @param width width of resized bitmap
     * @param height height of resized bitmap
     * @return resized [ImageBitmap]
     */
    private fun resizeBitmap(bitmap: ImageBitmap?, width: Int, height: Int): ImageBitmap? {
        if (bitmap == null) return null
        val image = ImageBitmap(width, height)
        val canvas = Canvas(image)
        canvas.drawImageRect(
            bitmap,
            srcSize = IntSize(bitmap.width, bitmap.height),
            dstSize = IntSize(width, height),
            paint = Paint()
        )
        return image
    }

    private fun getIcon(key: String): ImageBitmap? {
        if (cachedIcons.containsKey(key)) {
            return cachedIcons[key]
        }
        val icon = iconStorage.getIcon(key) ?: return null
        cachedIcons[key] = icon
        return icon
    }
}
