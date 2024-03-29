package ui.window.help

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ui.Labels
import ui.LocalLanguage


sealed class HelpEntry(val headingLabel: String) {
    abstract fun display(scope: LazyListScope)
}

class HelpSection(
    headingLabel: String,
    val content: List<ParagraphContent>,
    val paragraphs: List<HelpParagraph>
) : HelpEntry(headingLabel) {
    override fun display(scope: LazyListScope) {
        scope.item(headingLabel) {
            HighlightedHeading(
                headingLabel,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(top = 16.dp, start = 8.dp, bottom = 4.dp)
            )

            content.display()
        }
    }
}

class HelpParagraph(headingLabel: String, val content: List<ParagraphContent>) : HelpEntry(headingLabel) {
    constructor(headingLabel: String, descriptionLabel: String) : this(
        headingLabel,
        listOf(ParagraphContent.Text(descriptionLabel))
    )

    override fun display(scope: LazyListScope) {
        scope.item(headingLabel) {
            HighlightedHeading(headingLabel, modifier = Modifier.padding(top = 8.dp, start = 4.dp))

            content.display()
        }
    }
}

sealed class ParagraphContent {
    class Text(val label: String) : ParagraphContent() {
        @Composable
        override fun Content() {
            HighlightedText(label)
        }
    }

    class Image(val path: String) : ParagraphContent() {
        @Composable
        override fun Content() {
            Image(painterResource(path), null)
        }
    }

    class LocalizedImage(val pathLabel: String) : ParagraphContent() {
        @Composable
        override fun Content() {
            Image(painterResource(LocalLanguage.current.getString(pathLabel)), null)
        }
    }

    @Composable
    abstract fun Content()
}

@Composable
fun List<ParagraphContent>.display() {
    for (element in this) {
        element.Content()
    }
}

object UserGuide {
    internal val sections = listOf(
        SurViz.section,
        StartScreen.section,
        ProjectScreen.section,
        AppBar.section,
        SingleValue.section,
        Situations.section,
        Export.section,
        Hotkeys.section
    )

    internal val entries by lazy {
        val entries = mutableListOf<HelpEntry>()

        for (section in sections) {
            entries.add(section)
            entries.addAll(section.paragraphs)
        }

        entries
    }

    object SurViz {
        val section = HelpSection(
            Labels.USER_GUIDE_SURVIZ,
            content = listOf(
                ParagraphContent.Text(Labels.USER_GUIDE_SURVIZ_DESCRIPTION)
            ),
            paragraphs = listOf()
        )
    }
    object StartScreen {
        val lastProject =
            HelpParagraph(
                Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT,
                Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION
            )
        val newProject =
            HelpParagraph(
                Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT,
                Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION
            )
        val loadProject =
            HelpParagraph(
                Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT,
                Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION
            )
        val settings =
            HelpParagraph(
                Labels.USER_GUIDE_START_SCREEN_SETTINGS,
                Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION
            )

        val section = HelpSection(
            Labels.USER_GUIDE_START_SCREEN,
            listOf(
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_START_SCREEN),
                ParagraphContent.Text(Labels.USER_GUIDE_START_SCREEN_DESCRIPTION)
            ),
            listOf(
                lastProject, newProject, loadProject, settings
            )
        )
    }

    object ProjectScreen {
        val preview = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW,
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION
        )

        val section = HelpSection(
            Labels.USER_GUIDE_PROJECT_SCREEN,
            content = listOf(
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION)
            ),
            paragraphs = listOf(preview)
        )
    }

    object AppBar {
        val save = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE,
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION
        )

        val close = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE,
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION
        )
        val new = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW,
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW_DESCRIPTION
        )


        val section = HelpSection(
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR,
            content = listOf(
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_FILE_MENU),
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION)
            ),
            paragraphs = listOf(save, close, new)
        )
    }

    object SingleValue {
        val add = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD,
            listOf(
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_SINGLE_VALUES),
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION)
            )
        )

        val scheme = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME,
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION
        )

        val levels = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS,
            listOf(
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_ICON_LEVELS),
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_ICON_LEVELS_EXAMPLE),
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS_DESCRIPTION)
            )
        )

        val setAllColumns = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS,
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS_DESCRIPTION
        )

        val section = HelpSection(
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE,
            content = listOf(
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION)
            ),
            paragraphs = listOf(add, scheme, levels, setAllColumns)
        )
    }

    object Situations {
        val edit = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT,
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION
        )

        val section = HelpSection(
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION,
            content = listOf(
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_SITUATIONS),
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION)
            ),
            paragraphs = listOf(edit)
        )
    }

    object Export {
        val scheme = HelpParagraph(
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME,
            listOf(
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_SCHEME_FILE_NAMES),
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION)
            )
        )

        val section = HelpSection(
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT,
            content = listOf(
                ParagraphContent.LocalizedImage(Labels.USER_GUIDE_PNG_EXPORT),
                ParagraphContent.Text(Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION)
            ),
            paragraphs = listOf(scheme)
        )
    }

    object Hotkeys {
        val section = HelpSection(
            Labels.USER_GUIDE_HOTKEYS,
            content = listOf(
                ParagraphContent.Text(Labels.USER_GUIDE_HOTKEYS_DESCRIPTION)
            ),
            paragraphs = listOf()
        )
    }
}