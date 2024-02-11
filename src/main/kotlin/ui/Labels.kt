package ui

import androidx.compose.runtime.compositionLocalOf

object Labels {
    const val SURVIZ = "SurViz"
    const val LOAD_LAST_PROJECT = "LAST_PROJECT"
    const val NEW_PROJECT = "NEW_PROJECT"
    const val LOAD_PROJECT = "LOAD_PROJECT"
    const val SETTINGS = "SETTINGS"

    const val PAGE_SINGLE_VALUE = "PAGE_SINGLE_VALUE"
    const val PAGE_SITUATION = "PAGE_SITUATION"
    const val PAGE_EXPORT = "PAGE_EXPORT"
    const val NEW = "NEW"
    const val SELECT = "SELECT"
    const val OK = "OK"

    const val FIELD_UNIT = "FIELD_UNIT"
    const val FIELD_COLUMN_SCHEME = "FIELD_COLUMN_SCHEME"
    const val SCHEME_NO_RESULT_FOUND = "SCHEME_NO_RESULT_FOUND"

    const val FIELD_LINE_TYPE = "FIELD_LINE_TYPE"

    const val BLOCK = "BLOCK"
    const val SITUATION = "SITUATION"
    const val OPTION = "OPTION"
    const val COLUMNS = "COLUMNS"
    const val COLUMN = "COLUMN"
    const val TIMELINE = "TIMELINE"

    const val SCHEME_COLUMN_NAME = "SCHEME_COLUMN_NAME"
    const val SCHEME_COLUMN_DESC = "SCHEME_COLUMN_DESC"

    const val ZERO_COLUMN_NAME = "ZERO_COLUMN_NAME"
    const val ZERO_COLUMN_DESC = "ZERO_COLUMN_DESC"

    const val TIMELINE_COLUMN_NAME = "TIMELINE_COLUMN_NAME"
    const val TIMELINE_COLUMN_DESC = "TIMELINE_COLUMN_DESC"

    const val SELECT_COLUMN_NAME = "SELECT_COLUMN_NAME"
    const val SELECT_COLUMN_DESC = "SELECT_COLUMN_DESC"

    const val EXPORTER = "EXPORTER"

    const val EXPORT_SELECT_ALL_BLOCKS = "SELECT_ALL_BLOCKS"
    const val EXPORT_SELECT_ALL_SITUATIONS = "SELECT_ALL_SITUATIONS"
    const val EXPORT_SEPARATE_OPTIONS = "EXPORT_SEPARATE_OPTIONS"
    const val EXPORT_OUTPUT_PATH = "EXPORT_OUTPUT_PATH"
    const val EXPORT_FILE_NAME_SCHEME = "EXPORT_FILE_NAME_SCHEME"

    const val EXPORT_HTML_INCLUDE_VERSION = "EXPORT_HTML_INCLUDE_VERSION"
    const val EXPORT_HTML_VERSION_NUMBER = "EXPORT_HTML_VERSION_NUMBER"

    const val EXPORT_SETTINGS = "EXPORT_GENERAL_SETTINGS"
    const val EXPORT_IMAGE_SETTINGS = "EXPORT_IMAGE_SETTINGS"

    const val EXPORT_IMAGE_CONFIG_WIDTH = "EXPORT_IMAGE_CONFIG_WIDTH"
    const val EXPORT_IMAGE_CONFIG_TIMELINE_SCALING = "EXPORT_IMAGE_CONFIG_TIMELINE_SCALING"

    const val PLACEHOLDERS = "PLACEHOLDERS"

    const val EXPORT_BUTTON = "EXPORT_BUTTON"

    const val IMPORT_ERROR_INVALID_FILE_TYPE = "IMPORT_ERROR_INVALID_FILE_TYPE"
    const val IMPORT_ERROR_CORRUPT_FILE = "IMPORT_ERROR_CORRUPT_FILE"

    const val ICON_SELECT_WINDOW = "ICON_SELECT_WINDOW"

    const val UNNAMED_PROJECT = "UNNAMED_PROJECT"

    const val APP_BAR_GROUP_FILE = "APP_BAR_GROUP_FILE"
    const val ACTION_SAVE = "ACTION_SAVE"
    const val ACTION_SAVE_AS = "ACTION_SAVE_AS"
    const val ACTION_LOAD_DATA = "ACTION_LOAD_DATA"
    const val ACTION_CLOSE = "ACTION_CLOSE"
    const val ACTION_OPEN_SETTINGS = "ACTION_OPEN_SETTINGS"

