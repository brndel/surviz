package ui

import LocalGlobalCallbacks
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.util.NestedSurface


@Composable
fun AppBar() {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row {
            for (group in actionsManager.groups) {
                AppBarButton(group)
            }
        }
    }
}

@Composable
private fun AppBarButton(group: AppBarGroup) {
    var menuOpen by remember { mutableStateOf(false) }

    Box {
        TextButton(
            { menuOpen = true }
        ) {
            Label(group.label)
        }

        DropdownMenu(menuOpen, { menuOpen = false }) {
            val globalCallbacks = LocalGlobalCallbacks.current!!
            for (action in group.actions) {
                DropdownMenuItem({
                    menuOpen = false
                    action.onClick(globalCallbacks)
                }) {
                    Label(action.label)

                    action.shortcut?.let {
                        ShortcutTag(it)
                    }
                }
            }
        }
    }
}

@Composable
private fun ShortcutTag(shortcut: Shortcut) {
    NestedSurface(Modifier.padding(4.dp)) {
        Text(shortcut.toStringLocalized(), modifier = Modifier.padding(4.dp))
    }
}