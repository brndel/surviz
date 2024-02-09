package ui.window.settings.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import ui.Label
import ui.Labels
import ui.LocalLanguage
import ui.util.NestedSurface

@Composable
fun HelpTab() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                HelpHeading(
                    Labels.USER_GUIDE,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 10.dp)
                )
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HelpHeading(
                            Labels.USER_GUIDE_START_SCREEN,
                            style = MaterialTheme.typography.h5
                        )
                        Image(painterResource("userguide/Start_Screen.png"), null)
                        Label(Labels.USER_GUIDE_START_SCREEN_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT)
                        Label(Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT)
                        Label(Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT)
                        Label(Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_START_SCREEN_SETTINGS)
                        Label(Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION)
                    }
                }
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HelpHeading(
                            Labels.USER_GUIDE_PROJECT_SCREEN,
                            style = MaterialTheme.typography.h5
                        )

                        Label(Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION)
                        HelpHeading(Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW)
                        //TODO(ADD GIF)
                        Label(Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION)
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    "Dateimenü", style = MaterialTheme.typography.h6

                                )
                                //TODO(ADD GIF)
                                Text(
                                    "Über das Dateimenü lässt sich die gerade geöffnete Projektdatei speichern oder schließen.\n" + "Außerdem können hier die Einstellungen geöffnet werden."
                                )
                                HelpHeading("Projekt Speichern")
                                Text(
                                    "Nach dem Öffnen des Dateimenüs kann die derzeitige Projektkonfiguration, durch Klicken auf Datei speichern, gespeichert werden.\n" + "Beim erstmaligen Speichern eines Projekts muss der Speicherpfad der Datei festgelegt werden."
                                )
                                HelpHeading("Projekt schließen")
                                Text("Nach dem Öffnen des Dateimenüs kann durch Klicken auf Datei schließen, das Projekt geschlossen werden, um zum Startbildschirm zurückzukehren.")
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    "Einzelwerte Reiter",
                                    style = MaterialTheme.typography.h6,
                                )
                                //TODO(ADD GIF)
                                Text(
                                    "Im Reiter Einzelwerte können Einzelwerte konfiguriert werden."
                                )
                                HelpHeading("Einzelwert hinzufügen und konfigurieren")
                                HighlightedText(
                                    "Durch Klicken auf die Schaltfläche + Neu kann ein neuer Einzelwert hinzugefügt werden.\n" + "\n" + "Dem Einzelwert kann im Textfeld Einheit eine Einheit zugewiesen werden.\n" + "\n" + "Dem Einzelwert kann im Textfeld Spaltenschema ein Spaltenschema zugewiesen werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter \$Einzelwert Spaltenschema\$ zu finden.\n" + "\n" + "Durch Klicken der Schaltfläche unter dem Textfeld Spaltenschema kann ein Icon für den Einzelwert ausgewählt werden.\n" + "\n" + "Durch Klicken des + unter der Schaltfläche für das Icon, kann ein Iconlevel hinzugefügt werden.\n" + "\n" + "Einem Iconlevel kann ein eigenes Icon hinzugefügt werden. Außerdem kann im Textfeld neben der Schaltfläche für das Icon eine untere Schwelle festgelegt werden.\n" + "Diese gibt an, ab welchem Wert des Einzelwerts das Icon wechselt.\n" + "\n" + "Die Reihenfolge der Einzelwerte kann durch Anklicken der Schaltfläche mit den drei übereinanderliegenden Strichen, mittels Drag and Drop, angepasst werden."
                                )
                                HelpHeading("Einzelwert Spaltenschema")
                                //TODO(ADD GIF)
                                HighlightedText(
                                    "Durch Angeben eines Spaltenschemas kann SurViz aus den Spalten jeder Situation automatisch eine Liste der Spalten erstellen, die durch Aufsummieren der Werte, den Wert dieses Einzelwerts angeben.\n" + "\n" + "Das Schema besteht aus einem genau definierten \$Präfix\$ eines Spaltennamens und einem beliebigen \$Suffix\$ eines Spaltennamens, welcher mit einem * ersetzt wird.\n" + "Das Schema \$Präfix*\$ wählt alle Spalten der Ngene-Datei aus, deren Name folgendermaßen aufgebaut ist: \$Situation\$.\$PräfixSuffix\$\n" + "\n" + "Beispiel:\n" + "Es existieren die Spalten\n" + "- \$car.cost_maut\$\n" + "- \$car.cost_benzin\$\n" + "- \$car.fz\$\n" + "- \$car.fz_abgang\$\n" + "- \$car.fz_zugang\$\n" + "\n" + "zu der Situation \$car\$.\n" + "\n" + "Durch das Schema \$cost*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + "- \$car.cost_maut\$\n" + "- \$car.cost_benzin\$\n" + "\n" + "Jedoch würde in diesem Beispiel bereits das Schema \$c*\$ reichen, um die oben aufgeführten Spalten auszuwählen.\n" + "\n" + "Durch das Schema \$fz*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + "- \$car.fz\$\n" + "- \$car.fz_abgang\$\n" + "- \$car.fz_zugang\$\n" + "\n" + "Durch das Schema \$fz_*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + "- \$car.fz_abgang\$\n" + "- \$car.fz_zugang\$\n" + "\n" + "Die Spalte \$car.fz\$ wird hier nicht mehr ausgewählt, da diese nicht den suffix \$fz_\$ enthält."
                                )
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    "Situationen Reiter",
                                    style = MaterialTheme.typography.h6,
                                )
                                Text("Im Reiter Situationen können die einzelnen Situationen eines projekts konfiguriert werden.")
                                HelpHeading("Situation konfigurieren")
                                Text(
                                    "Um eine Situation zu konfigurieren, muss diese zunächst ausgewählt werden.\n" + "\n" + "Im Textfeld Name kann der Situation ein Name zugewiesen werden.\n" + "\n" + "Unter dem Textfeld Name kann eine Farbe, durch Angeben eines Hex-Codes oder durch Verändern der Farbregler, festgelegt werden.\n" + "\n" + "Unter Einzelwert Spalten kann jedem Einzelwert zugewiesen werden, welche Spalten für die Berechnung des Wertes benutzt werden. Durch Klicken auf die Schaltfläche neben dem Icon, des jeweiligen Eigenwerts, öffnet sich ein Dropdown-Menü. In diesem kann die Art der Spaltenberechnung ausgewählt werden.\n" + "\n" + "Wird Schema ausgewählt, wird die zuvor durch das Schema definierte Liste an Spalten verwendet.\n" + "Wird Null ausgewählt, ist diese Spalte immer Null.\n" + "Wird Zeitlinie ausgewählt, werden die Spalten der Zeitlinie ausgewählt.\n" + "Wird Auswählen ausgewählt, können die Spalten per Hand ausgewählt werden.\n" + "Unter Zeitlinie kann der Zeitstrahl der Situation angepasst werden. Einzelne Abschnitte des Zeitstrahl können mittels des + hinzugefügt werden. Durch Anklicken der Icon Schaltfläche kann, wie bei den Einzelwerten, jedem Abschnitt ein Icon zugewiesen werden. Neben der Icon-Schaltfläche kann die Spalte angegeben werden, die den Wert dieses Abschnitts enthält. Außerdem kann der Typ der Linie angegeben werden. Genauso wie bei den Einzelwerten kann die Reihenfolge der Abschnitte, durch Drag and Drop angepasst werden."
                                )
                            }
                        }
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    "Export Reiter",
                                    style = MaterialTheme.typography.h6,
                                )
                                Text(
                                    "Im Reiter Export kann das projekt als PNG oder HTML exportiert werden.\n" + "Zum Exportieren des Projekts kann die Größe und Skalierung der Zeitlinie angegeben werden. Außerdem kann ein Dateipfad und ein Schema für den Dateinamen angegeben werden"
                                )
                                HelpHeading("Schema für Dateinamen")
                                HighlightedText(
                                    "Im Schema für die Dateinamen der Dateien können folgende Platzhalter benutzt werden:\n" + "- \$block\$\n" + "- \$situation\$\n" + "- \$option\$\n" + "\n" + "\$block\$ wird beim Erstellen des Dateinamens mit der Nummer des Blocks ersetzt.\n" + "\n" + "\$situation\$ wird beim Erstellen des Dateinamens mit der Nummer der Situation ersetzt.\n" + "\n" + "\$option\$ wird beim Erstellen des Dateinamens mit der Nummer der Auswahloption ersetzt. Die Ersetzung von \$option\$ erfolgt jedoch nur, wenn *Alle Optionen separat exportieren* aktiviert ist."
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HelpHeading(
    label: String, style: TextStyle? = null, modifier: Modifier = Modifier
) {
    val textStyle = style
        ?: TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize) // Use MaterialTheme.typography.subtitle1.fontSize if style is null

    Text(
        LocalLanguage.current.getString(label),
        style = textStyle.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary),
        modifier = modifier
    )
}


@Composable
private fun HighlightedText(label: String) {
    val text = LocalLanguage.current.getString(label)
    val regex = "\\$(.*?)\\$".toRegex()
    val matches = regex.findAll(text)
    var currentIndex = 0
    val annotatedString = buildAnnotatedString {
        matches.forEach { matchResult ->
            val value = matchResult.groupValues[1]
            val startIndex = matchResult.range.first
            val endIndex = matchResult.range.last - 1
            append(text.substring(currentIndex, startIndex))
            withStyle(style = SpanStyle(color = MaterialTheme.colors.primary)) {
                append(value)
            }
            currentIndex = endIndex + 2 // Move index past the closing **
        }
        append(text.substring(currentIndex, text.length))
    }
    Text(annotatedString)
}