    const val ACTION_SAVE_AS_DIRECTORY = "ACTION_SAVE_AS_DIRECTORY"
    const val ACTION_SAVE_AS_PROJECT_NAME = "ACTION_SAVE_AS_PROJECT_NAME"

    const val SHORTCUT_CTRL = "SHORTCUT_CTRL"
    const val SHORTCUT_ALT = "SHORTCUT_ALT"
    const val SHORTCUT_META = "SHORTCUT_META"
    const val SHORTCUT_SHIFT = "SHORTCUT_SHIFT"

    const val EXPORT_SUCCESS = "EXPORT_SUCCESS"
    const val EXPORT_WARNING = "EXPORT_WARNING"
    const val APPLY_FIX = "APPLY_FIX"

    const val NEEDED_WIDTH = "NEEDED_WIDTH"

    const val SETTINGS_GENERAL = "SETTINGS_GENERAL"
    const val SETTINGS_HELP = "SETTINGS_HELP"

    const val SITUATION_NAME = "SITUATION_NAME"
    const val SITUATION_SINGLE_VALUE_COLUMNS = "SITUATION_SINGLE_VALUE_COLUMNS"

    const val FORBIDDEN_CHARACTERS = "FORBIDDEN_CHARACTERS"

    const val USER_GUIDE = "USER_GUIDE"
    const val USER_GUIDE_START_SCREEN = "USER_GUIDE_START_SCREEN"
    const val USER_GUIDE_START_SCREEN_DESCRIPTION = "USER_GUIDE_START_SCREEN_DESCRIPTION"
    const val USER_GUIDE_START_SCREEN_LAST_PROJECT = "USER_GUIDE_START_SCREEN_LAST_PROJECT"
    const val USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION =
        "USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION"
    const val USER_GUIDE_START_SCREEN_NEW_PROJECT = "USER_GUIDE_START_SCREEN_NEW_PROJECT"
    const val USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION =
        "USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION"
    const val USER_GUIDE_START_SCREEN_LOAD_PROJECT = "USER_GUIDE_START_SCREEN_LOAD_PROJECT"
    const val USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION =
        "USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION"
    const val USER_GUIDE_START_SCREEN_SETTINGS = "USER_GUIDE_START_SCREEN_SETTINGS"
    const val USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION =
        "USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN = "USER_GUIDE_PROJECT_SCREEN"
    const val USER_GUIDE_PROJECT_SCREEN_DESCRIPTION = "USER_GUIDE_PROJECT_SCREEN_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_PREVIEW = "USER_GUIDE_PROJECT_SCREEN_PREVIEW"
    const val USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR = "USER_GUIDE_PROJECT_SCREEN_APPBAR"
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE =
        "USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE"
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE =
        "USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE"
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE = "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SITUATION = "USER_GUIDE_PROJECT_SCREEN_SITUATION"
    const val USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT = "USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT"
    const val USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_EXPORT = "USER_GUIDE_PROJECT_SCREEN_EXPORT"
    const val USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME = "USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME"
    const val USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION"

    const val SETTINGS_CHANGE_LANGUAGE = "SETTINGS_CHANGE_LANGUAGE"
    const val SETTINGS_CHANGE_DARK_MODE = "SETTINGS_CHANGE_DARK_MODE"

    const val English = "English"
    const val German = "German"

    const val PREVIEW = "PREVIEW"
}

