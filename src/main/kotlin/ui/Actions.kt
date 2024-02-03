package ui

import GlobalCallbacks
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut

sealed class AppBarAction(
    val label: String,
    val shortcut: KeyShortcut? = null
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

data object SaveAction : AppBarAction(Labels.ACTION_SAVE, KeyShortcut(Key.S, ctrl = true)) {
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