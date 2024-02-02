package ui.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val LocalSurface = compositionLocalOf { false }

@Composable
fun NestedSurface(
    modifier: Modifier = Modifier,
    border: BorderStroke? = null,
    elevation: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val surface = LocalSurface.current


    CompositionLocalProvider(
        LocalSurface provides !surface
    ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            color = if (surface) MaterialTheme.colors.surface else MaterialTheme.colors.background,
            elevation = elevation,
            border = border,
            content = content,
        )
    }
}