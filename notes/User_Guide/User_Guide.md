# Benutzerhandbuch für SurViz

## Startbildschirm
<img src="C:\KIT Studium\5. Semester\PSE\surviz\notes\User_Guide\Start_Screen.png">

Auf dem Startbildschirm lässt sich das letzte Projekt laden, ein neues Projekt erstellen, eine bereits angelegte Projektdatei laden oder die Einstellungen öffnen.

#### Letztes Projekt laden
Durch Klicken auf die Schaltfläche *Letztes Projekt Laden* wird automatisch das letzte bearbeitete und gespeicherte SurViz Projekt geladen.

#### Ein neues Projekt erstellen
Durch Klicken auf die Schaltfläche *Neues Projekt* öffnet sich automatisch der Explorer, damit eine Ngene-Datei ausgewählt werden kann.
Nach der Auswahl einer gültigen Datei wird automatisch ein neues Projekt aufgrund dieser Datei erstellt.

#### Eine bereits angelegte Projektdatei laden
Durch Klicken auf die Schaltfläche *Projekt laden* öffnet sich automatisch der Explorer, damit eine Projektdatei ausgewählt werden kann.
Nach der Auswahl einer gültigen Datei wird automatisch das gewählte SurViz Projekt geladen.

#### Einstellungen öffnen
Durch Klicken auf die Schaltfläche Einstellungen öffnet sich ein neues Fenster mit den Einstellungen des Programms.

## Projektbildschirm
Im Projektbildschirm lässt sich zu jeder Zeit das Dateimenü öffnen.
Außerdem kann zwischen den Reitern *Einzelwerte*, *Situationen* und *Export* ausgewählt werden.
Nach dem Erstellen einer neuen oder öffnen einer bereits bestehenden Projektdatei ist standardmäßig der Reiter Einzelwerte ausgewählt.
Im Projektbildschirm wird zu jeder Zeit eine Vorschau der Konfiguration einer Situation angezeigt

#### Situation eines Blocks in Vorschau anzeigen
<img src="C:\KIT Studium\5. Semester\PSE\surviz\notes\User_Guide\Block_GIF.gif">

In jedem der Reiter kann rechts oben in den Textfeldern *Block* und *Situation* die gewünschte Situation eines Blocks angegeben werden, um diese in der Vorschau anzuzeigen.

### Dateimenü
<img src="C:\KIT Studium\5. Semester\PSE\surviz\notes\User_Guide\Datei_GIF.gif">

Über das Dateimenü lässt sich die gerade geöffnete Projektdatei speichern oder schließen.
Außerdem können hier die Einstellungen geöffnet werden.

#### Projekt Speichern
Nach dem Öffnen des Dateimenüs kann die derzeitige Projektkonfiguration, durch Klicken auf *Datei speichern*, gespeichert werden.
Beim erstmaligen Speichern eines Projekts muss der Speicherpfad der Datei festgelegt werden.

#### Projekt schließen
Nach dem Öffnen des Dateimenüs kann durch Klicken auf *Datei schließen*, das Projekt geschlossen werden, um zum Startbildschirm zurückzukehren.

### Einzelwerte Reiter
Im Reiter *Einzelwerte* können Einzelwerte konfiguriert werden.
<img src="C:\KIT Studium\5. Semester\PSE\surviz\notes\User_Guide\Single_Values_GIF.gif">

#### Einzelwert hinzufügen und konfigurieren
Durch Klicken auf die Schaltfläche *+ Neu* kann ein neuer Einzelwert hinzugefügt werden.

Dem Einzelwert kann im Textfeld *Einheit* eine Einheit zugewiesen werden.

Dem Einzelwert kann im Textfeld *Spaltenschema* ein Spaltenschema zugewiesen werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter **Einzelwert Spaltenschema** zu finden.

Durch Klicken der Schaltfläche unter dem Textfeld *Spaltenschema* kann ein Icon für den Einzelwert ausgewählt werden.

Durch Klicken des *+* unter der Schaltfläche für das Icon, kann ein Iconlevel hinzugefügt werden.

Einem Iconlevel kann ein eigenes Icon hinzugefügt werden. Außerdem kann im Textfeld neben der Schaltfläche für das Icon eine untere Schwelle festgelegt werden.
Diese gibt an, ab welchem Wert des Einzelwerts das Icon wechselt.

Die Reihenfolge der Einzelwerte kann durch Anklicken der Schaltfläche mit den drei übereinanderliegenden Strichen, mittels Drag and Drop, angepasst werden.

#### Einzelwert Spaltenschema
<img src="C:\KIT Studium\5. Semester\PSE\surviz\notes\User_Guide\Column_Scheme_GIF.gif">

