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
                    "SurViz Benutzerhandbuch",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 10.dp)
                )
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HelpHeading("Starbildschirm", style = MaterialTheme.typography.h5)
                        Image(painterResource("userguide/Start_Screen.png"), null)
                        Text("Auf dem Startbildschirm lässt sich das letzte Projekt laden, ein neues Projekt erstellen, eine bereits angelegte Projektdatei laden oder die Einstellungen öffnen.")
                        HelpHeading("Letztes Projekt laden")
                        Text("Durch Klicken auf die Schaltfläche Letztes Projekt Laden wird automatisch das letzte bearbeitete und gespeicherte SurViz Projekt geladen.")
                        HelpHeading("Ein neues Projekt erstellen")
                        Text(
                            "Durch Klicken auf die Schaltfläche Neues Projekt öffnet sich automatisch der Explorer, damit eine Ngene-Datei ausgewählt werden kann.\n" +
                                    "Nach der Auswahl einer gültigen Datei wird automatisch ein neues Projekt aufgrund dieser Datei erstellt."
                        )
                        HelpHeading("Eine bereits angelegte Projektdatei laden")
                        Text(
                            "Durch Klicken auf die Schaltfläche Projekt laden öffnet sich automatisch der Explorer, damit eine Projektdatei ausgewählt werden kann.\n" +
                                    "Nach der Auswahl einer gültigen Datei wird automatisch das gewählte SurViz Projekt geladen."
                        )
                        HelpHeading("Einstellungen öffnen")
                        Text("Durch Klicken auf die Schaltfläche Einstellungen öffnet sich ein neues Fenster mit den Einstellungen des Programms.")
                    }
                }
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        HelpHeading(
                            "Projektbildschirm",
                            style = MaterialTheme.typography.h5
                        )

                        Text(
                            "Im Projektbildschirm lässt sich zu jeder Zeit das Dateimenü öffnen.\n" +
                                    "Außerdem kann zwischen den Reitern Einzelwerte, Situationen und Export ausgewählt werden.\n" +
                                    "Nach dem Erstellen einer neuen oder öffnen einer bereits bestehenden Projektdatei ist standardmäßig der Reiter Einzelwerte ausgewählt.\n" +
                                    "Im Projektbildschirm wird zu jeder Zeit eine Vorschau der Konfiguration einer Situation angezeigt"
                        )
                        HelpHeading("Situation eines Blocks in Vorschau anzeigen")
                        //TODO(ADD GIF)
                        Text("In jedem der Reiter kann rechts oben in den Textfeldern Block und Situation die gewünschte Situation eines Blocks angegeben werden, um diese in der Vorschau anzuzeigen.")
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                HelpHeading(
                                    "Dateimenü",
                                    style = MaterialTheme.typography.h6

                                )
                                //TODO(ADD GIF)
                                Text(
                                    "Über das Dateimenü lässt sich die gerade geöffnete Projektdatei speichern oder schließen.\n" +
                                            "Außerdem können hier die Einstellungen geöffnet werden."
                                )
                                HelpHeading("Projekt Speichern")
                                Text(
                                    "Nach dem Öffnen des Dateimenüs kann die derzeitige Projektkonfiguration, durch Klicken auf Datei speichern, gespeichert werden.\n" +
                                            "Beim erstmaligen Speichern eines Projekts muss der Speicherpfad der Datei festgelegt werden."
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
                                HighlightedText("Durch Klicken auf die Schaltfläche + Neu kann ein neuer Einzelwert hinzugefügt werden.\n" +
                                        "\n" +
                                        "Dem Einzelwert kann im Textfeld Einheit eine Einheit zugewiesen werden.\n" +
                                        "\n" +
                                        "Dem Einzelwert kann im Textfeld Spaltenschema ein Spaltenschema zugewiesen werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter \$Einzelwert Spaltenschema\$ zu finden.\n" +
                                        "\n" +
                                        "Durch Klicken der Schaltfläche unter dem Textfeld Spaltenschema kann ein Icon für den Einzelwert ausgewählt werden.\n" +
                                        "\n" +
                                        "Durch Klicken des + unter der Schaltfläche für das Icon, kann ein Iconlevel hinzugefügt werden.\n" +
                                        "\n" +
                                        "Einem Iconlevel kann ein eigenes Icon hinzugefügt werden. Außerdem kann im Textfeld neben der Schaltfläche für das Icon eine untere Schwelle festgelegt werden.\n" +
                                        "Diese gibt an, ab welchem Wert des Einzelwerts das Icon wechselt.\n" +
                                        "\n" +
                                        "Die Reihenfolge der Einzelwerte kann durch Anklicken der Schaltfläche mit den drei übereinanderliegenden Strichen, mittels Drag and Drop, angepasst werden.")
                                HelpHeading("Einzelwert Spaltenschema")
                                //TODO(ADD GIF)
                                HighlightedText(
                                    "Durch Angeben eines Spaltenschemas kann SurViz aus den Spalten jeder Situation automatisch eine Liste der Spalten erstellen, die durch Aufsummieren der Werte, den Wert dieses Einzelwerts angeben.\n" +
                                            "\n" +
                                            "Das Schema besteht aus einem genau definierten \$Präfix\$ eines Spaltennamens und einem beliebigen \$Suffix\$ eines Spaltennamens, welcher mit einem * ersetzt wird.\n" +
                                            "Das Schema \$Präfix*\$ wählt alle Spalten der Ngene-Datei aus, deren Name folgendermaßen aufgebaut ist: \$Situation\$.\$PräfixSuffix\$\n" +
                                            "\n" +
                                            "Beispiel:\n" +
                                            "Es existieren die Spalten\n" +
                                            "- \$car.cost_maut\$\n" +
                                            "- \$car.cost_benzin\$\n" +
                                            "- \$car.fz\$\n" +
                                            "- \$car.fz_abgang\$\n" +
                                            "- \$car.fz_zugang\$\n" +
                                            "\n" +
                                            "zu der Situation \$car\$.\n" +
                                            "\n" +
                                            "Durch das Schema \$cost*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" +
                                            "- \$car.cost_maut\$\n" +
                                            "- \$car.cost_benzin\$\n" +
                                            "\n" +
                                            "Jedoch würde in diesem Beispiel bereits das Schema \$c*\$ reichen, um die oben aufgeführten Spalten auszuwählen.\n" +
                                            "\n" +
                                            "Durch das Schema \$fz*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" +
                                            "- \$car.fz\$\n" +
                                            "- \$car.fz_abgang\$\n" +
                                            "- \$car.fz_zugang\$\n" +
                                            "\n" +
                                            "Durch das Schema \$fz_*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" +
                                            "- \$car.fz_abgang\$\n" +
                                            "- \$car.fz_zugang\$\n" +
                                            "\n" +
                                            "Die Spalte \$car.fz\$ wird hier nicht mehr ausgewählt, da diese nicht den suffix \$fz_\$ enthält."
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
                                    "Einzelwerte Reiter",
                                    style = MaterialTheme.typography.h6,
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
    text: String,
    style: TextStyle? = null,
    modifier: Modifier = Modifier
) {
    val textStyle = style ?: TextStyle(fontSize = MaterialTheme.typography.subtitle1.fontSize) // Use MaterialTheme.typography.subtitle1.fontSize if style is null

    Text(
        text,
        style = textStyle.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary),
        modifier = modifier
    )
}


@Composable
private fun HighlightedText(text: String) {
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
