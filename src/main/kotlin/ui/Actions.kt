package ui

import GlobalCallbacks
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.isAltPressed
import androidx.compose.ui.input.key.isCtrlPressed
import androidx.compose.ui.input.key.isMetaPressed
import androidx.compose.ui.input.key.isShiftPressed
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode

sealed class AppBarAction(
    val label: String,
    val shortcut: Shortcut? = null,
    val icon: ImageVector? = null,
) {
    abstract fun onClick(globalCallbacks: GlobalCallbacks)
}

data class AppBarGroup(val label: String, val actions: List<AppBarAction>)

val actionsManager by lazy {
    ShortcutActionsManager(
        fileGroup,
        settingsGroup,
        helpGroup,
    )
}

class ShortcutActionsManager(vararg groups: AppBarGroup) {
    val shortcutMap = groups.flatMap { it.actions }.associateBy { it.shortcut }
    val groups = groups.toList()
}

// Groups

val fileGroup = AppBarGroup(
    Labels.APP_BAR_GROUP_FILE, listOf(
        SaveAction,
        SaveAsAction,
        LoadDataAction,
        CloseAction
    )
)

val settingsGroup = AppBarGroup(
    Labels.APP_BAR_GROUP_SETTINGS, listOf(
        OpenSettingsAction,
    )
)

val helpGroup = AppBarGroup(
    Labels.APP_BAR_GROUP_HELP, listOf(
        OpenHelpAction
    )
)



// Actions

data object SaveAction : AppBarAction(Labels.ACTION_SAVE, Shortcut(Key.S, ctrl = true), Icons.Default.Save) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.saveProject()
    }
}

data object SaveAsAction :
    AppBarAction(Labels.ACTION_SAVE_AS, Shortcut(Key.S, ctrl = true, shift = true), icon = Icons.Default.SaveAs) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.saveProjectAs()
    }
}

data object LoadDataAction :
    AppBarAction(Labels.ACTION_LOAD_DATA, Shortcut(Key.L, ctrl = true), icon = Icons.Default.UploadFile) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.loadData()
    }
}

data object CloseAction : AppBarAction(Labels.ACTION_CLOSE, icon = Icons.Default.Close) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.closeProject()
    }
}

data object OpenSettingsAction : AppBarAction(
    Labels.ACTION_OPEN_SETTINGS,
    Shortcut(Key.P, ctrl = true, shift = true),
    icon = Icons.Default.Settings
) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.openSettings()
    }
}

data object OpenHelpAction : AppBarAction(Labels.ACTION_OPEN_HELP, Shortcut(Key.F1), icon = Icons.Default.HelpCenter) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.openHelp()
    }

}

data class Shortcut(
    val key: Key, val ctrl: Boolean = false, val meta: Boolean = false,
    val alt: Boolean = false,
    val shift: Boolean = false
) {
    constructor(event: KeyEvent) : this(
        event.key,
        event.isCtrlPressed,
        event.isMetaPressed,
        event.isAltPressed,
        event.isShiftPressed
    )

    @Composable
    fun toStringLocalized(): String {
        val lang = LocalLanguage.current

        return buildString {
            if (ctrl) {
                append(lang.getString(Labels.SHORTCUT_CTRL), "+")
            }

            if (meta) {
                append(lang.getString(Labels.SHORTCUT_META), "+")
            }

            if (alt) {
                append(lang.getString(Labels.SHORTCUT_ALT), "+")
            }

            if (shift) {
                append(lang.getString(Labels.SHORTCUT_SHIFT), "+")
            }

            append(keyToString(key))
        }
    }

    private fun keyToString(key: Key): String {
        return when (key) {
            Key.F1 -> "F1"
            Key.F2 -> "F2"
            Key.F3 -> "F3"
            Key.F4 -> "F4"
            Key.F5 -> "F5"
            Key.F6 -> "F6"
            Key.F7 -> "F7"
            Key.F8 -> "F8"
            Key.F9 -> "F9"
            Key.F10 -> "F10"
            Key.F11 -> "F11"
            Key.F12 -> "F12"
            else ->
                key.nativeKeyCode.toChar().toString()
        }
    }
}