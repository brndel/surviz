package ui.util

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import ui.Label
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

val LocalStatusLabel = compositionLocalOf<StatusMessage?> { null }

data class StatusMessage(val label: String, val type: StatusMessageType) {
    val id = idCounter++

    companion object {
        private var idCounter = 0
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as StatusMessage

        return id == other.id
    }

    override fun hashCode(): Int {
        return id
    }
}

enum class StatusMessageType(val duration: Duration, val icon: ImageVector, val color: @Composable () -> Color) {
    Info(5.seconds, Icons.Default.Info, { MaterialTheme.colors.secondary }),
    Error(10.seconds, Icons.Default.Warning, { MaterialTheme.colors.error });
}

@Composable
fun StatusLabel(modifier: Modifier = Modifier) {
    val statusLabel = LocalStatusLabel.current

    var progressbarValue by remember { mutableStateOf(0.0F) }

    LaunchedEffect(statusLabel) {
        if (statusLabel == null) return@LaunchedEffect

        animate(
            1.0F,
            0.0F,
            0.0F,
            tween(statusLabel.type.duration.inWholeMilliseconds.toInt(), 0, LinearEasing)
        ) { currentValue, _ ->
            progressbarValue = currentValue
        }
    }

    AnimatedContent(statusLabel, modifier) {
        if (it != null) {
            Surface(
                color = it.type.color(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(4.dp)
            ) {
                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(it.type.icon, null)
                        Label(it.label)
                    }

                    LinearProgressIndicator(
                        progressbarValue,
                        color = MaterialTheme.colors.onSecondary,
                        modifier = Modifier.align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}


