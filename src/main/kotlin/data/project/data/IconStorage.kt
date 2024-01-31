package data.project.data

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.useResource
import org.jetbrains.skia.Data
import org.jetbrains.skia.Image
import org.jetbrains.skia.svg.*
import java.io.File
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

    init {
        loadInitIcons()
    }

    /**
     * This method stores an icon.
     * @param filePath the file path
     */
    fun storeIcon(imagePath: String) {

        when (imagePath.substringAfterLast(".", "")) {
            "svg" -> {
                val bytes: ByteArray = File(imagePath).readBytes()
                val icon = loadSvgIcon(bytes)
                icons[imagePath] = icon

            }

            "png" -> {
                val bytes: ByteArray = File(imagePath).readBytes()
                val icon = loadPngIcon(bytes)
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

    private fun loadPngIcon(bytes: ByteArray): ImageBitmap {
        val png = Image.makeFromEncoded(bytes)
        val image = ImageBitmap(png.width, png.height, hasAlpha = true)
        val canvas = Canvas(image)
        canvas.nativeCanvas.drawImage(png, 0.0F, 0.0F)
        return image


    }

    private fun loadSvgIcon(bytes: ByteArray): ImageBitmap {
        val svg = SVGDOM(Data.makeFromBytes(bytes))


        val image = ImageBitmap(64, 64, hasAlpha = true)
        val canvas = Canvas(image)

        svg.root?.width = SVGLength(64.0F, SVGLengthUnit.PX)
        svg.root?.height = SVGLength(64.0F, SVGLengthUnit.PX)
        svg.root?.preserveAspectRatio = SVGPreserveAspectRatio(SVGPreserveAspectRatioAlign.XMID_YMID)
        svg.render(canvas.nativeCanvas)

        return image

    }

    private fun loadInitIcons() {
        val walker = File("src/main/resources/icons").walk(FileWalkDirection.TOP_DOWN)
        for (entry in walker) {
            if (entry.isFile) {
                storeIcon(entry.path)
            }
        }
//        this.javaClass.classLoader.getResourceAsStream("icons")?.bufferedReader()
//            ?.useLines { lines ->
//                lines.forEach {
//                    storeIcon("src/main/resources/icons/$it")
//
//                }
//            }
    }
}