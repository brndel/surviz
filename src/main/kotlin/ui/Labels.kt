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
}

enum class Language(private val code: String, private val strings: Map<String, String>) {
    English("en", mapOf(
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
    )),
    German("de", mapOf(
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
    ));

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