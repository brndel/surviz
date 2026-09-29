package data.generator.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.unit.IntSize

fun drawDivider(
    canvas: Canvas,
    x: Float,
    centerY: Float,
    length: Float,
    paint: Paint,
) {
    canvas.drawLine(
        Offset(x, centerY - length / 2),
        Offset(x, centerY + length / 2),
        paint,
    )
}

/**
 * Resizes the given bitmap to given size
 *
 * @param bitmap [ImageBitmap] to resize
 * @param width width of resized bitmap
 * @param height height of resized bitmap
 * @return resized [ImageBitmap]
 */
fun resizeBitmap(
    bitmap: ImageBitmap?,
    width: Int,
    height: Int,
): ImageBitmap? {
    if (bitmap == null) return null
    val image = ImageBitmap(width, height)
    val canvas = Canvas(image)
    canvas.drawImageRect(
        bitmap,
        srcSize = IntSize(bitmap.width, bitmap.height),
        dstSize = IntSize(width, height),
        paint = Paint(),
    )
    return image
}

fun createStrokePaint(
    colorLong: Long,
    strokeWidth: Float,
): Paint =
    Paint().apply {
        style = PaintingStyle.Stroke
        this.strokeWidth = strokeWidth
        this.color = Color(colorLong)
    }

fun createStrokePaint(
    color: Color,
    strokeWidth: Float,
): Paint =
    Paint().apply {
        style = PaintingStyle.Stroke
        this.strokeWidth = strokeWidth
        this.color = color
    }
