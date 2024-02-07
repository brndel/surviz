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
    const val OK = "OK"

    const val FIELD_UNIT = "FIELD_UNIT"
    const val FIELD_COLUMN_SCHEME = "FIELD_COLUMN_SCHEME"

    const val BLOCK = "BLOCK"
    const val SITUATION = "SITUATION"
    const val OPTION = "OPTION"
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

    const val EXPORT_SELECT_ALL_BLOCKS = "SELECT_ALL_BLOCKS"
    const val EXPORT_SELECT_ALL_SITUATIONS = "SELECT_ALL_SITUATIONS"
    const val EXPORT_SEPARATE_OPTIONS = "EXPORT_SEPARATE_OPTIONS"
    const val EXPORT_OUTPUT_PATH = "EXPORT_OUTPUT_PATH"
    const val EXPORT_FILE_NAME_SCHEME = "EXPORT_FILE_NAME_SCHEME"

    const val EXPORT_BUTTON = "EXPORT_BUTTON"

    const val IMPORT_ERROR_INVALID_FILE_TYPE = "IMPORT_ERROR_INVALID_FILE_TYPE"
    const val IMPORT_ERROR_CORRUPT_FILE = "IMPORT_ERROR_CORRUPT_FILE"

    const val ICON_SELECT_WINDOW = "ICON_SELECT_WINDOW"

    const val UNNAMED_PROJECT = "UNNAMED_PROJECT"

    const val APP_BAR_GROUP_FILE = "APP_BAR_GROUP_FILE"
    const val ACTION_SAVE = "ACTION_SAVE"
    const val ACTION_LOAD_DATA = "ACTION_LOAD_DATA"
    const val ACTION_CLOSE = "ACTION_CLOSE"
    const val ACTION_OPEN_SETTINGS = "ACTION_OPEN_SETTINGS"

    const val SHORTCUT_CTRL = "SHORTCUT_CTRL"
    const val SHORTCUT_ALT = "SHORTCUT_ALT"
    const val SHORTCUT_META = "SHORTCUT_META"
    const val SHORTCUT_SHIFT = "SHORTCUT_SHIFT"

    const val EXPORT_SUCCESS = "EXPORT_SUCCESS"
    const val EXPORT_WARNING = "EXPORT_WARNING"
    const val APPLY_FIX = "APPLY_FIX"

    const val NEEDED_WIDTH = "NEEDED_WIDTH"
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
            Labels.OK to "Ok",

            Labels.FIELD_UNIT to "Unit",
            Labels.FIELD_COLUMN_SCHEME to "Column scheme",

            Labels.BLOCK to "Block",
            Labels.SITUATION to "Situation",
            Labels.OPTION to "Option",
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

            Labels.EXPORT_SELECT_ALL_BLOCKS to "Select all blocks",
            Labels.EXPORT_SELECT_ALL_SITUATIONS to "Select all situations",
            Labels.EXPORT_SEPARATE_OPTIONS to "Export all options separately",
            Labels.EXPORT_OUTPUT_PATH to "Output path",
            Labels.EXPORT_FILE_NAME_SCHEME to "File name scheme",

            Labels.EXPORT_BUTTON to "Export",

            Labels.ICON_SELECT_WINDOW to "Select icon",

            Labels.UNNAMED_PROJECT to "Untitled project",

            Labels.APP_BAR_GROUP_FILE to "File",
            Labels.ACTION_SAVE to "Save file",
            Labels.ACTION_LOAD_DATA to "Load new simulation data",
            Labels.ACTION_CLOSE to "Close file",
            Labels.ACTION_OPEN_SETTINGS to "Open Settings",

            Labels.SHORTCUT_CTRL to "Ctrl",
            Labels.SHORTCUT_ALT to "Alt",
            Labels.SHORTCUT_META to "Meta",
            Labels.SHORTCUT_SHIFT to "Shift",

            Labels.EXPORT_SUCCESS to "Exported successfully",
            Labels.EXPORT_WARNING to "Problems have occurred while exporting",
            Labels.APPLY_FIX to "Apply fix",

            Labels.NEEDED_WIDTH to "needed width",
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
            Labels.OK to "Ok",

            Labels.FIELD_UNIT to "Einheit",
            Labels.FIELD_COLUMN_SCHEME to "Spaltenschema",

            Labels.BLOCK to "Block",
            Labels.SITUATION to "Situation",
            Labels.OPTION to "Option",
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

            Labels.EXPORT_SELECT_ALL_BLOCKS to "Alle Blöcke auswählen",
            Labels.EXPORT_SELECT_ALL_SITUATIONS to "Alle Situationen auswählen",
            Labels.EXPORT_SEPARATE_OPTIONS to "Alle Optionen seperat exportieren",
            Labels.EXPORT_OUTPUT_PATH to "Speicherpfad",
            Labels.EXPORT_FILE_NAME_SCHEME to "Schema für Dateinamen",

            Labels.EXPORT_BUTTON to "Exportieren",

            Labels.ICON_SELECT_WINDOW to "Icon auswählen",

            Labels.UNNAMED_PROJECT to "Unbenanntes Projekt",

            Labels.APP_BAR_GROUP_FILE to "Datei",
            Labels.ACTION_SAVE to "Datei speichern",
            Labels.ACTION_LOAD_DATA to "Neue Simulationsdatei laden",
            Labels.ACTION_CLOSE to "Datei schließen",
            Labels.ACTION_OPEN_SETTINGS to "Einstellungen öffnen",

            Labels.SHORTCUT_CTRL to "Strg",
            Labels.SHORTCUT_ALT to "Alt",
            Labels.SHORTCUT_META to "Meta",
            Labels.SHORTCUT_SHIFT to "Umschalt",

            Labels.EXPORT_SUCCESS to "Exportieren erfolgreich",
            Labels.EXPORT_WARNING to "Während des exportierens sind Fehler aufgetreten",
            Labels.APPLY_FIX to "Problem beheben",

            Labels.NEEDED_WIDTH to "benötigte Breite",
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