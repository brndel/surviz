package ui.fields

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindow
import ui.Label
import ui.Labels

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
        if (icon != null) {
            Icon(painterResource(icon), null)
        }
    }

    if (dialogOpen) {
        IconFieldDialog(icon, onIconChange) { dialogOpen = false }
    }
}

@Composable
private fun IconFieldDialog(currentIcon: String?, onIconChange: (String?) -> Unit, onDismissRequest: () -> Unit) {
    var selectedIcon by remember { mutableStateOf(currentIcon) }

    DialogWindow(onCloseRequest = onDismissRequest) {
        Column(Modifier.fillMaxSize()) {
            LazyVerticalGrid(
                columns = GridCells.FixedSize(64.dp),
                modifier = Modifier.weight(1F),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(
                    listOf(
                        "icons/Icon_Bike.svg",
                        "icons/Icon_BS.svg",
                        "icons/Icon_CS.svg",
                        "icons/Icon_EScooter.svg"
                    )
                ) { icon ->
                    IconFieldDialogButton(icon, selectedIcon) {
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
private fun IconFieldDialogButton(icon: String, selectedIcon: String?, onClick: () -> Unit) {
    val selected = icon == selectedIcon
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(4.dp),
        border = if (selected) BorderStroke(2.dp, MaterialTheme.colors.primary) else null,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(painterResource(icon), null)
    }
}