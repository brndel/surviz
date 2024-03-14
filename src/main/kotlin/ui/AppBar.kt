package ui

import LocalGlobalCallbacks
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ui.util.StatusLabel


@Composable
fun AppBar() {
    Surface(
        color = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (group in actionsManager.groups) {
                AppBarButton(group)
            }
            Box(Modifier.weight(1F))
            StatusLabel()
        }
    }
}

@Composable
private fun AppBarButton(group: AppBarGroup) {
    val globalCallbacks = LocalGlobalCallbacks.current!!
    if(group.actions.size == 1) {
        val action = group.actions[0]
        Box {(Modifier.padding(end = 8.dp))
            TextButton(
                { action.onClick(globalCallbacks) }
            ) {
                Label(group.label)
            }
        }
    }else{

    var menuOpen by remember { mutableStateOf(false) }

    Box {
        TextButton(
            { menuOpen = true }
        ) {
            Label(group.label)
        }

        DropdownMenu(menuOpen, { menuOpen = false }) {
            for (action in group.actions) {
                DropdownMenuItem({
                    menuOpen = false
                    action.onClick(globalCallbacks)
                })
                {
                    Box(Modifier.padding(end = 8.dp)) {
                        action.icon?.let { Icon(it, null) }
                    }
                    Column(verticalArrangement = Arrangement.spacedBy(4.dp), modifier = Modifier.padding(4.dp)) {
                        Label(action.label)

                        action.shortcut?.let {
                            ShortcutTag(it)
                        }
                    }
                }
            }
        }
        }
    }
}

@Composable
private fun ShortcutTag(shortcut: Shortcut) {
    Surface(color = MaterialTheme.colors.background, shape = RoundedCornerShape(4.dp)) {
        Text(shortcut.toStringLocalized(), modifier = Modifier.padding(2.dp))
    }
}