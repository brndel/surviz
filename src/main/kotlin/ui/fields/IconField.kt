package ui.fields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindow
import data.project.data.IconStorage
import ui.Label
import ui.Labels
import ui.LocalIconStorage

/**
 * An input field where the user can select an icon
 *
 * @param icon the current icon of the field
 * @param onIconChange gets called when the user selects a new item
 */
@Composable
fun IconField(icon: String?, onIconChange: (String?) -> Unit) {
    var dialogOpen by remember { mutableStateOf(false) }

    Button({ dialogOpen = true }, modifier = Modifier.size(64.dp)) {
        IconStorageImage(icon)
    }

    if (dialogOpen) {
        IconFieldDialog(icon, onIconChange) { dialogOpen = false }
    }
}

@Composable
private fun IconFieldDialog(currentIcon: String?, onIconChange: (String?) -> Unit, onDismissRequest: () -> Unit) {
    var selectedIcon by remember { mutableStateOf(currentIcon) }

    val iconStorage = LocalIconStorage.current!!

    DialogWindow(onCloseRequest = onDismissRequest) {
        Column(Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(64.dp),
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    iconStorage.getLoadedIconPaths()
                ) { icon ->
                    IconFieldDialogButton(icon, selectedIcon, iconStorage) {
                        selectedIcon = icon
                    }
                }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button({}, enabled = false) {
                    Text("Import Icon - TODO")
                }

                Button({
                    onIconChange(selectedIcon)
                    onDismissRequest()
                }) {
                    Label(Labels.SELECT)
                }
            }
        }
    }
}

@Composable
private fun IconFieldDialogButton(icon: String, selectedIcon: String?, iconStorage: IconStorage, onClick: () -> Unit) {
    val selected = icon == selectedIcon
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colors.primary) else null,
        modifier = Modifier.size(64.dp)
    ) {
        IconStorageImage(icon, iconStorage)
    }
}

@Composable
fun IconStorageImage(iconPath: String?, iconStorage: IconStorage? = LocalIconStorage.current) {
    if (iconPath == null) {
        Box(Modifier.size(64.dp)) {
            Text("-", modifier = Modifier.align(Alignment.Center))
        }
        return
    }

    val image = iconStorage?.getIcon(iconPath)


    if (image == null) {
        Box(Modifier.size(64.dp)) {
            Text("-", modifier = Modifier.align(Alignment.Center))
        }
        return
    }

    Image(image, null, modifier = Modifier.size(64.dp))
}