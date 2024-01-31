package data.generator.resources

import androidx.compose.runtime.*

data class ImageConfig(
    val width: MutableIntState = mutableIntStateOf(650),
    val timelineScaling: MutableDoubleState = mutableDoubleStateOf(1.0)
) // TODO read width value from configuration file
