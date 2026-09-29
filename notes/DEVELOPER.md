DEVELOPER GUIDE — SurViz
=========================

Kurzüberblick
-------------
SurViz ist eine Desktop-Anwendung (JetBrains Compose Desktop + Kotlin) zur Visualisierung von stated-choice Umfragedaten. Ziel dieses Dokuments: schnellen Einstieg für Entwickler:innen, die neue Features hinzufügen oder sich in die Architektur einarbeiten wollen.

Voraussetzungen (lokal)
-----------------------
- JDK 17+ (JAVA_HOME korrekt gesetzt)
- Git
- Gradle Wrapper verfügbar (./gradlew)

Schnellstart
------------
1. Projekt bauen und starten (Entwicklung):
   ./gradlew run
2. Tests:
   ./gradlew test
3. Native Distribution für das aktuelle OS erzeugen (siehe README):
   - Version in build.gradle.kts anpassen (z. B. version = "1.0.1")
   - Dann:
     ./gradlew packageDistributionForCurrentOS
   Hinweis: Native Installer müssen auf dem Ziel-Betriebssystem gebaut werden (Windows -> WiX etc.).

Repository-Layout (wichtigste Pfade)
-----------------------------------
- src/main/kotlin/data/generator — ImageGenerator, TextLayout, Zeichenlogik
- src/main/kotlin/data/project — Project, ProjectData, Konfigurationen, Serialisierung
- src/main/kotlin/data/project/data — IconStorage, Blocks, Situations
- src/main/kotlin/data/io/importer — Importer (Ngene, CSV, TabularImporter contract)
- src/main/kotlin/data/io/exporter — Exporter (PNG/HTML)
- src/main/kotlin/ui — Compose UI (Main, Pages, Fields, Preview)
- src/main/resources — assets & config (config/image_generator.properties)
- docs/, notes/ — Hilfsdokumentation & Pläne

Architektur-Highlights
----------------------
- Project (Datenwurzel): Enthält ProjectData, DataScheme, ProjectConfiguration und IconStorage. Serialisierung/Versioning wird in Project.Companion verwaltet.
- ImageGenerator: Erzeugt ImageBitmaps für Situationen, Optionen und Legenden. Viele Layout-Parameter kommen aus src/main/resources/config/image_generator.properties.
- IconStorage: Speichert interne (bundled) und user‑Icons (PNG/SVG). User‑Icons werden im Projekt persistiert (base64) und beim Laden wiederhergestellt.
- Importer/Exporter Contracts:
  - Importer/TabularImporter: implementiert Parsing, getAlternatives/getBlockCount/rowCount, valueFromString.
  - Exporter: export(project, exportConfig, onPathSelected) + getFields() für UI‑Konfiguration.
- UI: Compose-Klasse Main.kt initialisiert GlobalCallbacks, Sprache, Theme; MainScreen/Preview sind Zustands‑Owner für die Ansicht.

Wichtige Konventionen
---------------------
- KDoc für öffentliche Klassen/Methoden: kurz Zweck, Parameter, Rückgabewerte, Ausnahmen.
- Property/Key-Zugriff: ImageGenerator verwendet properties für layout-Keys — pflege keys zentral in config/image_generator.properties.
- Fehlerbehandlung: Fange spezifische Exceptions und mappe sie auf userfreundliche Meldungen (z. B. CorruptFileException, FileTypeException).

Wie man ein neues Importer-Format hinzufügt
-----------------------------------------
1. Implementiere TabularImporter oder Importer (je nach Bedarf).
2. Implementiere required methods: extensions, separator, getAlternatives, getRowCount, getBlockCount, splitLine, isValidLine, valueFromString, readFile.
3. Fügt den Importer zu ImporterVariant hinzu, damit die UI ihn erkennt.

Wie man einen neuen Exporter hinzufügt
-------------------------------------
1. Implementiere das Exporter-Interface.
2. Definiere die Konfigurationsfelder via getFields() (NamedField/FieldData).
3. Registriere den Exporter in ExporterVariant.
4. Dokumentiere exportConfig‑Keys in der Exporter‑Implementierung (KDoc).

Image-Layout / properties
-------------------------
- Alle Layout‑Konstanten liegen in src/main/resources/config/image_generator.properties.
- Wichtige Keys: situation_default_width, timeline_default_scaling, border_padding, column_padding, single_value_size, single_value_min_width, divider_color, divider_weight, timeline_weight, legend_height, timeline_icon_size, timeline_padding, timeline_y_offset usw.
- Änderung dieser Werte kann Bilder verzerren — siehe README und Preview zur Validierung.

Serialisierung & Versionsmanagement
-----------------------------------
- Project.Companion registriert Gson-Adapter und hält konstante VERSION und READABLE_VERSIONS.
- Beim Bumpen der Projektversion: neue Serialisierungs-Adapter oder Migrationspfade dokumentieren.
