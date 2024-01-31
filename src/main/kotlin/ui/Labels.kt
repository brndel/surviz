package ui

import androidx.compose.runtime.compositionLocalOf

object Labels {
    const val SURVIZ = "SurViz"
    const val LOAD_LAST_PROJECT = "LAST_PROJECT"
    const val NEW_PROJECT = "NEW_PROJECT"
    const val LOAD_PROJECT = "LOAD_PROJECT"
    const val SETTINGS = "SETTINGS"

    const val PAGE_SINGLE_VALUE = "PAGE_SINGLE_VALUE"
    const val PAGE_SITUATION = "PAGE_SITUATION"
    const val PAGE_EXPORT = "PAGE_EXPORT"
    const val NEW = "NEW"
    const val SELECT = "SELECT"

    const val FIELD_UNIT = "FIELD_UNIT"
    const val FIELD_COLUMN_SCHEME = "FIELD_COLUMN_SCHEME"

    const val BLOCK = "BLOCK"
    const val SITUATION = "SITUATION"
    const val COLUMNS = "COLUMNS"
    const val TIMELINE = "TIMELINE"

    const val SCHEME_COLUMN_NAME = "SCHEME_COLUMN_NAME"
    const val SCHEME_COLUMN_DESC = "SCHEME_COLUMN_DESC"

    const val ZERO_COLUMN_NAME = "ZERO_COLUMN_NAME"
    const val ZERO_COLUMN_DESC = "ZERO_COLUMN_DESC"

    const val TIMELINE_COLUMN_NAME = "TIMELINE_COLUMN_NAME"
    const val TIMELINE_COLUMN_DESC = "TIMELINE_COLUMN_DESC"

    const val SELECT_COLUMN_NAME = "SELECT_COLUMN_NAME"
    const val SELECT_COLUMN_DESC = "SELECT_COLUMN_DESC"
}

enum class Language(private val code: String, private val strings: Map<String, String>) {
    English(
        "en", mapOf(
            Labels.SURVIZ to "SurViz",
            Labels.LOAD_LAST_PROJECT to "Load last Project",
            Labels.NEW_PROJECT to "New Project",
            Labels.LOAD_PROJECT to "Load Project",
            Labels.SETTINGS to "Settings",

            Labels.PAGE_SINGLE_VALUE to "Single Values",
            Labels.PAGE_SITUATION to "Situations",
            Labels.PAGE_EXPORT to "Export",

            Labels.NEW to "New",
            Labels.SELECT to "Select",

            Labels.FIELD_UNIT to "Unit",
            Labels.FIELD_COLUMN_SCHEME to "Column scheme",

            Labels.BLOCK to "Block",
            Labels.SITUATION to "Situation",
            Labels.COLUMNS to "Columns",
            Labels.TIMELINE to "Timeline",

            Labels.SCHEME_COLUMN_NAME to "Scheme",
            Labels.SCHEME_COLUMN_DESC to "Select columns by scheme defined in the SingleValue",

            Labels.ZERO_COLUMN_NAME to "Zero",
            Labels.ZERO_COLUMN_DESC to "Use 0 for this column",

            Labels.TIMELINE_COLUMN_NAME to "Timeline",
            Labels.TIMELINE_COLUMN_DESC to "Take columns of the timeline of this option",

            Labels.SELECT_COLUMN_NAME to "Select",
            Labels.SELECT_COLUMN_DESC to "Select columns by hand",
        )
    ),
    German(
        "de", mapOf(
            Labels.SURVIZ to "SurViz",
            Labels.LOAD_LAST_PROJECT to "Letztes Projekt laden",
            Labels.NEW_PROJECT to "Neues Projekt",
            Labels.LOAD_PROJECT to "Projekt laden",
            Labels.SETTINGS to "Einstellungen",

            Labels.PAGE_SINGLE_VALUE to "Einzelwerte",
            Labels.PAGE_SITUATION to "Situationen",
            Labels.PAGE_EXPORT to "Exportieren",

            Labels.NEW to "Neu",
            Labels.SELECT to "Auswählen",

            Labels.FIELD_UNIT to "Einheit",
            Labels.FIELD_COLUMN_SCHEME to "Spaltenschema",

            Labels.BLOCK to "Block",
            Labels.SITUATION to "Situation",
            Labels.COLUMNS to "Spalten",
            Labels.TIMELINE to "Zeitlinie",

            Labels.SCHEME_COLUMN_NAME to "Schema",
            Labels.SCHEME_COLUMN_DESC to "Wählt die Spalten automatisch mit dem definierten Schema aus",

            Labels.ZERO_COLUMN_NAME to "Null",
            Labels.ZERO_COLUMN_DESC to "Diese Spalte ist immer 0",

            Labels.TIMELINE_COLUMN_NAME to "Zeitlinie",
            Labels.TIMELINE_COLUMN_DESC to "Nimmt die Spalten aus der Zeitlinie",

            Labels.SELECT_COLUMN_NAME to "Auswählen",
            Labels.SELECT_COLUMN_DESC to "Wähle die Spalten von Hand aus",
        )
    );

    fun getString(label: String): String = strings[label] ?: "<MISSING LABEL '$label'>"

    companion object {
        fun fromCode(code: String): Language {
            for (lang in entries) {
                if (code.startsWith(lang.code)) {
                    return lang
                }
            }
            return English
        }
    }
}

val LocalLanguage = compositionLocalOf { Language.English }