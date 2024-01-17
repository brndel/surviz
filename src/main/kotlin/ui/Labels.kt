package ui

import androidx.compose.runtime.compositionLocalOf

object Labels {
    const val LAST_PROJECT = "LAST_PROJECT"
    const val NEW_PROJECT = "NEW_PROJECT"
    const val LOAD_PROJECT = "LOAD_PROJECT"
    const val SETTINGS = "SETTINGS"
}

enum class Language(private val code: String, private val strings: Map<String, String>) {
    English("en", mapOf(
        Labels.LAST_PROJECT to "Last Project",
        Labels.NEW_PROJECT to "New Project",
        Labels.LOAD_PROJECT to "Load Project",
        Labels.SETTINGS to "Settings",
    )),
    German("de", mapOf(
        Labels.LAST_PROJECT to "Letztes Projekt",
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