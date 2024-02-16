package ui

import GlobalCallbacks
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.SaveAs
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.UploadFile
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
        fileGroup
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
        CloseAction,
        OpenSettingsAction
    )
)

// Actions

data object SaveAction : AppBarAction(Labels.ACTION_SAVE, Shortcut(Key.S, ctrl = true), Icons.Default.Save) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.saveProject()
    }
}

data object SaveAsAction : AppBarAction(Labels.ACTION_SAVE_AS, Shortcut(Key.S, ctrl = true, shift = true), icon = Icons.Default.SaveAs) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.saveProjectAs()
    }
}

data object LoadDataAction : AppBarAction(Labels.ACTION_LOAD_DATA, Shortcut(Key.L, ctrl = true), icon = Icons.Default.UploadFile) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.loadData()
    }
}

data object CloseAction : AppBarAction(Labels.ACTION_CLOSE, Shortcut(Key.X, ctrl = true, shift = true), icon = Icons.Default.Close) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.closeProject()
    }
}

data object OpenSettingsAction : AppBarAction(Labels.ACTION_OPEN_SETTINGS, Shortcut(Key.P, ctrl = true, shift = true), icon = Icons.Default.Settings) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.openSettings()
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

            append(key.nativeKeyCode.toChar())
        }
    }
}