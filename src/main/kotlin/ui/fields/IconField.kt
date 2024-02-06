package ui.fields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import data.project.data.IconStorage
import data.resources.exceptions.FileTypeException
import ui.Label
import ui.Labels
import ui.LocalIconStorage
import ui.LocalLanguage
import ui.util.ErrorDialog

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
private fun IconFieldDialog(
    currentIcon: String?,
    onIconChange: (String?) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedIcon by remember { mutableStateOf(currentIcon) }

    val iconStorage = LocalIconStorage.current!!

    val dialogTitle = LocalLanguage.current.getString(Labels.ICON_SELECT_WINDOW)

    DialogWindow(onCloseRequest = onDismissRequest, title = dialogTitle) {
        Column(Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(64.dp),
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(iconStorage.getUserIconNames()) { icon ->
                    IconFieldDialogButton(icon, selectedIcon, iconStorage) {
                        selectedIcon = icon
                    }
                }
                items(
                    iconStorage.getInternalIconNames()
                ) { icon ->
                    IconFieldDialogButton(icon, selectedIcon, iconStorage) {
                        selectedIcon = icon
                    }
                }
            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                ImportIconButton()

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
private fun ImportIconButton(modifier: Modifier = Modifier) {
    var filePickerOpen by remember { mutableStateOf(false) }
    val iconStorage = LocalIconStorage.current
    var errorLabel: String? by remember { mutableStateOf(null) }

    Button(onClick = {
        filePickerOpen = true
    }, modifier) {
        Text("Import Icon")
    }

    FilePicker(filePickerOpen) {
        filePickerOpen = false

        if (it == null || iconStorage == null) return@FilePicker

        val path = it.path

        try {
            iconStorage.storeIcon(path)
        } catch (e: FileTypeException) {
            errorLabel = Labels.IMPORT_ERROR_INVALID_FILE_TYPE
        }
    }

    ErrorDialog(errorLabel) { errorLabel = null }
}

@Composable
private fun IconFieldDialogButton(
    icon: String,
    selectedIcon: String?,
    iconStorage: IconStorage,
    onClick: () -> Unit
) {
    val selected = icon == selectedIcon
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colors.primary) else null,
        modifier = Modifier.size(64.dp)
    ) {
        IconStorageImage(icon, iconStorage = iconStorage)
    }
}

@Composable
fun IconStorageImage(
    iconPath: String?,
    modifier: Modifier = Modifier,
    iconStorage: IconStorage? = LocalIconStorage.current
) {
    if (iconPath == null) {
        Box(modifier.size(64.dp)) {
            Text("-", modifier = Modifier.align(Alignment.Center))
        }
        return
    }

    val image = iconStorage?.getIcon(iconPath)


    if (image == null) {
        Box(modifier.size(64.dp)) {
            Text("-", modifier = Modifier.align(Alignment.Center))
        }
        return
    }

    Image(image, null, modifier = modifier.size(64.dp))
}