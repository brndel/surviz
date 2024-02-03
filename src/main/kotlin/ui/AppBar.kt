package ui

import LocalGlobalCallbacks
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier


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
                        Text(it.toString())
                    }
                }
            }
        }
    }
}