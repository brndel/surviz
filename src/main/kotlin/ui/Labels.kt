package ui

import androidx.compose.runtime.compositionLocalOf

object Labels {
    const val SURVIZ = "SurViz"
    const val LOAD_LAST_PROJECT = "LAST_PROJECT"
    const val NEW_PROJECT = "NEW_PROJECT"
    const val LOAD_PROJECT = "LOAD_PROJECT"
    const val SETTINGS = "SETTINGS"
}

enum class Language(private val code: String, private val strings: Map<String, String>) {
    English("en", mapOf(
        Labels.SURVIZ to "SurViz",
        Labels.LOAD_LAST_PROJECT to "Load last Project",
        Labels.NEW_PROJECT to "New Project",
        Labels.LOAD_PROJECT to "Load Project",
        Labels.SETTINGS to "Settings",
    )),
    German("de", mapOf(
        Labels.SURVIZ to "SurViz",
        Labels.LOAD_LAST_PROJECT to "Letztes Projekt laden",
        Labels.NEW_PROJECT to "Neues Projekt",
        Labels.LOAD_PROJECT to "Projekt laden",
        Labels.SETTINGS to "Einstellungen",
    ));

    fun getString(label: String): String? = strings[label]

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