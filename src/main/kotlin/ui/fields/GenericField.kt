package ui.fields

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import data.resources.fields.BooleanFieldData
import data.resources.fields.ColorFieldData
import data.resources.fields.FieldData
import data.resources.fields.FileSchemeFieldData
import data.resources.fields.IntFieldData
import data.resources.fields.OptionsFieldData
import data.resources.fields.StringFieldData
import ui.Label
import ui.page.export.ExportPage

/**
 * An input field that dynamically displays the matching field based on the given [FieldData].
 * This field mainly gets used by [ExportPage]
 *
 * @param field the field that gets edited
 */
@Composable
fun GenericField(field: FieldData, modifier: Modifier = Modifier) {
    when (field) {
        is BooleanFieldData -> BooleanField(
            field.value.value,
            { field.value.value = it },
            modifier
        ) { Label(field.getLabel()) }

        is ColorFieldData -> ColorField(
            field.value.value,
            { field.value.value = it },
            modifier
        ) { Label(field.getLabel()) }

        is FileSchemeFieldData -> TextField(
            field.value.value,
            { field.value.value = it },
            modifier
        ) { Row { Label(field.getLabel()); Text("THIS IS A NORMAL TEXT FIELD FOR NOW") } }

        is IntFieldData -> IntField(
            field.value.value,
            { field.value.value = it },
            field.max,
            field.min,
            modifier
        ) { Label(field.getLabel()) }

        is OptionsFieldData -> OptionsField(
            field.value.value,
            { field.value.value = it },
            field.options,
            modifier
        ) { Text(it) }

        is StringFieldData -> TextField(
            field.value.value,
            { field.value.value = it },
            modifier
        ) { Label(field.getLabel()) }
    }
}