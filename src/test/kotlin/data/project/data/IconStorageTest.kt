package data.project.data

import androidx.compose.desktop.AppWindow
import androidx.compose.desktop.ComposePanel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.Menu
import androidx.compose.material3.icons.filled.Send
import androidx.compose.material3.icons.filled.Settings
import androidx.compose.material3.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.ColorMatrixColorFilter
import androidx.compose.ui.graphics.ColorMatrix.Companion.identity
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import jdk.javadoc.internal.doclets.formats.html.markup.Text
import org.jetbrains.compose.desktop.LocalAppWindow
import org.jetbrains.compose.desktop.Window
import org.jetbrains.compose.desktop.ui.tooling.preview.Preview
import java.awt.Window
import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO


/**
 * Unfertige Testklasse für die Klasse IconStorage
 */
class IconStorageTest

fun main() = Window {
    val iconUrl = URL("https://example.com/your_icon.png") // replace with your icon URL
    val iconImage = loadImage(iconUrl)
    val appWindow = LocalAppWindow.current

    MaterialTheme {
        AppContent(iconImage, appWindow::setWindowIcon)
    }
}

@Composable
private fun AppContent(iconImage: ImageBitmap?, setWindowIcon: (ImageBitmap?) -> Unit) {
    var windowIcon by remember { mutableStateOf(iconImage) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (iconImage != null) {
            Image(
                bitmap = iconImage,
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(64.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Change the icon dynamically
                val newIconUrl = URL("https://example.com/your_new_icon.png") // replace with your new icon URL
                val newIconImage = loadImage(newIconUrl)
                windowIcon = newIconImage
                setWindowIcon(newIconImage)
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Change Icon")
        }
    }
}

@Composable
private fun loadImage(url: URL): ImageBitmap? {
    return try {
        val image = ImageIO.read(url)
        val rgb = IntArray(image.width * image.height)
        image.getRGB(0, 0, image.width, image.height, rgb, 0, image.width)
        ImageBitmap(IntArray(image.width * image.height) { i ->
            val color = rgb[i]
            (color and 0xFF shl 16 or (color and 0xFF00) or (color and 0xFF0000 shr 16) or (color and 0xFF000000.toInt())).toLong()
        }, image.width, image.height)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}