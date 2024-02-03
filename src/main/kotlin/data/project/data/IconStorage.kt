package data.project.data

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import org.jetbrains.skia.Data
import org.jetbrains.skia.Image
import org.jetbrains.skia.svg.*
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream
import java.util.Base64
import javax.imageio.ImageIO


/**
 * This class represents  icon storage.
 * To ensure flexibility all icons stored in the icon storage.
 * So when accessed on different devices the icons are available.
 * The icons are stored in the project data and can be accessed with getIcon.
 *
 * @property icons The icons of the project
 */
class IconStorage {
    private val icons: SnapshotStateMap<String, ImageBitmap> = mutableStateMapOf()

    private val userIconsBits: LinkedHashMap<String, String> = linkedMapOf()

    private val iconWidth = 64
    private val iconHeight = 64

    init {
        loadInitIcons()
    }


    /**
     * This method stores an icon.
     * @param filePath the file path
     */
    fun storeIcon(imagePath: String, userIcon: Boolean) {

        when (imagePath.substringAfterLast(".", "")) {

            "svg" -> {
                val bytes: ByteArray = File(imagePath).readBytes()
                val icon = loadSvgIcon(bytes)
                if (userIcon) {
                    userIconsBits[imagePath] = Base64.getEncoder().encodeToString(bytes)
                }
                icons[imagePath] = icon

            }

            "png" -> {
                val file = File(imagePath)
                val icon = loadPngIcon(file)
                if (userIcon) {
                    userIconsBits[imagePath] = Base64.getEncoder().encodeToString(file.readBytes())
                }
                icons[imagePath] = icon

            }

            else -> throw Exception("Icon type not supported")
        }
    }


    /**
     * This method returns an icons string by a given icon path.
     * @param filePath the icon path
     * @return the icon as a string
     */
    fun getIcon(filePath: String): ImageBitmap? {
        return icons[filePath]
    }

    fun getLoadedIconPaths(): List<String> {
        return icons.keys.sorted()
    }

    private fun loadPngIcon(file: File): ImageBitmap {
        // this should also work for other formats according to
        // https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/imageio/package-summary.html
        val originalImage = ImageIO.read(file)

        val originalWidth = originalImage.width
        val originalHeight = originalImage.height

        // Calculate aspect ratios
        val aspectRatio = originalWidth.toDouble() / originalHeight
        val newAspectRatio = iconWidth.toDouble() / iconHeight

        // Calculate new dimensions while preserving aspect ratio
        val targetWidth: Int
        val targetHeight: Int

        if (aspectRatio > newAspectRatio) {
            targetWidth = iconWidth
            targetHeight = (iconWidth / aspectRatio).toInt()
        } else {
            targetWidth = (iconHeight * aspectRatio).toInt()
            targetHeight = iconHeight
        }

        // resize image
        val tmp = originalImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_SMOOTH)
        val dimg = BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB)

        val g2d = dimg.createGraphics()
        g2d.drawImage(tmp, 0, 0, null)
        g2d.dispose()

        val tempFile = File.createTempFile("image", ".png")
        ImageIO.write(dimg, "png", tempFile)
        val resizedBitmap = loadImageBitmap(FileInputStream(tempFile))

        // center image
        val image = ImageBitmap(iconWidth, iconHeight)
        val canvas = Canvas(image)

        // make icon black
        val paint = Paint()
        paint.colorFilter = ColorFilter.colorMatrix(
            ColorMatrix(
                floatArrayOf(
                    0f, 0f, 0f, 0f, 0f,
                    0f, 0f, 0f, 0f, 0f,
                    0f, 0f, 0f, 0f, 0f,
                    0f, 0f, 0f, 1f, 0f
                )
            )
        )
        // Calculate the position to center the bitmap
        val left = (iconWidth - resizedBitmap.width) / 2f
        val top = (iconHeight - resizedBitmap.height) / 2f

        canvas.drawImage(resizedBitmap, Offset(left, top), paint)
        return image
    }

    private fun loadSvgIcon(bytes: ByteArray): ImageBitmap {
        val svg = SVGDOM(Data.makeFromBytes(bytes))


        val image = ImageBitmap(64, 64, hasAlpha = true)
        val canvas = Canvas(image)

        svg.root?.width = SVGLength(64.0F, SVGLengthUnit.PX)
        svg.root?.height = SVGLength(64.0F, SVGLengthUnit.PX)
        svg.root?.preserveAspectRatio =
            SVGPreserveAspectRatio(SVGPreserveAspectRatioAlign.XMID_YMID)
        svg.render(canvas.nativeCanvas)

        return image

    }

    private fun loadInitIcons() {
        val walker = File("src/main/resources/icons").walk(FileWalkDirection.TOP_DOWN)
        for (entry in walker) {
            if (entry.isFile) {
                storeIcon(entry.path, false)

            }
        }
    }
}