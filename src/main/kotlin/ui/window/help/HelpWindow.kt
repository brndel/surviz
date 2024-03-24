package ui.window.help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpCenter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import kotlinx.coroutines.launch
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.util.NestedSurface
import ui.window.help.UserGuide.entries


@Composable
fun HelpWindow(onCloseRequest: () -> Unit, focusedEntry: HelpEntry?) {

    Window(
        title = LocalLanguage.current.getString(Labels.SETTINGS_HELP),
        onCloseRequest = onCloseRequest,
        icon = rememberVectorPainter(Icons.Default.HelpCenter)
    ) {
        Surface(color = MaterialTheme.colors.background) {
            HelpWindowContent(focusedEntry)
        }
    }
}

const val SCROLL_OFFSET = 1

@Composable
fun HelpWindowContent(focusedEntry: HelpEntry?) {
    fun getIndex(entry: HelpEntry?): Int {
        return entries.indexOf(entry).let {
            if (it == -1) {
                0
            } else {
                it
            }
        } + SCROLL_OFFSET
    }

    val startIndex = remember {
        getIndex(focusedEntry)
    }

    val scrollScope = rememberCoroutineScope()

    val state = rememberLazyListState(startIndex)

    Row(modifier = Modifier.fillMaxSize().padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        NestedSurface {
            LazyColumn(
                modifier = Modifier.fillMaxHeight().width(256.dp)
            ) {
                items(entries) {
                    TextButton({
                        scrollScope.launch {
                            state.animateScrollToItem(getIndex(it))
                        }
                    }, modifier = Modifier.fillMaxWidth()) {
                        val textStyle = if (it is HelpSection) {
                            TextStyle(
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        } else {
                            TextStyle()
                        }.copy(textAlign = TextAlign.Center)


                        Label(it.headingLabel, style = textStyle)
                    }
                }
            }
        }
        NestedSurface {
            LazyColumn(
                state = state,
                modifier = Modifier.fillMaxHeight().weight(1F).padding(horizontal = 10.dp)
            ) {
                item {
                    HighlightedHeading(
                        Labels.USER_GUIDE,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }

                for (entry in entries) {
                    entry.display(this)
                }
            }
        }
    }
}