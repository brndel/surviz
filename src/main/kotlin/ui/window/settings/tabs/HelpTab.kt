package ui.window.settings.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
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
                Text(
                    "SurViz Benutzerhandbuch",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(top = 10.dp),
                    fontWeight = FontWeight.Bold
                )
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            "Startbildschirm",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )
                        Image(painterResource("userguide/Start_Screen.png"), null)
                        Text("Auf dem Startbildschirm lässt sich das letzte Projekt laden, ein neues Projekt erstellen, eine bereits angelegte Projektdatei laden oder die Einstellungen öffnen.")
                        Text("Letztes Projekt laden", fontWeight = FontWeight.Bold)
                        Text("Durch Klicken auf die Schaltfläche Letztes Projekt Laden wird automatisch das letzte bearbeitete und gespeicherte SurViz Projekt geladen.")
                        Text("Ein neues Projekt erstellen", fontWeight = FontWeight.Bold)
                        Text(
                            "Durch Klicken auf die Schaltfläche Neues Projekt öffnet sich automatisch der Explorer, damit eine Ngene-Datei ausgewählt werden kann.\n" +
                                    "Nach der Auswahl einer gültigen Datei wird automatisch ein neues Projekt aufgrund dieser Datei erstellt."
                        )
                        Text(
                            "Eine bereits angelegte Projektdatei laden",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Durch Klicken auf die Schaltfläche Projekt laden öffnet sich automatisch der Explorer, damit eine Projektdatei ausgewählt werden kann.\n" +
                                    "Nach der Auswahl einer gültigen Datei wird automatisch das gewählte SurViz Projekt geladen."
                        )
                        Text("Einstellungen öffnen", fontWeight = FontWeight.Bold)
                        Text("Durch Klicken auf die Schaltfläche Einstellungen öffnet sich ein neues Fenster mit den Einstellungen des Programms.")
                    }
                }
                NestedSurface(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            "Projektbildschirm",
                            style = MaterialTheme.typography.h5,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            "Im Projektbildschirm lässt sich zu jeder Zeit das Dateimenü öffnen.\n" +
                                    "Außerdem kann zwischen den Reitern Einzelwerte, Situationen und Export ausgewählt werden.\n" +
                                    "Nach dem Erstellen einer neuen oder öffnen einer bereits bestehenden Projektdatei ist standardmäßig der Reiter Einzelwerte ausgewählt.\n" +
                                    "Im Projektbildschirm wird zu jeder Zeit eine Vorschau der Konfiguration einer Situation angezeigt"
                        )
                        Text(
                            "Situation eines Blocks in Vorschau anzeigen",
                            fontWeight = FontWeight.Bold
                        )
                        //TODO(ADD GIF)
                        Text("In jedem der Reiter kann rechts oben in den Textfeldern Block und Situation die gewünschte Situation eines Blocks angegeben werden, um diese in der Vorschau anzuzeigen.")
                        NestedSurface(
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(10.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Text(
                                    "Dateimenü",
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                                //TODO(ADD GIF)
                                Text(
                                    "Über das Dateimenü lässt sich die gerade geöffnete Projektdatei speichern oder schließen.\n" +
                                            "Außerdem können hier die Einstellungen geöffnet werden."
                                )
                                Text("Projekt Speichern", fontWeight = FontWeight.Bold)
                                Text(
                                    "Nach dem Öffnen des Dateimenüs kann die derzeitige Projektkonfiguration, durch Klicken auf Datei speichern, gespeichert werden.\n" +
                                            "Beim erstmaligen Speichern eines Projekts muss der Speicherpfad der Datei festgelegt werden."
                                )
                                Text("Projekt schließen", fontWeight = FontWeight.Bold)
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
                                Text(
                                    "Einzelwerte Reiter",
                                    style = MaterialTheme.typography.h6,
                                    fontWeight = FontWeight.Bold
                                )
                                //TODO(ADD GIF)
                                Text(
                                    "Im Reiter Einzelwerte können Einzelwerte konfiguriert werden."
                                )
                                Text(
                                    "Einzelwert hinzufügen und konfigurieren",
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    "Durch Klicken auf die Schaltfläche + Neu kann ein neuer Einzelwert hinzugefügt werden.\n" +
                                            "\n" +
                                            "Dem Einzelwert kann im Textfeld Einheit eine Einheit zugewiesen werden.\n" +
                                            "\n" +
                                            "Dem Einzelwert kann im Textfeld Spaltenschema ein Spaltenschema zugewiesen werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter Einzelwert Spaltenschema zu finden.\n" +
                                            "\n" +
                                            "Durch Klicken der Schaltfläche unter dem Textfeld Spaltenschema kann ein Icon für den Einzelwert ausgewählt werden.\n" +
                                            "\n" +
                                            "Durch Klicken des + unter der Schaltfläche für das Icon, kann ein Iconlevel hinzugefügt werden.\n" +
                                            "\n" +
                                            "Einem Iconlevel kann ein eigenes Icon hinzugefügt werden. Außerdem kann im Textfeld neben der Schaltfläche für das Icon eine untere Schwelle festgelegt werden.\n" +
                                            "Diese gibt an, ab welchem Wert des Einzelwerts das Icon wechselt.\n" +
                                            "\n" +
                                            "Die Reihenfolge der Einzelwerte kann durch Anklicken der Schaltfläche mit den drei übereinanderliegenden Strichen, mittels Drag and Drop, angepasst werden."
                                )
                                Text("Einzelwert Spaltenschema", fontWeight = FontWeight.Bold)
                                //TODO(ADD GIF)

                                val singleValueDescription = buildAnnotatedString {
                                    appendLine("Durch Angeben eines Spaltenschemas kann SurViz aus den Spalten jeder Situation automatisch eine Liste der Spalten erstellen, die durch Aufsummieren der Werte, den Wert dieses Einzelwerts angeben.")
                                    appendLine("\nDas Schema besteht aus einem genau definierten ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("Präfix")
                                    }
                                    append(" eines Spaltennamens und einem beliebigen ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("Suffix")
                                    }
                                    append(", welcher mit einem * ersetzt wird.\nDas Schema ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("Präfix*")
                                    }
                                    append(" wählt alle Spalten der Ngene-Datei aus, deren Name folgendermaßen aufgebaut ist: ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("Situation.PräfixSuffix")
                                    }

                                    appendLine("\n\nBeispiel:")
                                    appendLine("Es existieren die Spalten")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        appendLine("- car.cost_maut")
                                        appendLine("- car.cost_benzin")
                                        appendLine("- car.fz")
                                        appendLine("- car.fz_abgang")
                                        appendLine("- car.fz_zugang")
                                    }
                                    append("zu der Situation ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("car")
                                    }
                                    appendLine(".")

                                    appendLine("\nDurch das Schema ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("cost*")
                                    }
                                    appendLine(" werden die folgenden Spalten in der Situation ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("car")
                                    }
                                    appendLine(" ausgewählt:")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        appendLine("- car.cost_maut")
                                        appendLine("- car.cost_benzin")
                                    }

                                    appendLine("\nJedoch würde in diesem Beispiel bereits das Schema ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        appendLine("c*")
                                    }
                                    appendLine(" reichen, um die oben aufgeführten Spalten auszuwählen.")

                                    appendLine("\nDurch das Schema ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("fz*")
                                    }
                                    appendLine(" werden die folgenden Spalten in der Situation ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("car")
                                    }
                                    appendLine(" ausgewählt:")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        appendLine("- car.fz")
                                        appendLine("- car.fz_abgang")
                                        appendLine("- car.fz_zugang")
                                    }

                                    appendLine("\nDurch das Schema ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("fz_*")
                                    }
                                    appendLine(" werden die folgenden Spalten in der Situation ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("car")
                                    }
                                    appendLine(" ausgewählt:")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        appendLine("- car.fz_abgang")
                                        appendLine("- car.fz_zugang")
                                    }

                                    append("Die Spalte ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("car.fz")
                                    }
                                    appendLine(" wird hier nicht mehr ausgewählt, da diese nicht den Suffix ")
                                    withStyle(style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)) {
                                        append("fz_")
                                    }
                                    append(" enthält.")
                                }

                                Text(text = singleValueDescription)


                            }
                        }
                    }
                }
            }
        }
    }
}