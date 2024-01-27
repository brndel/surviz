package ui.util

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.burnoutcrew.reorderable.ReorderableState
import org.burnoutcrew.reorderable.detectReorder

@Composable
fun ReorderHandle(reorderState: ReorderableState<*>) {
    Surface(
        modifier = Modifier.detectReorder(reorderState),
        color = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        shape = RoundedCornerShape(4.dp)
    ) {
        Icon(Icons.Default.Menu, null, modifier = Modifier.padding(4.dp))
    }
}