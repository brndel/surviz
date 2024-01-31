package data.generator.resources

import androidx.compose.runtime.*
import java.io.FileInputStream
import java.util.Properties

class ImageConfig {
    var width: MutableIntState
    var timelineScaling: MutableDoubleState
    init {
        val properties = Properties()
        properties.load(FileInputStream("src/main/resources/config/image_generator.properties"))
        width = mutableIntStateOf(properties.getProperty("situation_default_width").toInt())
        timelineScaling = mutableDoubleStateOf(properties.getProperty("timeline_default_scaling").toDouble())
    }
}

