package data.project.data

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.nativeCanvas
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import org.jetbrains.skia.Data
import org.jetbrains.skia.Image
import org.jetbrains.skia.Rect
import org.jetbrains.skia.svg.*
import java.io.File
import java.util.Base64
import java.util.UUID
import kotlin.math.max


/**
 * This class represents  icon storage.
 * To ensure flexibility all icons stored in the icon storage.
 * So when accessed on different devices the icons are available.
 * The icons are stored in the project data and can be accessed with getIcon.
 *
 * @property internalIcons The icons of the project
 */
class IconStorage {
    private val internalIcons: SnapshotStateMap<String, ImageBitmap> = mutableStateMapOf()
    private val userIcons: SnapshotStateMap<String, UserIcon> = mutableStateMapOf()

    private data class UserIcon(val image: ImageBitmap, val filePath: String, val originalFileBase64: String)

    init {
        loadInternalIcons()
    }


    /**
     * This method stores an icon.
     * @param filePath the file path
     */
    fun storeIcon(imagePath: String) {
        val file = File(imagePath)
        val bytes = file.readBytes()

        storeUserIcon(bytes, file.absolutePath)
    }

    private fun storeUserIcon(bytes: ByteArray, filePath: String, id: String = UUID.randomUUID().toString()) {
        val icon = createIcon(bytes, File(filePath).extension)

        val userIcon = UserIcon(icon, filePath, Base64.getEncoder().encodeToString(bytes))
        userIcons[id] = userIcon
    }

    private fun storeInternalIcon(imagePath: String) {
        val file = File(imagePath)
        val icon = createIcon(file.readBytes(), file.extension)

        internalIcons[imagePath] = icon
    }


    private fun createIcon(bytes: ByteArray, extension: String): ImageBitmap {
        return when (extension) {
            "svg" -> {
                loadSvgIcon(bytes)
            }

            "png" -> {
                loadPngIcon(bytes)
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
        return internalIcons[filePath]
    }

    fun getLoadedIconPaths(): List<String> {
        return internalIcons.keys.sorted()
    }

    private fun loadPngIcon(bytes: ByteArray): ImageBitmap {
        val png = Image.makeFromEncoded(bytes)
        val image = ImageBitmap(ICON_SIZE, ICON_SIZE, hasAlpha = true)
        val canvas = Canvas(image)

        val scaleFactor = ICON_SIZE.toFloat() / max(png.width, png.height)
        val scaledWidth = png.width * scaleFactor
        val scaledHeight = png.height * scaleFactor

        val top = (ICON_SIZE.toFloat() - scaledHeight) / 2
        val left = (ICON_SIZE.toFloat() - scaledWidth) / 2

        canvas.nativeCanvas.drawImageRect(png, Rect.makeXYWH(left, top, scaledWidth, scaledHeight))

        return image
    }

    private fun loadSvgIcon(bytes: ByteArray): ImageBitmap {
        val svg = SVGDOM(Data.makeFromBytes(bytes))

        val image = ImageBitmap(ICON_SIZE, ICON_SIZE, hasAlpha = true)
        val canvas = Canvas(image)

        svg.root?.width = SVGLength(ICON_SIZE.toFloat(), SVGLengthUnit.PX)
        svg.root?.height = SVGLength(ICON_SIZE.toFloat(), SVGLengthUnit.PX)
        svg.root?.preserveAspectRatio = SVGPreserveAspectRatio(SVGPreserveAspectRatioAlign.XMID_YMID)
        svg.render(canvas.nativeCanvas)

        return image

    }

    private fun loadInternalIcons() {
        val walker = File("src/main/resources/icons").walk(FileWalkDirection.TOP_DOWN)
        for (entry in walker) {
            if (entry.isFile) {
                storeInternalIcon(entry.path)
            }
        }
    }

    companion object {
        const val ICON_SIZE = 64

        val SERIALIZER = JsonSerializer<IconStorage> { iconStorage, _, _ ->
            val json = JsonObject()

            for ((id, icon) in iconStorage.userIcons.entries) {
                val iconObj = JsonObject()

                iconObj.addProperty("path", icon.filePath)
                iconObj.addProperty("content", icon.originalFileBase64)

                json.add(id, iconObj)
            }

            return@JsonSerializer json
        }

        val DESERIALIZER = JsonDeserializer { obj, _, _ ->
            val iconStorage = IconStorage()

            for (prop in obj.asJsonObject.entrySet()) {
                val id = prop.key

                val filePath = prop.value.asJsonObject.get("path").asString
                val base64content = prop.value.asJsonObject.get("content").asString

                val bytes = Base64.getDecoder().decode(base64content)

                iconStorage.storeUserIcon(bytes, filePath, id)
            }


            return@JsonDeserializer iconStorage
        }
    }
}