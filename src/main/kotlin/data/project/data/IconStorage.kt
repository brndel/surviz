package data.project.data

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.loadImageBitmap
import com.google.gson.JsonDeserializer
import com.google.gson.JsonObject
import com.google.gson.JsonSerializer
import data.resources.exceptions.FileTypeException
import org.jetbrains.skia.Data
import org.jetbrains.skia.svg.*
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileInputStream
import java.util.Base64
import java.util.UUID
import javax.imageio.ImageIO


/**
 * This class represents  icon storage.
 * To ensure flexibility all icons stored in the icon storage.
 * So when accessed on different devices the icons are available.
 * The icons are stored in the project data and can be accessed with getIcon.
 *
 * @property internalIcons The icons of the project
 */
data class IconStorage(
    private val userIcons: SnapshotStateMap<String, UserIcon> = mutableStateMapOf()
) {
    private val internalIcons: SnapshotStateMap<String, ImageBitmap> = mutableStateMapOf()

    data class UserIcon(
        val image: ImageBitmap,
        val filePath: String,
        val originalFileBase64: String
    )

    init {
        loadInternalIcons()
    }


    /**
     * This method stores an icon.
     * @param imagePath the file path
     */
    fun storeIcon(imagePath: String) {
        val file = File(imagePath)
        storeUserIcon(file)
    }

    private fun storeUserIcon(file: File, id: String = UUID.randomUUID().toString()) {
        val bytes = file.readBytes()
        val icon = createIcon(bytes, file.extension)

        val userIcon = UserIcon(icon, file.absolutePath, Base64.getEncoder().encodeToString(bytes))
        userIcons[id] = userIcon
    }

    private fun storeInternalIcon(imagePath: String) {
        val file = File(imagePath)
        val icon = createIcon(file.readBytes(), file.extension)

        internalIcons[imagePath] = icon
    }

    /**
     * This method returns an icons string by a given icon path.
     * @param name the icon name
     * @return the icon as a string
     */
    fun getIcon(name: String): ImageBitmap? {
        return userIcons[name]?.image ?: internalIcons[name]
    }

    fun getInternalIconNames(): List<String> {
        return internalIcons.keys.sorted()
    }

    fun getUserIconNames(): List<String> {
        return userIcons.keys.sorted()
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
        private const val ICON_SIZE = 64

        val serializer = JsonSerializer<IconStorage> { iconStorage, _, _ ->
            val json = JsonObject()

            for ((id, icon) in iconStorage.userIcons.entries) {
                val iconObj = JsonObject()

                iconObj.addProperty("path", icon.filePath)
                iconObj.addProperty("content", icon.originalFileBase64)

                json.add(id, iconObj)
            }

            return@JsonSerializer json
        }

        val deserializer = JsonDeserializer { obj, _, _ ->
            val iconStorage = IconStorage()

            for (prop in obj.asJsonObject.entrySet()) {
                val id = prop.key

                val filePath = prop.value.asJsonObject.get("path").asString
                val base64content = prop.value.asJsonObject.get("content").asString

                val bytes = Base64.getDecoder().decode(base64content)

                val file = File(filePath)
                file.writeBytes(bytes)

                iconStorage.storeUserIcon(file, id)
            }


            return@JsonDeserializer iconStorage
        }

        private fun createIcon(bytes: ByteArray, fileExtension: String): ImageBitmap {
            return when (fileExtension) {
                "svg" -> {
                    loadSvgIcon(bytes)
                }

                "png" -> {
                    loadPngIcon(bytes)
                }

                else -> throw FileTypeException("Icon type not supported")
            }
        }

        private fun loadPngIcon(bytes: ByteArray): ImageBitmap {
            // this should also work for other formats according to
            // https://docs.oracle.com/en/java/javase/17/docs/api/java.desktop/javax/imageio/package-summary.html
            val originalImage = ImageIO.read(bytes.inputStream())

            val originalWidth = originalImage.width
            val originalHeight = originalImage.height

            // Calculate aspect ratios
            val aspectRatio = originalWidth.toDouble() / originalHeight
            val newAspectRatio = 1

            // Calculate new dimensions while preserving aspect ratio
            val targetWidth: Int
            val targetHeight: Int

            if (aspectRatio > newAspectRatio) {
                targetWidth = ICON_SIZE
                targetHeight = (ICON_SIZE / aspectRatio).toInt()
            } else {
                targetWidth = (ICON_SIZE * aspectRatio).toInt()
                targetHeight = ICON_SIZE
            }

            // resize image
            val tmp = originalImage.getScaledInstance(
                targetWidth,
                targetHeight,
                java.awt.Image.SCALE_SMOOTH
            )
            val dimg = BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB)

            val g2d = dimg.createGraphics()
            g2d.drawImage(tmp, 0, 0, null)
            g2d.dispose()

            val tempFile = File.createTempFile("image", ".png")
            ImageIO.write(dimg, "png", tempFile)
            val resizedBitmap = loadImageBitmap(FileInputStream(tempFile))

            // center image
            val image = ImageBitmap(ICON_SIZE, ICON_SIZE)
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
            val left = (ICON_SIZE - resizedBitmap.width) / 2f
            val top = (ICON_SIZE - resizedBitmap.height) / 2f

            canvas.drawImage(resizedBitmap, Offset(left, top), paint)
            return image
        }

        private fun loadSvgIcon(bytes: ByteArray): ImageBitmap {
            val svg = SVGDOM(Data.makeFromBytes(bytes))

            val image = ImageBitmap(ICON_SIZE, ICON_SIZE, hasAlpha = true)
            val canvas = Canvas(image)

            svg.root?.width = SVGLength(ICON_SIZE.toFloat(), SVGLengthUnit.PX)
            svg.root?.height = SVGLength(ICON_SIZE.toFloat(), SVGLengthUnit.PX)
            svg.root?.preserveAspectRatio =
                SVGPreserveAspectRatio(SVGPreserveAspectRatioAlign.XMID_YMID)
            svg.render(canvas.nativeCanvas)

            return image

        }
    }
}