enum class Language(private val code: String, private val strings: Map<String, String>) {
    English(
        "en", mapOf(
            Labels.SURVIZ to "SurViz",
            Labels.LOAD_LAST_PROJECT to "Load last Project",
            Labels.NEW_PROJECT to "New Project",
            Labels.LOAD_PROJECT to "Load Project",
            Labels.SETTINGS to "Settings",

            Labels.PAGE_SINGLE_VALUE to "Single Values",
            Labels.PAGE_SITUATION to "Situations",
            Labels.PAGE_EXPORT to "Export",

            Labels.NEW to "New",
            Labels.SELECT to "Select",
            Labels.OK to "Ok",

            Labels.FIELD_UNIT to "Unit",
            Labels.FIELD_COLUMN_SCHEME to "Column scheme",
            Labels.SCHEME_NO_RESULT_FOUND to "No columns found",

            Labels.FIELD_LINE_TYPE to "Line type",

            Labels.BLOCK to "Block",
            Labels.SITUATION to "Situation",
            Labels.OPTION to "Option",
            Labels.COLUMNS to "Columns",
            Labels.COLUMN to "Column",
            Labels.TIMELINE to "Timeline",

            Labels.SCHEME_COLUMN_NAME to "Scheme",
            Labels.SCHEME_COLUMN_DESC to "Select columns by scheme defined in the SingleValue",

            Labels.ZERO_COLUMN_NAME to "Zero",
            Labels.ZERO_COLUMN_DESC to "Use 0 for this column",

            Labels.TIMELINE_COLUMN_NAME to "Timeline",
            Labels.TIMELINE_COLUMN_DESC to "Take columns of the timeline of this option",

            Labels.SELECT_COLUMN_NAME to "Select",
            Labels.SELECT_COLUMN_DESC to "Select columns by hand",

            Labels.EXPORTER to "Exporter",

            Labels.EXPORT_SELECT_ALL_BLOCKS to "Select all blocks",
            Labels.EXPORT_SELECT_ALL_SITUATIONS to "Select all situations",
            Labels.EXPORT_SEPARATE_OPTIONS to "Export all options separately",
            Labels.EXPORT_OUTPUT_PATH to "Output path",
            Labels.EXPORT_FILE_NAME_SCHEME to "File name scheme",

            Labels.EXPORT_HTML_INCLUDE_VERSION to "Include a version number",
            Labels.EXPORT_HTML_VERSION_NUMBER to "Version Number",

            Labels.EXPORT_SETTINGS to "Export settings",
            Labels.EXPORT_IMAGE_SETTINGS to "General image settings",

            Labels.EXPORT_IMAGE_CONFIG_WIDTH to "Width",
            Labels.EXPORT_IMAGE_CONFIG_TIMELINE_SCALING to "Timeline scaling",

            Labels.PLACEHOLDERS to "Placeholders",

            Labels.EXPORT_BUTTON to "Export",

            Labels.ICON_SELECT_WINDOW to "Select icon",

            Labels.UNNAMED_PROJECT to "Untitled project",

            Labels.APP_BAR_GROUP_FILE to "File",
            Labels.ACTION_SAVE to "Save file",
            Labels.ACTION_SAVE_AS to "Save file as",
            Labels.ACTION_LOAD_DATA to "Load new simulation data",
            Labels.ACTION_CLOSE to "Close file",
            Labels.ACTION_OPEN_SETTINGS to "Open Settings",

            Labels.ACTION_SAVE_AS_DIRECTORY to "Choose directory",
            Labels.ACTION_SAVE_AS_PROJECT_NAME to "Project name",

            Labels.SHORTCUT_CTRL to "Ctrl",
            Labels.SHORTCUT_ALT to "Alt",
            Labels.SHORTCUT_META to "Meta",
            Labels.SHORTCUT_SHIFT to "Shift",

            Labels.EXPORT_SUCCESS to "Exported successfully",
            Labels.EXPORT_WARNING to "Problems have occurred while exporting",
            Labels.APPLY_FIX to "Apply fix",

            Labels.NEEDED_WIDTH to "needed width",

            Labels.SETTINGS_GENERAL to "General settings",
            Labels.SETTINGS_HELP to "Help",

            Labels.SITUATION_NAME to "Name",
            Labels.SITUATION_SINGLE_VALUE_COLUMNS to "Single value columns",

            Labels.FORBIDDEN_CHARACTERS to "Forbidden characters",

            Labels.USER_GUIDE to "SurViz user guide",
            Labels.USER_GUIDE_START_SCREEN to "Home screen",
            Labels.USER_GUIDE_START_SCREEN_DESCRIPTION to "On the start screen, you can load the last project, create a new project, load an existing project file, or open the settings.\n" + "\n" + "Load last project",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT to "Load last project",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION to "By clicking on the Load Last Project button, the last edited and saved SurViz project is automatically loaded.",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT to "Create a new project",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION to "By clicking on the New Project button, the Explorer opens automatically, allowing selection of an Ngene file.\n" + "After selecting a valid file, a new project is automatically created based on this file.",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT to "Load an existing project file",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION to "By clicking on the Load Project button, the Explorer opens automatically, allowing selection of a project file.\n" + "After selecting a valid file, the chosen SurViz project is automatically loaded.",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS to "Open settings",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION to "By clicking on the Settings button, a new window opens displaying the program settings.",
            Labels.USER_GUIDE_PROJECT_SCREEN to "Project screen",
            Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION to "In the project screen, the file menu can be opened at any time.\n" + "Additionally, users can select between the tabs Single Values, Situations, and Export.\n" + "After creating a new project or opening an existing project file, the Single Values tab is selected by default.\n" + "In the project screen, a preview of the configuration of a situation is always displayed",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW to "Display preview of a block's situation",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION to "In each of the tabs, in the text fields located at the top right, the desired situation of a block can be specified to display it in the preview.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR to "File menu",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION to "Through the file menu, the currently opened project file can be saved or closed.\n" + "Additionally, settings can be opened from here.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE to "Save Project",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION to "After opening the file menu, the current project configuration can be saved by clicking on 'Save File'.\n" + "When saving a project for the first time, the file path must be specified.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE to "Close Project",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION to "After opening the file menu, clicking on 'Close File' will close the project and return to the start screen.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE to "Single Values tab",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION to "In the Single Values tab, single  values can be configured.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD to "Add and configure single  value",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION to "By clicking on the + New button, a new single value can be added.\n" + "\n" + "An unit can be assigned to the single value in the Unit text field.\n" + "\n" + "A column scheme can be assigned to the single value in the Column scheme text field. Further information can be found in the user manual under \$single Value Column scheme\$.\n" + "\n" + "By clicking the button below the Column Scheme text field, an icon for the single value can be selected.\n" + "\n" + "By clicking the + below the icon button, an icon level can be added.\n" + "\n" + "An single icon can be added to an icon level. Additionally, a lower threshold can be set in the text field next to the icon button. This indicates at which value of the single value the icon changes.\n" + "\n" + "The order of single values can be adjusted by clicking the button with the three stacked lines and using drag and drop.By clicking on the + New button, a new single value can be added.\n" + "\n" + "An unit can be assigned to the single value in the Unit text field.\n" + "\n" + "A column scheme can be assigned to the single value in the Column Scheme text field. Further information can be found in the user manual under \$Single Value Column Scheme\$.\n" + "\n" + "By clicking the button below the Column Scheme text field, an icon for the single value can be selected.\n" + "\n" + "By clicking the + below the icon button, an icon level can be added.\n" + "\n" + "An single icon can be added to an icon level. Additionally, a lower threshold can be set in the text field next to the icon button. This indicates at which value of the single value the icon changes.\n" + "\n" + "The order of single values can be adjusted by clicking the button with the three stacked lines and using drag and drop.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME to "Single Value Column Scheme",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION to "By specifying a column scheme, SurViz can automatically create a list of columns from each situation's columns, which, when summed, indicate the value of this single value.\n" + "\n" + "The scheme consists of a precisely defined \$prefix\$ of a column name and an arbitrary \$suffix\$ of a column name, which is replaced with an .\n" + "The scheme \$prefix\$ selects all columns of the Ngene file whose name is structured as follows: \$Situation\$.\$PrefixSuffix\$.\n" + "\n" + "Example:\n" + "There are the columns:\n" + "\n" + "-\$car.cost_toll\$\n" + "-\$car.cost_gasoline\$\n" + "-\$car.fuel\$\n" + "-\$car.disposal\$\n" + "-\$car.access\$\n" + "for the situation \$car\$.\n" + "\n" + "By the scheme \$cost*\$, the following columns are selected in the situation \$car\$:\n" + "\n" + "-\$car.cost_toll\$\n" + "-\$car.cost_gasoline\$\n" + "However, in this example, the scheme \$c*\$ would be sufficient to select the columns listed above.\n" + "\n" + "By the scheme -\$fuel*\$, the following columns are selected in the situation \$car\$:\n" + "\n" + "-\$car.fuel\$\n" + "-\$car.disposal\$\n" + "-\$car.access\$\n" + "By the scheme \$fuel_*\$, the following columns are selected in the situation \$car\$:\n" + "\n" + "-\$car.disposal\$\n" + "-\$car.access\$\n" + "The column \$car.fuel\$ is no longer selected here because it does not contain the suffix \$fuel_\$.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION to "Situations tab",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION to "In the Situations tab, the single situations of a project can be configured.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT to "Configure situation",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION to "To configure a situation, it must first be selected.\n" + "\n" + "In the Name text field, a name can be assigned to the situation.\n" + "\n" + "Below the Name text field, a color can be set by specifying a hex code or by adjusting the color sliders.\n" + "\n" + "Under Individual Value Columns, columns can be assigned to each individual value for calculating the value. By clicking on the button next to the icon of each individual value, a dropdown menu opens. In this menu, the type of column calculation can be selected.\n" + "\n" + "If 'Scheme' is selected, the previously defined list of columns by the scheme is used.\n" + "If 'Null' is selected, this column is always zero.\n" + "If 'Timeline' is selected, the timeline columns are chosen.\n" + "If 'Select' is selected, the columns can be manually selected.\n" + "\n" + "Under Timeline, the timeline of the situation can be adjusted. Individual sections of the timeline can be added by clicking the + button. Like with the individual values, an icon can be assigned to each section by clicking the icon button. Next to the icon button, the column containing the value of this section can be specified. Additionally, the type of line can be specified. Just like with the individual values, the order of the sections can be adjusted by drag and drop.",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT to "Export tab",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION to "In the Export tab, the project can be exported as PNG or HTML. For exporting the project, the size and scaling of the timeline can be specified. Additionally, a file path and a scheme for the file name can be specified.",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME to "Scheme for file names",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION to "In the scheme for the file names, the following placeholders can be used:\n" + "\n" + "-\$block\$\n" + "-\$situation\$\n" + "-\$option\$\n" + "-\$block\$ is replaced with the number of the block when creating the file name.\n" + "\n" + "\$situation\$ is replaced with the number of the situation when creating the file name.\n" + "\n" + "\$option\$ is replaced with the number of the selection option when creating the file name. However, the replacement of \$option\$ only occurs when Export all options separately is activated.",

            Labels.SETTINGS_CHANGE_LANGUAGE to "Change language",

            Labels.English to "English",
            Labels.German to "German",

            Labels.SETTINGS_CHANGE_DARK_MODE to "Dark mode",

            Labels.PREVIEW to "Preview",
            )
    ),
    German(
        "de", mapOf(
            Labels.SURVIZ to "SurViz",
            Labels.LOAD_LAST_PROJECT to "Letztes Projekt laden",
            Labels.NEW_PROJECT to "Neues Projekt",
            Labels.LOAD_PROJECT to "Projekt laden",
            Labels.SETTINGS to "Einstellungen",

            Labels.PAGE_SINGLE_VALUE to "Einzelwerte",
            Labels.PAGE_SITUATION to "Situationen",
            Labels.PAGE_EXPORT to "Exportieren",

            Labels.NEW to "Neu",
            Labels.SELECT to "Auswählen",
            Labels.OK to "Ok",

            Labels.FIELD_UNIT to "Einheit",
            Labels.FIELD_COLUMN_SCHEME to "Spaltenschema",
            Labels.SCHEME_NO_RESULT_FOUND to "Keine Spalten gefunden",

            Labels.FIELD_LINE_TYPE to "Linientyp",

            Labels.BLOCK to "Block",
            Labels.SITUATION to "Situation",
            Labels.OPTION to "Option",
            Labels.COLUMNS to "Spalten",
            Labels.COLUMN to "Spalte",
            Labels.TIMELINE to "Zeitstrahl",

            Labels.SCHEME_COLUMN_NAME to "Schema",
            Labels.SCHEME_COLUMN_DESC to "Wählt die Spalten automatisch mit dem definierten Schema aus",

            Labels.ZERO_COLUMN_NAME to "Null",
            Labels.ZERO_COLUMN_DESC to "Diese Spalte ist immer 0",

            Labels.TIMELINE_COLUMN_NAME to "Zeitlinie",
            Labels.TIMELINE_COLUMN_DESC to "Nimmt die Spalten aus dem Zeitstrahl",

            Labels.SELECT_COLUMN_NAME to "Auswählen",
            Labels.SELECT_COLUMN_DESC to "Wähle die Spalten von Hand aus",

            Labels.EXPORTER to "Exporter",

            Labels.EXPORT_SELECT_ALL_BLOCKS to "Alle Blöcke auswählen",
            Labels.EXPORT_SELECT_ALL_SITUATIONS to "Alle Situationen auswählen",
            Labels.EXPORT_SEPARATE_OPTIONS to "Alle Optionen separat exportieren",
            Labels.EXPORT_OUTPUT_PATH to "Speicherpfad",
            Labels.EXPORT_FILE_NAME_SCHEME to "Schema für Dateinamen",

            Labels.EXPORT_HTML_INCLUDE_VERSION to "Versionsnummer hinzufügen",
            Labels.EXPORT_HTML_VERSION_NUMBER to "Version",

            Labels.EXPORT_SETTINGS to "Export Einstellungen",
            Labels.EXPORT_IMAGE_SETTINGS to "Generelle Bildeinstellungen",

            Labels.EXPORT_IMAGE_CONFIG_WIDTH to "Breite",
            Labels.EXPORT_IMAGE_CONFIG_TIMELINE_SCALING to "Skalierung des Zeitstrahls",

            Labels.PLACEHOLDERS to "Platzhalter",

            Labels.EXPORT_BUTTON to "Exportieren",

            Labels.ICON_SELECT_WINDOW to "Icon auswählen",

            Labels.UNNAMED_PROJECT to "Unbenanntes Projekt",

            Labels.APP_BAR_GROUP_FILE to "Datei",
            Labels.ACTION_SAVE to "Datei speichern",
            Labels.ACTION_SAVE_AS to "Datei speichern unter",
            Labels.ACTION_LOAD_DATA to "Neue Simulationsdatei laden",
            Labels.ACTION_CLOSE to "Datei schließen",
            Labels.ACTION_OPEN_SETTINGS to "Einstellungen öffnen",

            Labels.ACTION_SAVE_AS_DIRECTORY to "Verzeichnis auswählen",
            Labels.ACTION_SAVE_AS_PROJECT_NAME to "Projektname",

            Labels.SHORTCUT_CTRL to "Strg",
            Labels.SHORTCUT_ALT to "Alt",
            Labels.SHORTCUT_META to "Meta",
            Labels.SHORTCUT_SHIFT to "Umschalt",

            Labels.EXPORT_SUCCESS to "Exportieren erfolgreich",
            Labels.EXPORT_WARNING to "Während des exportierens sind Fehler aufgetreten",
            Labels.APPLY_FIX to "Problem beheben",

            Labels.NEEDED_WIDTH to "benötigte Breite",

            Labels.SETTINGS_GENERAL to "Generelle Einstellungen",
            Labels.SETTINGS_HELP to "Hilfe",

            Labels.SITUATION_NAME to "Name",
            Labels.SITUATION_SINGLE_VALUE_COLUMNS to "Einzelwert-Spalten",

            Labels.FORBIDDEN_CHARACTERS to "Verbotene Zeichen",

            Labels.USER_GUIDE to "SurViz Benutzerhandbuch",
            Labels.USER_GUIDE_START_SCREEN to "Startbildschirm",
            Labels.USER_GUIDE_START_SCREEN_DESCRIPTION to "Auf dem Startbildschirm lässt sich das letzte Projekt laden, ein neues Projekt erstellen, eine bereits angelegte Projektdatei laden oder die Einstellungen öffnen.\n" + "\n" + "Letztes Projekt laden",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT to "Letztes Projekt laden",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION to "Durch Klicken auf die Schaltfläche Letztes Projekt Laden wird automatisch das letzte bearbeitete und gespeicherte SurViz Projekt geladen.",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT to "Ein neues Projekt erstellen",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION to "Durch Klicken auf die Schaltfläche Neues Projekt öffnet sich automatisch der Explorer, damit eine Ngene-Datei ausgewählt werden kann.\n" + "Nach der Auswahl einer gültigen Datei wird automatisch ein neues Projekt aufgrund dieser Datei erstellt.",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT to "Eine bereits angelegte Projektdatei laden",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION to "Durch Klicken auf die Schaltfläche Projekt laden öffnet sich automatisch der Explorer, damit eine Projektdatei ausgewählt werden kann.\n" + "Nach der Auswahl einer gültigen Datei wird automatisch das gewählte SurViz Projekt geladen.",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS to "Einstellungen öffnen",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION to "Durch Klicken auf die Schaltfläche Einstellungen öffnet sich ein neues Fenster mit den Einstellungen des Programms.",
            Labels.USER_GUIDE_PROJECT_SCREEN to "Projektbildschirm",
            Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION to "Im Projektbildschirm lässt sich zu jeder Zeit das Dateimenü öffnen.\n" + "Außerdem kann zwischen den Reitern Einzelwerte, Situationen und Export ausgewählt werden.\n" + "Nach dem Erstellen einer neuen oder öffnen einer bereits bestehenden Projektdatei ist standardmäßig der Reiter Einzelwerte ausgewählt.\n" + "Im Projektbildschirm wird zu jeder Zeit eine Vorschau der Konfiguration einer Situation angezeigt",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW to "Situation eines Blocks in Vorschau anzeigen",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION to "In jedem der Reiter kann rechts oben in den Textfeldern Block und Situation die gewünschte Situation eines Blocks angegeben werden, um diese in der Vorschau anzuzeigen.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR to "Dateimenü",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION to "Über das Dateimenü lässt sich die gerade geöffnete Projektdatei speichern oder schließen.\n" + "Außerdem können hier die Einstellungen geöffnet werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE to "Projekt Speichern",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION to "Nach dem Öffnen des Dateimenüs kann die derzeitige Projektkonfiguration, durch Klicken auf Datei speichern, gespeichert werden.\n" + "Beim erstmaligen Speichern eines Projekts muss der Speicherpfad der Datei festgelegt werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE to "Projekt schließen",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION to "Nach dem Öffnen des Dateimenüs kann durch Klicken auf Datei schließen, das Projekt geschlossen werden, um zum Startbildschirm zurückzukehren.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE to "Einzelwerte Reiter",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION to "Im Reiter Einzelwerte können Einzelwerte konfiguriert werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD to "Einzelwert hinzufügen und konfigurieren",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION to "Durch Klicken auf die Schaltfläche + Neu kann ein neuer Einzelwert hinzugefügt werden.\n" + "\n" + "Dem Einzelwert kann im Textfeld Einheit eine Einheit zugewiesen werden.\n" + "\n" + "Dem Einzelwert kann im Textfeld Spaltenschema ein Spaltenschema zugewiesen werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter \$Einzelwert Spaltenschema\$ zu finden.\n" + "\n" + "Durch Klicken der Schaltfläche unter dem Textfeld Spaltenschema kann ein Icon für den Einzelwert ausgewählt werden.\n" + "\n" + "Durch Klicken des + unter der Schaltfläche für das Icon, kann ein Iconlevel hinzugefügt werden.\n" + "\n" + "Einem Iconlevel kann ein eigenes Icon hinzugefügt werden. Außerdem kann im Textfeld neben der Schaltfläche für das Icon eine untere Schwelle festgelegt werden.\n" + "Diese gibt an, ab welchem Wert des Einzelwerts das Icon wechselt.\n" + "\n" + "Die Reihenfolge der Einzelwerte kann durch Anklicken der Schaltfläche mit den drei übereinanderliegenden Strichen, mittels Drag and Drop, angepasst werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME to "Einzelwert Spaltenschema",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION to "Durch Angeben eines Spaltenschemas kann SurViz aus den Spalten jeder Situation automatisch eine Liste der Spalten erstellen, die durch Aufsummieren der Werte, den Wert dieses Einzelwerts angeben.\n" + "\n" + "Das Schema besteht aus einem genau definierten \$Präfix\$ eines Spaltennamens und einem beliebigen \$Suffix\$ eines Spaltennamens, welcher mit einem * ersetzt wird.\n" + "Das Schema \$Präfix*\$ wählt alle Spalten der Ngene-Datei aus, deren Name folgendermaßen aufgebaut ist: \$Situation\$.\$PräfixSuffix\$\n" + "\n" + "Beispiel:\n" + "Es existieren die Spalten\n" + "- \$car.cost_maut\$\n" + "- \$car.cost_benzin\$\n" + "- \$car.fz\$\n" + "- \$car.fz_abgang\$\n" + "- \$car.fz_zugang\$\n" + "\n" + "zu der Situation \$car\$.\n" + "\n" + "Durch das Schema \$cost*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + "- \$car.cost_maut\$\n" + "- \$car.cost_benzin\$\n" + "\n" + "Jedoch würde in diesem Beispiel bereits das Schema \$c*\$ reichen, um die oben aufgeführten Spalten auszuwählen.\n" + "\n" + "Durch das Schema \$fz*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + "- \$car.fz\$\n" + "- \$car.fz_abgang\$\n" + "- \$car.fz_zugang\$\n" + "\n" + "Durch das Schema \$fz_*\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + "- \$car.fz_abgang\$\n" + "- \$car.fz_zugang\$\n" + "\n" + "Die Spalte \$car.fz\$ wird hier nicht mehr ausgewählt, da diese nicht den suffix \$fz_\$ enthält.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION to "Situationen Reiter",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION to "Im Reiter Situationen können die einzelnen Situationen eines projekts konfiguriert werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT to "Situation konfigurieren",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION to "Um eine Situation zu konfigurieren, muss diese zunächst ausgewählt werden.\n" + "\n" + "Im Textfeld Name kann der Situation ein Name zugewiesen werden.\n" + "\n" + "Unter dem Textfeld Name kann eine Farbe, durch Angeben eines Hex-Codes oder durch Verändern der Farbregler, festgelegt werden.\n" + "\n" + "Unter Einzelwert Spalten kann jedem Einzelwert zugewiesen werden, welche Spalten für die Berechnung des Wertes benutzt werden. Durch Klicken auf die Schaltfläche neben dem Icon, des jeweiligen Eigenwerts, öffnet sich ein Dropdown-Menü. In diesem kann die Art der Spaltenberechnung ausgewählt werden.\n" + "\n" + "Wird Schema ausgewählt, wird die zuvor durch das Schema definierte Liste an Spalten verwendet.\n" + "Wird Null ausgewählt, ist diese Spalte immer Null.\n" + "Wird Zeitlinie ausgewählt, werden die Spalten der Zeitlinie ausgewählt.\n" + "Wird Auswählen ausgewählt, können die Spalten per Hand ausgewählt werden.\n" + "Unter Zeitlinie kann der Zeitstrahl der Situation angepasst werden. Einzelne Abschnitte des Zeitstrahl können mittels des + hinzugefügt werden. Durch Anklicken der Icon Schaltfläche kann, wie bei den Einzelwerten, jedem Abschnitt ein Icon zugewiesen werden. Neben der Icon-Schaltfläche kann die Spalte angegeben werden, die den Wert dieses Abschnitts enthält. Außerdem kann der Typ der Linie angegeben werden. Genauso wie bei den Einzelwerten kann die Reihenfolge der Abschnitte, durch Drag and Drop angepasst werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT to "Export Reiter",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION to "Im Reiter Export kann das projekt als PNG oder HTML exportiert werden.\n" + "Zum Exportieren des Projekts kann die Größe und Skalierung der Zeitlinie angegeben werden. Außerdem kann ein Dateipfad und ein Schema für den Dateinamen angegeben werden",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME to "Schema für Dateinamen",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION to "Im Schema für die Dateinamen der Dateien können folgende Platzhalter benutzt werden:\n" + "- \$block\$\n" + "- \$situation\$\n" + "- \$option\$\n" + "\n" + "\$block\$ wird beim Erstellen des Dateinamens mit der Nummer des Blocks ersetzt.\n" + "\n" + "\$situation\$ wird beim Erstellen des Dateinamens mit der Nummer der Situation ersetzt.\n" + "\n" + "\$option\$ wird beim Erstellen des Dateinamens mit der Nummer der Auswahloption ersetzt. Die Ersetzung von \$option\$ erfolgt jedoch nur, wenn *Alle Optionen separat exportieren* aktiviert ist.",

            Labels.SETTINGS_CHANGE_LANGUAGE to "Sprache ändern",

            Labels.English to "Englisch",
            Labels.German to "Deutsch",

            Labels.SETTINGS_CHANGE_DARK_MODE to "Dunkel-Modus",

            Labels.PREVIEW to "Vorschau",
            )
    );

    fun getString(label: String): String = strings[label] ?: "<MISSING LABEL '$label'>"

    fun toCode(): String {
        return code
    }

    companion object {
        fun fromCode(code: String): Language {
            for (lang in entries) {
                if (code.startsWith(lang.code)) {
                    return lang
                }
            }
            return English
        }
    }
}

val LocalLanguage = compositionLocalOf { Language.English }