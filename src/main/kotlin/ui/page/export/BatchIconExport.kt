package ui.page.export

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.project.config.BatchIconSetting
import ui.Label
import ui.Labels
import ui.LocalProject
import ui.fields.ColorField
import ui.fields.IntField
import ui.fields.MultipleDoubleField
import ui.page.singleValue.SingleValueIconCard
import ui.util.NestedSurface
import ui.window.help.UserGuide.StartScreen.settings

@Composable
fun IconBatchExportContent() {
    val config = LocalProject.current.configuration
    val batchIconSettings = config.batchIconSettings

    var width by batchIconSettings.width
    var height by batchIconSettings.height

    Column(Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Column(Modifier.padding(10.dp).fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            IntField(width, { width = it }, 1920, 0) {
                Label(Labels.WIDTH)
            }

            IntField(height, { height = it }, 1920, 0) {
                Label(Labels.HEIGHT)
            }
        }

        Button(onClick = { batchIconSettings.addSetting() }) {
            Icon(Icons.Default.Add, null)
            Label(Labels.NEW)
        }
    }
    LazyColumn(Modifier.padding(10.dp).fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        batchIconSettings.settings.forEach { setting ->
            item {
                BatchIconSettingCard(setting) { batchIconSettings.removeSetting(setting) }
            }
        }
    }
}

@Composable
fun BatchIconSettingCard(
    setting: BatchIconSetting,
    onDelete: () -> Unit,
) {
    NestedSurface {
        Row(Modifier.padding(10.dp).fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            BatchSettingCardContent(setting)

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, null)
            }
        }
    }
}

@Composable
fun RowScope.BatchSettingCardContent(setting: BatchIconSetting) {
    var prefix by setting.prefix
    var unit by setting.unit
    var color by setting.color

    Column(Modifier.weight(1F), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        OutlinedTextField(prefix, { prefix = it }, label = {
            Label(Labels.FIELD_PREFIX)
        })

        OutlinedTextField(unit, { unit = it }, label = {
            Label(Labels.FIELD_UNIT)
        })

        MultipleDoubleField(value = setting.values, { newValues ->
            setting.values.apply {
                clear()
                addAll(newValues)
            }
        })

        ColorField(color, { color = it })

        SingleValueIconCard(setting.icon)
    }
}
