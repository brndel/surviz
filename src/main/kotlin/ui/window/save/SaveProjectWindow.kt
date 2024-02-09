package ui.window.save

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogWindow
import data.project.Project
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.fields.DirectoryPickerField
import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.path.extension
import kotlin.io.path.pathString

@Composable
fun SaveProjectWindow(
    onCloseRequest: () -> Unit,
    onFilePicked: (Path) -> Unit
) {
    var directory: String by remember { mutableStateOf(Project.defaultSaveDirectory) }

    var filename: String by remember { mutableStateOf(Project.DEFAULT_FILE_NAME) }

    val path by derivedStateOf {
        var p = Path(directory, filename)
        if (p.extension == "") {
            p = Path(p.pathString.removeSuffix(".") + ".svz")
        }

        p
    }

    DialogWindow(
        onCloseRequest = onCloseRequest,
        icon = rememberVectorPainter(Icons.Default.Save),
        title = LocalLanguage.current.getString(Labels.ACTION_SAVE_AS)
    ) {
        Column(
            Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DirectoryPickerField(directory, { directory = it }, label = { Label(Labels.ACTION_SAVE_AS_DIRECTORY) })

            OutlinedTextField(filename, { filename = it }, label = { Label(Labels.ACTION_SAVE_AS_PROJECT_NAME) })
            Text(path.pathString)

            Button({
                onCloseRequest()
                onFilePicked(path)
            }) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Label(Labels.ACTION_SAVE)
                    Icon(Icons.Default.Save, contentDescription = null)
                }
            }
        }
    }
}