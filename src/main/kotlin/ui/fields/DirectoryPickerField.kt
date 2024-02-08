package ui.fields

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Folder
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.darkrockstudios.libraries.mpfilepicker.DirectoryPicker

@Composable
fun DirectoryPickerField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: @Composable (() -> Unit)? = null
) {
    var directoryPickerOpen by remember { mutableStateOf(false) }

    OutlinedTextField(value, onValueChange, modifier, label = label, trailingIcon = {
        IconButton({ directoryPickerOpen = true }) {
            Icon(Icons.Default.Folder, null)
        }
    })

    DirectoryPicker(directoryPickerOpen, initialDirectory = value) {
        directoryPickerOpen = false
        if (it != null) {
            onValueChange(it)
        }
    }
}