Durch Angeben eines Spaltenschemas kann SurViz aus den Spalten jeder Situation automatisch eine Liste der Spalten erstellen, die durch Aufsummieren der Werte, den Wert dieses Einzelwerts angeben.

Das Schema besteht aus einem genau definierten **Präfix** eines Spaltennamens und einem beliebigen **Suffix** eines Spaltennamens, welcher mit einem * ersetzt wird.
Das Schema **Präfix*** wählt alle Spalten der Ngene-Datei aus, deren Name folgendermaßen aufgebaut ist: **Situation**.**PräfixSuffix**

Beispiel:
Es existieren die Spalten
- **car.cost_maut**
- **car.cost_benzin**
- **car.fz**
- **car.fz_abgang**
- **car.fz_zugang**

zu der Situation **car**.

Durch das Schema **cost*** werden die folgenden Spalten in der Situation **car** ausgewählt:
- **car.cost_maut**
- **car.cost_benzin**

Jedoch würde in diesem Beispiel bereits das Schema **c*** reichen, um die oben aufgeführten Spalten auszuwählen.

Durch das Schema **fz*** werden die folgenden Spalten in der Situation **car** ausgewählt:
- **car.fz**
- **car.fz_abgang**
- **car.fz_zugang**

Durch das Schema **fz_*** werden die folgenden Spalten in der Situation **car** ausgewählt:
- **car.fz_abgang**
- **car.fz_zugang**

Die Spalte **car.fz** wird hier nicht mehr ausgewählt, da diese nicht den suffix **fz_** enthält.

### Situationen Reiter
Im Reiter *Situationen* können die einzelnen Situationen eines projekts konfiguriert werden.

<img src="C:\KIT Studium\5. Semester\PSE\surviz\notes\User_Guide\Situation_GIF.gif">

#### Situation konfigurieren
Um eine Situation zu konfigurieren, muss diese zunächst ausgewählt werden.

Im Textfeld *Name* kann der Situation ein Name zugewiesen werden.

Unter dem Textfeld *Name* kann eine Farbe, durch Angeben eines Hex-Codes oder durch Verändern der Farbregler, festgelegt werden.

Unter *Einzelwert Spalten* kann jedem Einzelwert zugewiesen werden, welche Spalten für die Berechnung des Wertes benutzt werden. Durch Klicken auf die Schaltfläche neben dem Icon, des jeweiligen Eigenwerts, öffnet sich ein Dropdown-Menü. In diesem kann die Art der Spaltenberechnung ausgewählt werden.

- Wird *Schema* ausgewählt, wird die zuvor durch das Schema definierte Liste an Spalten verwendet.
- Wird *Null* ausgewählt, ist diese Spalte immer Null.
- Wird *Zeitlinie* ausgewählt, werden die Spalten der Zeitlinie ausgewählt.
- Wird *Auswählen* ausgewählt, können die Spalten per Hand ausgewählt werden.

Unter Zeitlinie kann der Zeitstrahl der Situation angepasst werden. Einzelne Abschnitte des Zeitstrahl können mittels des *+* hinzugefügt werden. Durch Anklicken der Icon Schaltfläche kann, wie bei den Einzelwerten, jedem Abschnitt ein Icon zugewiesen werden. Neben der Icon-Schaltfläche kann die Spalte angegeben werden, die den Wert dieses Abschnitts enthält. Außerdem kann der Typ der Linie angegeben werden. Genauso wie bei den Einzelwerten kann die Reihenfolge der Abschnitte, durch Drag and Drop angepasst werden.

### Export Reiter
Im Reiter *Export* kann das projekt als PNG oder HTML exportiert werden.
Zum Exportieren des Projekts kann die Größe und Skalierung der Zeitlinie angegeben werden. Außerdem kann ein Dateipfad und ein Schema für den Dateinamen angegeben werden

<img src="C:\KIT Studium\5. Semester\PSE\surviz\notes\User_Guide\Export_GIF.gif">

#### Schema für Dateinamen
Im Schema für die Dateinamen der Dateien können folgende Platzhalter benutzt werden:
- $block$
- $situation$
- $option$

$block$ wird beim Erstellen des Dateinamens mit der Nummer des Blocks ersetzt.

$situation$ wird beim Erstellen des Dateinamens mit der Nummer der Situation ersetzt.

$option$ wird beim Erstellen des Dateinamens mit der Nummer der Auswahloption ersetzt. Die Ersetzung von $option$ erfolgt jedoch nur, wenn *Alle Optionen separat exportieren* aktiviert ist.

