package ui

import GlobalCallbacks
import androidx.compose.runtime.Composable
import androidx.compose.ui.input.key.*

sealed class AppBarAction(
    val label: String,
    val shortcut: Shortcut? = null
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
        CloseAction,
        OpenSettingsAction
    )
)

// Actions

data object SaveAction : AppBarAction(Labels.ACTION_SAVE, Shortcut(Key.S, ctrl = true)) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.saveProject()
    }
}

data object CloseAction : AppBarAction(Labels.ACTION_CLOSE) {
    override fun onClick(globalCallbacks: GlobalCallbacks) {
        globalCallbacks.closeProject()
    }
}

data object OpenSettingsAction : AppBarAction(Labels.ACTION_OPEN_SETTINGS) {
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