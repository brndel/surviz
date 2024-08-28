package ui

import androidx.compose.runtime.compositionLocalOf
import ui.Labels.ENUMERATION_SIGN

object Labels {

    /*
    #########################################   BASIC LABELS   #########################################################
    */
    const val SURVIZ = "SurViz"
    const val LOAD_LAST_PROJECT = "LAST_PROJECT"
    const val NEW_PROJECT = "NEW_PROJECT"
    const val LOAD_PROJECT = "LOAD_PROJECT"
    const val SETTINGS = "SETTINGS"

    const val PAGE_SINGLE_VALUE = "PAGE_SINGLE_VALUE"
    const val PAGE_MODES = "PAGE_MODES"
    const val PAGE_IMAGE = "PAGE_IMAGE"
    const val PAGE_EXPORT = "PAGE_EXPORT"
    const val PAGE_OVERRIDE_OPTIONS = "PAGE_OVERRIDE_OPTIONS"
    const val NEW = "NEW"
    const val SELECT = "SELECT"
    const val OK = "OK"
    const val ERROR = "ERROR"
    const val CANCEL = "CANCEL"
    const val ENUMERATION_SIGN: Char = 0x25CF.toChar()
    const val PAGE_LEGEND = "LEGEND"
    const val LEGEND_ABBREVIATION = "LEGEND_ABBREVIATION"
    const val LEGEND_DESCRIPTION = "LEGEND_DESCRIPTION"

    const val ADD = "ADD"

    const val FIELD_UNIT = "FIELD_UNIT"
    const val FIELD_PREFIX = "FIELD_PREFIX"
    const val FIELD_COLUMN_SCHEME = "FIELD_COLUMN_SCHEME"
    const val SCHEME_NO_RESULT_FOUND = "SCHEME_NO_RESULT_FOUND"

    const val ADD_SINGLE_VALUE_ICON_LEVEL = "ADD_SINGLE_VALUE_ICON_LEVEL"

    const val FIELD_LINE_TYPE = "FIELD_LINE_TYPE"
    const val LINE_TYPE_SOLID = "LINE_TYPE_SOLID"
    const val LINE_TYPE_DOTTED = "LINE_TYPE_DOTTED"
    const val LINE_TYPE_DASHED = "LINE_TYPE_DASHED"

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

    const val SINGLE_VALUE_SET_ALL_COLUMNS = "SINGLE_VALUE_SET_ALL_COLUMNS"
    const val SINGLE_VALUE_FORCE_DECIMAL = "SINGLE_VALUE_FORCE_DECIMAL"
    const val SINGLE_VALUE_DIVIDER = "SINGLE_VALUE_DIVIDER"
    const val SINGLE_VALUE_DIVIDER_LENGTH = "SINGLE_VALUE_DIVIDER_LENGTH"
    const val SINGLE_VALUE_DUMMY_SWITCH = "SINGLE_VALUE_DUMMY_SWITCH"
    const val SINGLE_VALUE_DUMMY_KEY = "SINGLE_VALUE_DUMMY_KEY"
    const val SINGLE_VALUE_DUMMY_VALUE = "SINGLE_VALUE_DUMMY_VALUE"

    const val SITUATION_OVERRIDE_SCALE = "SITUATION_OVERRIDE_SCALE"

    const val LEGEND_SWITCH = "LEGEND_SWITCH"

    /*
    #########################################   EXPORT/IMPORT LABELS   #################################################
    */

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
    const val EXPORT_IMAGE_CONFIG_BACKGROUND_COLOR = "EXPORT_IMAGE_CONFIG_BACKGROUND_COLOR"
    const val EXPORT_IMAGE_CONFIG_SINGLE_VALUE_SIZE = "EXPORT_IMAGE_CONFIG_SINGLE_VALUE_SIZE"

    const val EXPORT_IMAGE_CONFIG_ALPHA = "EXPORT_IMAGE_CONFIG_ALPHA"

    const val PLACEHOLDERS = "PLACEHOLDERS"

    const val EXPORT_BUTTON = "EXPORT_BUTTON"

    const val IMPORT_ERROR_INVALID_FILE_TYPE = "IMPORT_ERROR_INVALID_FILE_TYPE"
    const val IMPORT_ERROR_CORRUPT_FILE = "IMPORT_ERROR_CORRUPT_FILE"
    const val IMPORT_ERROR_WRONG_VERSION = "IMPORT_ERROR_WRONG_VERSION"

    const val ICON_SELECT_WINDOW = "ICON_SELECT_WINDOW"

    const val UNNAMED_PROJECT = "UNNAMED_PROJECT"

    /*
    #########################################   APPBAR/ACTION LABELS   #################################################
    */

    const val APP_BAR_GROUP_FILE = "APP_BAR_GROUP_FILE"
    const val ACTION_SAVE = "ACTION_SAVE"
    const val ACTION_SAVE_AS = "ACTION_SAVE_AS"
    const val ACTION_LOAD_DATA = "ACTION_LOAD_DATA"
    const val ACTION_CLOSE = "ACTION_CLOSE"

    const val APP_BAR_GROUP_SETTINGS = "APP_BAR_GROUP_SETTINGS"
    const val APP_BAR_GROUP_HELP = "APP_BAR_GROUP_HELP"
    const val ACTION_OPEN_SETTINGS = "ACTION_OPEN_SETTINGS"
    const val ACTION_OPEN_HELP = "ACTION_OPEN_HELP"

    const val ACTION_SAVE_AS_DIRECTORY = "ACTION_SAVE_AS_DIRECTORY"
    const val ACTION_SAVE_AS_PROJECT_NAME = "ACTION_SAVE_AS_PROJECT_NAME"

    const val FILE_ALREADY_EXISTS = "FILE_ALREADY_EXISTS"

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


    /*
    #######################################   Status Message Labels   ##################################################
    */

    const val STATUS_MSG_PROJECT_SAVED = "STATUS_MSG_SAVED"
    const val STATUS_MSG_PROJECT_LOADED = "STATUS_MSG_PROJECT_LOADED"
    const val STATUS_MSG_PROJECT_CREATED = "STATUS_MSG_PROJECT_CREATED"
    const val STATUS_MSG_PROJECT_CLOSED = "STATUS_MSG_PROJECT_CLOSED"
    const val STATUS_MSG_SIMULATION_DATA_LOADED = "STATUS_MSG_DATA_LOADED"

    /*
    #########################################   USER GUIDE LABELS   ####################################################
    */

    const val USER_GUIDE = "USER_GUIDE"

    const val USER_GUIDE_SURVIZ = "USER_GUIDE_SURVIZ"
    const val USER_GUIDE_SURVIZ_DESCRIPTION = "USER_GUIDE_SURVIZ_DESCRIPTION"

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
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW =
        "USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW"
    const val USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE = "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS"
    const val USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS_DESCRIPTION =
        "USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS_DESCRIPTION"

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

    const val USER_GUIDE_HOTKEYS = "USER_GUIDE_HOTKEYS"
    const val USER_GUIDE_HOTKEYS_DESCRIPTION = "USER_GUIDE_HOTKEYS_DESCRIPTION"

    const val USER_GUIDE_PNG_START_SCREEN = "USER_GUIDE_PNG_START_SCREEN"
    const val USER_GUIDE_PNG_FILE_MENU = "USER_GUIDE_PNG_FILE_MENU"
    const val USER_GUIDE_PNG_SINGLE_VALUES = "USER_GUIDE_PNG_SINGLE_VALUES"
    const val USER_GUIDE_PNG_SITUATIONS = "USER_GUIDE_PNG_SITUATIONS"
    const val USER_GUIDE_PNG_EXPORT = "USER_GUIDE_PNG_EXPORT"
    const val USER_GUIDE_PNG_SCHEME_FILE_NAMES = "USER_GUIDE_PNG_SCHEME_FILE_NAMES"
    const val USER_GUIDE_PNG_ICON_LEVELS = "USER_GUIDE_PNG_ICON_LEVELS"
    const val USER_GUIDE_PNG_ICON_LEVELS_EXAMPLE = "USER_GUIDE_PNG_ICON_LEVELS_EXAMPLE"

    /*
    #########################################   SETTINGS LABELS   ######################################################
    */

    const val SETTINGS_CHANGE_LANGUAGE = "SETTINGS_CHANGE_LANGUAGE"
    const val SETTINGS_CHANGE_DARK_MODE = "SETTINGS_CHANGE_DARK_MODE"

    const val LANGUAGE_ENGLISH = "ENGLISH"
    const val LANGUAGE_GERMAN = "GERMAN"

    const val PREVIEW = "PREVIEW"

    const val SETTINGS_999_TITLE = "SETTINGS_999_TITLE"

    /*
    #########################################   INFO LABELS   ##########################################################
    */
    const val OPEN_HElP_INFO_BOX = "OPEN_HElP_INFO_BOX"

    const val SINGLE_VALUE_SCHEME_INFO_TITLE = "SINGLE_VALUE_SCHEME_INFO_TITLE"
    const val SINGLE_VALUE_SCHEME_INFO_DESCRIPTION = "SINGLE_VALUE_SCHEME_INFO_DESCRIPTION"

    const val SINGLE_VALUE_LEVEL_INFO_TITLE = "SINGLE_VALUE_LEVEL_INFO_TITLE"
    const val SINGLE_VALUE_LEVEL_INFO_DESCRIPTION = "SINGLE_VALUE_LEVEL_INFO_DESCRIPTION"

    const val TIMELINE_SCALING_INFO_TITLE = "TIMELINE_SCALING_INFO_TITLE"
    const val TIMELINE_SCALING_INFO_DESCRIPTION = "TIMELINE_SCALING_INFO_DESCRIPTION"

    const val SINGLE_VALUE_DECIMAL_INFO_TITLE = "SINGLE_VALUE_DECIMAL_INFO_TITLE"
    const val SINGLE_VALUE_DECIMAL_INFO_DESCRIPTION = "SINGLE_VALUE_DECIMAL_INFO_DESCRIPTION"
    const val SINGLE_VALUE_ALL_COLUMNS_INFO_TITLE = "SINGLE_VALUE_ALL_COLUMNS_INFO_TITLE"
    const val SINGLE_VALUE_ALL_COLUMNS_INFO_DESCRIPTION =
        "SINGLE_VALUE_ALL_COLUMNS_INFO_DESCRIPTION"
    const val SINGLE_VALUE_ALPHA_INFO_TITLE = "SINGLE_VALUE_ALPHA_INFO_TITLE"
    const val SINGLE_VALUE_ALPHA_INFO_DESCRIPTION = "SINGLE_VALUE_ALPHA_INFO_DESCRIPTION"
    const val SINGLE_VALUE_DIVIDER_TITLE = "SINGLE_VALUE_DIVIDER_TITLE"

    const val SINGLE_VALUE_SIZE_TITLE = "SINGLE_VALUE_SIZE_TITLE"

    const val PREVIEW_WARNING_TITLE = "PREVIEW_WARNING_TITLE"
    const val PREVIEW_WARNING_DESCRIPTION = "PREVIEW_WARNING_DESCRIPTION"
    const val PREVIEW_WARNING_FIX = "PREVIEW_WARNING_FIX"

    const val PREVIEW_999_TITLE = "PREVIEW_999_TITLE"
    const val PREVIEW_999_DESCRIPTION = "PREVIEW_999_DESCRIPTION"

    const val SINGLE_VALUE_DUMMY_INFO_TITLE = "SINGLE_VALUE_DUMMY_INFO_TITLE"
    const val SINGLE_VALUE_DUMMY_INFO_DESCRIPTION = "SINGLE_VALUE_DUMMY_INFO_DESCRIPTION"

    const val RANGED_FIELD_INFO_TITLE = "RANGED_FIELD_INFO_TITLE"
    const val RANGED_FIELD_INFO_DESCRIPTION = "RANGED_FIELD_INFO_DESCRIPTION"

    const val SITUATION_OVERRIDE_SCALE_INFO_TITLE = "SITUATION_OVERRIDE_SCALE_INFO_TITLE"

    const val PREVIEW_SITUATION_OVERRIDE_INFO_TITLE = "PREVIEW_SITUATION_OVERRIDE_INFO_TITLE"

    const val LEGEND_SWITCH_INFO_TITLE = "LEGEND_SWITCH_INFO_TITLE"
    /*
    #########################################   MISC/ERROR LABELS   ####################################################
    */

    const val IMAGE_CREATE_ERROR = "IMAGE_CREATE_ERROR"
    const val SITUATION_NOT_FOUND = "SITUATION_NOT_FOUND"
    const val BLOCK_NOT_FOUND = "BLOCK_NOT_FOUND"

    const val INVALID_SEGMENT = "INVALID_SEGMENT"

    const val IMPORT_ICON = "IMPORT_ICON"

    const val EXPORT_DIALOG_APPLY_FIX = "EXPORT_DIALOG_APPLY_FIX"
    const val EXPORT_DIALOG_OPEN_FOLDER = "EXPORT_DIALOG_OPEN_FOLDER"
    const val EXPORT_DIALOG_FIX_ALL = "EXPORT_DIALOG_FIX_ALL"

    const val OVERRIDE_DATA_NOT_FITTING = "OVERRIDE_DATA_NOT_FITTING"
    const val OVERRIDE_DATA_ANYWAYS = "OVERRIDE_DATA_ANYWAYS"

    const val CLOSE_DIALOG_TITLE = "CLOSE_DIALOG_TITLE"
    const val CLOSE_DIALOG_TEXT = "CLOSE_DIALOG_TEXT"
    const val CLOSE_DIALOG_CONFIRM = "CLOSED_DIALOG_CONFIRM"

    const val ILLEGAL_CHAR = "ILLEGAL_CHAR"

    const val LOWER_THRESHOLD = "LOWER_THRESHOLD"

    const val COLOR = "COLOR"
    const val VALUE = "VALUE"
}

enum class Language(
    private val code: String,
    val label: String,
    private val strings: Map<String, String>
) {
    English(
        "en",
        Labels.LANGUAGE_ENGLISH,
        mapOf(
            /*
            #########################################   BASIC LABELS   #################################################
             */
            Labels.SURVIZ to "SurViz",
            Labels.LOAD_LAST_PROJECT to "Load last Project",
            Labels.NEW_PROJECT to "New Project",
            Labels.LOAD_PROJECT to "Load Project",
            Labels.SETTINGS to "Settings",

            Labels.PAGE_SINGLE_VALUE to "Single Values",
            Labels.PAGE_MODES to "Modes",
            Labels.PAGE_IMAGE to "Image",
            Labels.PAGE_EXPORT to "Export",
            Labels.PAGE_OVERRIDE_OPTIONS to "Override Situations",

            Labels.NEW to "New",
            Labels.SELECT to "Select",
            Labels.OK to "Ok",
            Labels.ERROR to "Error",
            Labels.CANCEL to "Cancel",
            Labels.ADD to "Add",
            Labels.PAGE_LEGEND to "Legend",
            Labels.LEGEND_ABBREVIATION to "Abbreviation",
            Labels.LEGEND_DESCRIPTION to "Description",

            Labels.FIELD_PREFIX to "Prefix",
            Labels.FIELD_UNIT to "Unit",
            Labels.FIELD_COLUMN_SCHEME to "Column scheme",
            Labels.SCHEME_NO_RESULT_FOUND to "No columns found",

            Labels.ADD_SINGLE_VALUE_ICON_LEVEL to "Add level",

            Labels.FIELD_LINE_TYPE to "Line type",
            Labels.LINE_TYPE_SOLID to "Solid",
            Labels.LINE_TYPE_DOTTED to "Dotted",
            Labels.LINE_TYPE_DASHED to "Dashed",

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

            Labels.SINGLE_VALUE_SET_ALL_COLUMNS to " Set Single value columns for all situations",
            Labels.SINGLE_VALUE_FORCE_DECIMAL to "Force Decimal",
            Labels.SINGLE_VALUE_DIVIDER to "Add Divider",
            Labels.SINGLE_VALUE_DIVIDER_LENGTH to "Length",
            Labels.SINGLE_VALUE_DUMMY_SWITCH to "Dummy encoding",
            Labels.SINGLE_VALUE_DUMMY_KEY to "Key",
            Labels.SINGLE_VALUE_DUMMY_VALUE to "Value",

            Labels.SITUATION_OVERRIDE_SCALE to "Override scale",

            Labels.LEGEND_SWITCH to "Entry with icon",

            /*
            #########################################   EXPORT/IMPORT LABELS   #########################################
             */

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
            Labels.EXPORT_IMAGE_CONFIG_BACKGROUND_COLOR to "Background color",
            Labels.EXPORT_IMAGE_CONFIG_SINGLE_VALUE_SIZE to "Single value width",

            Labels.EXPORT_IMAGE_CONFIG_ALPHA to "Single Value alpha",

            Labels.PLACEHOLDERS to "Placeholders",

            Labels.EXPORT_BUTTON to "Export",

            Labels.IMPORT_ERROR_INVALID_FILE_TYPE to "Invalid File type",
            Labels.IMPORT_ERROR_CORRUPT_FILE to "Corrupt file",
            Labels.IMPORT_ERROR_WRONG_VERSION to "Wrong file version",

            Labels.ICON_SELECT_WINDOW to "Select icon",

            Labels.UNNAMED_PROJECT to "Untitled project",

            /*
            #########################################   APPBAR/ACTION LABELS   #########################################
            */

            Labels.APP_BAR_GROUP_FILE to "File",
            Labels.ACTION_SAVE to "Save file",
            Labels.ACTION_SAVE_AS to "Save file as",
            Labels.ACTION_LOAD_DATA to "Load new simulation data",
            Labels.ACTION_CLOSE to "Close file",

            Labels.APP_BAR_GROUP_SETTINGS to "Settings",
            Labels.ACTION_OPEN_SETTINGS to "Open Settings",
            Labels.APP_BAR_GROUP_HELP to "Help",
            Labels.ACTION_OPEN_HELP to "Open Help",

            Labels.ACTION_SAVE_AS_DIRECTORY to "Choose directory",
            Labels.ACTION_SAVE_AS_PROJECT_NAME to "Project name",

            Labels.FILE_ALREADY_EXISTS to "File already exists and will be overwritten",

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

            /*
            #######################################   Status Message Labels   ##########################################
            */

            Labels.STATUS_MSG_PROJECT_SAVED to "Project saved",
            Labels.STATUS_MSG_PROJECT_LOADED to "Project loaded",
            Labels.STATUS_MSG_PROJECT_CREATED to "Project created",
            Labels.STATUS_MSG_PROJECT_CLOSED to "Project closed",
            Labels.STATUS_MSG_SIMULATION_DATA_LOADED to "Simulation data loaded",

            /*
            #########################################   USER GUIDE LABELS   ############################################
            */

            Labels.USER_GUIDE to "SurViz user guide",

            Labels.USER_GUIDE_SURVIZ to "SurViz",
            Labels.USER_GUIDE_SURVIZ_DESCRIPTION to "SurViz is a configuration tool for creating visual stated-preference surveys in the field of transportation research. A SurViz project is based on an Ngene file generated using the software tool Ngene.",

            Labels.USER_GUIDE_START_SCREEN to "Home screen",
            Labels.USER_GUIDE_START_SCREEN_DESCRIPTION to "On the start screen, you can load the last project, create a new project, load an existing project file, or open the settings.\n",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT to "Load last project",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION to "By clicking on the \$Load Last Project\$ button, the last edited and saved SurViz project is automatically loaded.",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT to "Create a new project",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION to "By clicking on the \$New Project\$ button, the Explorer opens automatically, allowing selection of an Ngene file.\n" + "After selecting a valid file, a new project is automatically created based on this file.",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT to "Load an existing project file",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION to "By clicking on the \$Load Project\$ button, the Explorer opens automatically, allowing selection of a project file.\n" + "After selecting a valid file, the chosen SurViz project is automatically loaded.",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS to "Open settings",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION to "By clicking on the \$Settings\$ button, a new window opens displaying the program settings.",
            Labels.USER_GUIDE_PROJECT_SCREEN to "Project screen",
            Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION to "In the project screen, the file menu can be opened at any time by klicking the \$File\$ Button.\n" + "Additionally, users can select between the tabs \$Single Values\$, \$Situations\$, and \$Export\$.\n" + "After creating a new project or opening an existing project file, the \$Single Values\$ tab is selected by default.\n" + "In the project screen, a \$preview\$ of the configuration of a situation is always displayed",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW to "Display preview of a block's situation",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION to "In each of the tabs, in the text fields located at the top right, the desired \$situation\$ of a \$block\$ can be specified to display it in the preview.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR to "File menu",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION to "Through the file menu, the currently opened project file can be saved or closed.\n" + "Additionally, a new Ngene File can be imported here.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW to "Load new simulation data",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW_DESCRIPTION to "By loading a new simulation data, the old Ngene File will be overwritten and the new one will be used. If the new Data has the same structure as the old one, no settings will be lost.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE to "Save Project",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION to "After opening the file menu, the current project configuration can be saved by clicking on \$Save File\$.\n" + "When saving a project for the first time, the file path must be specified.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE to "Close Project",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION to "After opening the file menu, clicking on \$Close File\$ will close the project and return to the start screen.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE to "Single Values tab",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION to "In the \$Single Values\$ tab, single  values can be configured.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD to "Add and configure single  value",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION to "By clicking on the \$+ New\$ button, a new single value can be added.\n" + "\n" + "A unit can be assigned to the single value in the \$Unit\$ text field.\n" + "\n" + "A column scheme can be assigned to the single value in the \$Column Scheme\$ text field. Further information can be found in the user manual under \$single Value Column Scheme\$.\n" + "\n" + "By clicking the button below the Column Scheme text field, an icon for the single value can be selected.\n\n" + "The Button \$+Add level\$ adds a new icon level. the specified icon of this level will be displayed, if the single value reaches the given threshold. Find out more about icon levels in the \$Icon level\$ section of this user guide.\n\n" + "The Button \$Set Single value columns for all situations\$ can be used to set the single value columns option for all situations at the same time. Find out more in the \$Set Single value columns for all situations\$ section of this User Guide.\n\n" + "The three dashes button can be used to change the order of the single values.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME to "Single Value Column Scheme",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION to "By specifying a column scheme, SurViz can automatically create a list of columns from each situation's columns, which, when summed, indicate the value of this single value.\n" + "The scheme consists of a Regular Expression (Regex)." + "\n" + "\n" + "Example:\n" + "There are the columns:\n" + "\n" + ENUMERATION_SIGN + "\$ car.cost_toll\$\n" + ENUMERATION_SIGN + "\$ car.cost_gasoline\$\n" + ENUMERATION_SIGN + "\$ car.time\$\n" + ENUMERATION_SIGN + "\$ car.access\$\n" + ENUMERATION_SIGN + "\$ car.exit\$\n\n" + "for the situation \$car\$.\n" + "\n" + "By the scheme \$cost\$, the following columns are selected in the situation \$car\$:\n" + ENUMERATION_SIGN + "\$ car.cost_toll\$\n" + ENUMERATION_SIGN + "\$ car.cost_gasoline\$\n" + "However, in this example, the scheme \$c\$ would be sufficient to select the columns listed above.\n" + "\n" + "By the scheme \$time\$, the following columns are selected in the situation \$car\$:\n" + ENUMERATION_SIGN + "\$ car.time\$\n" + "By the scheme \$(time|access|exit)\$, the following columns are selected in the situation \$car\$:\n" + ENUMERATION_SIGN + "\$ car.time\$\n" + ENUMERATION_SIGN + "\$ car.access\$\n" + ENUMERATION_SIGN + "\$ car.exit\$\n",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS to "Set Single value columns for all situations",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS_DESCRIPTION to "With the \$Set Single value columns for all situations\$ button, the 'Single value columns' option from the Situations window can be set for all situations simultaneously. The Button only changes the Single value columns option for this Single value. \$Caution:\$ This will overwrite all previous settings in the 'Single value columns' option for this single value in all situations! Changes to the 'Single value columns' option can still be made after the usage of \$Set single value columns for all situations\$",

            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS to "Icon level",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS_DESCRIPTION to "By clicking the \$+Add Level\$ button, an icon level can be added. Each icon level can be assigned its own icon as well as a lower threshold value. If no lower threshold value is specified (threshold set to 0.0), the level is ignored. When the value of a single value of a situation reaches the given lower threshold, the icon of that value switches to the icon of that level. Any number of icon levels can be added. Additionally, the levels do not need to be specified in any specific order.",

            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION to "Situations tab",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION to "In the \$Situations\$ tab, the single situations of a project can be configured.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT to "Configure situation",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION to "To configure a situation, it must first be selected.\n" + "\n" + "In the \$name\$ text field, a name can be assigned to the situation.\n" + "\n" + "Below the \$name\$ text field, a color can be set by specifying a hex code or by adjusting the color sliders.\n" + "\n" + "Under \$Single Value Columns\$, columns can be assigned to each single value for calculating the value. By clicking on the button next to the icon of each single value, a dropdown menu opens. In this menu, the type of column calculation can be selected. This option can also be set for  all Situations at the same time in the single values tab with the Button \$Set Single value columns for all situations\$.\n" + "\n" + "If \$Scheme\$ is selected, the previously defined list of columns by the scheme is used.\n" + "If \$Zero\$ is selected, this column is always zero.\n" + "If \$Timeline\$ is selected, the timeline columns are chosen.\n" + "If \$Select\$ is selected, the columns can be manually selected.\n" + "\n" + "Under Timeline, the timeline of the situation can be adjusted. Single sections of the timeline can be added by clicking the \$+\$ button. Like with the single values, an icon can be assigned to each section by clicking the \$icon button\$. Next to the icon button, the column containing the value of this section can be specified. Additionally, the type of line can be specified. Just like with the single values, the order of the sections can be adjusted by drag and drop.",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT to "Export tab",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION to "In the \$Export\$ tab, the project can be exported as \$PNG\$ or \$HTML\$. For exporting the project, the \$size\$ and \$scaling\$ of the timeline can be specified. Additionally, a \$file path\$ and a \$file name scheme\$ for the file name can be specified.",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME to "Scheme for file names",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION to "In the text field for the File names, placeholders should be used to specify the scheme for naming the file. Each placeholder should start and end with a '\$'. The following placeholders can be used:\n" + ENUMERATION_SIGN + " \$block\$\n" + ENUMERATION_SIGN + " \$situation\$\n" + ENUMERATION_SIGN + " \$option\$\n" + "\n" + "\$block\$ is replaced with the number of the block when creating the file name.\n" + "\n" + "\$situation\$ is replaced with the number of the situation when creating the file name.\n" + "\n" + "\$option\$ is replaced with the number of the selection option when creating the file name. However, the replacement of \$option\$ only occurs when 'Export all options separately' is activated.",

            Labels.USER_GUIDE_HOTKEYS to "Hotkeys",
            Labels.USER_GUIDE_HOTKEYS_DESCRIPTION to "The Following Hotkeys can be used:\n\n" + ENUMERATION_SIGN + "\$'F1'\$ to open Help Tab\n" + ENUMERATION_SIGN + "\$'ctrl + shift + P'\$ to open Settings\n" + ENUMERATION_SIGN + "\$'ctrl + L'\$ to load new simulation data\n" + ENUMERATION_SIGN + "\$'ctrl + shift + S'\$ to save Project as ...\n" + ENUMERATION_SIGN + "\$'ctrl + S'\$ to save Project\n" + ENUMERATION_SIGN + "\$'ctrl + Z'\$ to undo Action. Only works in Text fields and color picker.\n",

            Labels.USER_GUIDE_PNG_START_SCREEN to "userguide/USER_GUIDE_PNG_START_SCREEN_EN.png",
            Labels.USER_GUIDE_PNG_FILE_MENU to "userguide/USER_GUIDE_PNG_FILE_MENU_EN.png",
            Labels.USER_GUIDE_PNG_SINGLE_VALUES to "userguide/USER_GUIDE_PNG_SINGLE_VALUES_EN.png",
            Labels.USER_GUIDE_PNG_SITUATIONS to "userguide/USER_GUIDE_PNG_SITUATIONS_EN.png",
            Labels.USER_GUIDE_PNG_EXPORT to "userguide/USER_GUIDE_PNG_EXPORT_EN.png",
            Labels.USER_GUIDE_PNG_SCHEME_FILE_NAMES to "userguide/USER_GUIDE_PNG_SCHEME_FILE_NAMES_EN.png",
            Labels.USER_GUIDE_PNG_ICON_LEVELS to "userguide/USER_GUIDE_PNG_ICON_LEVELS_EN.png",
            Labels.USER_GUIDE_PNG_ICON_LEVELS_EXAMPLE to "userguide/USER_GUIDE_PNG_ICON_LEVELS_EXAMPLE.png",

            /*
            #########################################   SETTINGS LABELS   ##############################################
            */
            Labels.SETTINGS_CHANGE_LANGUAGE to "Change language",

            Labels.LANGUAGE_ENGLISH to "English",
            Labels.LANGUAGE_GERMAN to "German",

            Labels.SETTINGS_CHANGE_DARK_MODE to "Dark mode",

            Labels.PREVIEW to "Preview",

            Labels.SETTINGS_999_TITLE to "999 Values",

            /*
            #########################################   INFO LABELS   ##################################################
            */
            Labels.OPEN_HElP_INFO_BOX to "Klick here to find out more (or press F1).",

            Labels.SINGLE_VALUE_SCHEME_INFO_TITLE to "Scheme for automatic column matching",
            Labels.SINGLE_VALUE_SCHEME_INFO_DESCRIPTION to "By adding a Regular expression (Regex), SurViz can automatically determine\nthe Ngene file columns for this Single Value by checking if the Regex\ncontains the name of the column.\n\nThe automatic Column matching will be used if 'Scheme' is selected at the\nSingle Value columns option of a Situation.\n\nIf the Scheme Regex is left empty but the 'Scheme' option is selected,\nall columns of this Situation will be chosen.",

            Labels.SINGLE_VALUE_LEVEL_INFO_TITLE to "Icon-Levels",
            Labels.SINGLE_VALUE_LEVEL_INFO_DESCRIPTION to "By adding a level, the icon of this single value changes, depending\n on the specified lower threshold, to the selected icon of this level.",

            Labels.TIMELINE_SCALING_INFO_TITLE to "Recommended scaling based on minimum section length",
            Labels.TIMELINE_SCALING_INFO_DESCRIPTION to "- 1: ~45 min\n" + "- 5: ~10 min\n" + "- 10: ~5 min\n" + "- 15: ~3 min\n" + "- 20: ~2 min",

            Labels.SINGLE_VALUE_DECIMAL_INFO_TITLE to "Forces all decimal points to be shown",
            Labels.SINGLE_VALUE_DECIMAL_INFO_DESCRIPTION to "If checked, decimal points will be printed even when they don't hold any information (\".0\")",
            Labels.SINGLE_VALUE_ALL_COLUMNS_INFO_TITLE to "Setting the Single value columns option for all situations",
            Labels.SINGLE_VALUE_ALL_COLUMNS_INFO_DESCRIPTION to "Here, the 'Single value columns' option from the Situations window\ncan be set for all situations simultaneously.\n\nCaution: This will overwrite all previous settings in the 'Single value columns' option\nfor this single value in all situations!",
            Labels.SINGLE_VALUE_ALPHA_INFO_TITLE to "Sets the alpha value of \"0\" single values",
            Labels.SINGLE_VALUE_ALPHA_INFO_DESCRIPTION to "If the single value has numeric value of \"0\" and \"Zero\" column is not selected,\n" + "icon and text of single value will be printed with this alpha value",
            Labels.SINGLE_VALUE_DIVIDER_TITLE to "Add Divider at end of single value",

            Labels.SINGLE_VALUE_SIZE_TITLE to "Change the width of the single values",

            Labels.PREVIEW_WARNING_TITLE to "Image not wide enough",
            Labels.PREVIEW_WARNING_DESCRIPTION to "The image is not wide enough to fit all content.\nWidth needed:",
            Labels.PREVIEW_WARNING_FIX to "Change image configuration to fix this.",

            Labels.PREVIEW_999_TITLE to "Option contains \"999 Value\"",
            Labels.PREVIEW_999_DESCRIPTION to "This option contains the configured \"999 Value\" \nand will therefore not be exported",

            Labels.SINGLE_VALUE_DUMMY_INFO_TITLE to "Sets this Single Value as a dummy",
            Labels.SINGLE_VALUE_DUMMY_INFO_DESCRIPTION to "Single Values in dummy mode allow the user\nto map the contained values to text\ninstead of printing the value itself",

            Labels.RANGED_FIELD_INFO_TITLE to "Ranged Field",
            Labels.RANGED_FIELD_INFO_DESCRIPTION to "Ranged fields allow the user to specify multiple Segments.\nEach segment is either a single number or two numbers\nseparated by \"-\".\nMultiple segments are separated by \",\".\n\nExample: 1,2-4,8-10\nwould result in the values 1,2,3,4,8,9,10",

            Labels.SITUATION_OVERRIDE_SCALE_INFO_TITLE to "Allows the user to specify\na scaling factor for the situation",

            Labels.PREVIEW_SITUATION_OVERRIDE_INFO_TITLE to "This situation overrides general image settings",

            /*
            #########################################   MISC/ERROR LABELS   ############################################
            */
            Labels.IMAGE_CREATE_ERROR to "Error while creating image",
            Labels.SITUATION_NOT_FOUND to "Situation not found",
            Labels.BLOCK_NOT_FOUND to "Block not found",

            Labels.INVALID_SEGMENT to "Invalid segment",

            Labels.IMPORT_ICON to "Import icon",

            Labels.EXPORT_DIALOG_APPLY_FIX to "Apply fix",
            Labels.EXPORT_DIALOG_OPEN_FOLDER to "Open folder",
            Labels.EXPORT_DIALOG_FIX_ALL to "Fix all",

            Labels.OVERRIDE_DATA_NOT_FITTING to "The scheme of the file you tried to load does not match the currently loaded one",
            Labels.OVERRIDE_DATA_ANYWAYS to "Load data anyways",

            Labels.CLOSE_DIALOG_TITLE to "Close application",
            Labels.CLOSE_DIALOG_TEXT to "Any unsaved progress may be lost",
            Labels.CLOSE_DIALOG_CONFIRM to "Close",

            Labels.ILLEGAL_CHAR to "Illegal character in file name",

            Labels.LOWER_THRESHOLD to "Lower threshold",

            Labels.COLOR to "Color",
            Labels.VALUE to "Value",
        )
    ),
    German(
        "de",
        Labels.LANGUAGE_GERMAN,
        mapOf(
            /*
            #########################################   BASIC LABELS   #################################################
            */
            Labels.SURVIZ to "SurViz",
            Labels.LOAD_LAST_PROJECT to "Letztes Projekt laden",
            Labels.NEW_PROJECT to "Neues Projekt",
            Labels.LOAD_PROJECT to "Projekt laden",
            Labels.SETTINGS to "Einstellungen",

            Labels.PAGE_SINGLE_VALUE to "Einzelwerte",
            Labels.PAGE_MODES to "Verkehrsmittel",
            Labels.PAGE_IMAGE to "Bild",
            Labels.PAGE_EXPORT to "Exportieren",
            Labels.PAGE_OVERRIDE_OPTIONS to "Situationen überschreiben",

            Labels.NEW to "Neu",
            Labels.SELECT to "Auswählen",
            Labels.OK to "Ok",
            Labels.ERROR to "Fehler",
            Labels.CANCEL to "Abbrechen",
            Labels.ADD to "Hinzufügen",
            Labels.PAGE_LEGEND to "Legende",
            Labels.LEGEND_ABBREVIATION to "Abkürzung",
            Labels.LEGEND_DESCRIPTION to "Beschreibung",

            Labels.FIELD_PREFIX to "Präfix",
            Labels.FIELD_UNIT to "Einheit",
            Labels.FIELD_COLUMN_SCHEME to "Spaltenschema",
            Labels.SCHEME_NO_RESULT_FOUND to "Keine Spalten gefunden",

            Labels.ADD_SINGLE_VALUE_ICON_LEVEL to "Stufe hinzufügen",

            Labels.FIELD_LINE_TYPE to "Linientyp",
            Labels.LINE_TYPE_SOLID to "Solide",
            Labels.LINE_TYPE_DOTTED to "Gepunktet",
            Labels.LINE_TYPE_DASHED to "Gestrichelt",

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

            Labels.TIMELINE_COLUMN_NAME to "Zeitstrahl",
            Labels.TIMELINE_COLUMN_DESC to "Nimmt die Spalten aus dem Zeitstrahl",

            Labels.SELECT_COLUMN_NAME to "Auswählen",
            Labels.SELECT_COLUMN_DESC to "Wähle die Spalten von Hand aus",

            Labels.SINGLE_VALUE_SET_ALL_COLUMNS to " Einzelwert-Spalten für alle Situationen setzen",
            Labels.SINGLE_VALUE_FORCE_DECIMAL to "Dezimalstellen erzwingen",
            Labels.SINGLE_VALUE_DIVIDER to "Trennstrich hinzufügen",
            Labels.SINGLE_VALUE_DIVIDER_LENGTH to "Länge",
            Labels.SINGLE_VALUE_DUMMY_SWITCH to "Dummy Codierung",
            Labels.SINGLE_VALUE_DUMMY_KEY to "Key",
            Labels.SINGLE_VALUE_DUMMY_VALUE to "Value",

            Labels.SITUATION_OVERRIDE_SCALE to "Skalierung überschreiben",

            Labels.LEGEND_SWITCH to "Eintrag mit Icon",

            /*
            #########################################   EXPORT/IMPORT LABELS   #########################################
            */

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
            Labels.EXPORT_IMAGE_CONFIG_BACKGROUND_COLOR to "Hintergrundfarbe",
            Labels.EXPORT_IMAGE_CONFIG_ALPHA to "Einzelwert Alpha",
            Labels.EXPORT_IMAGE_CONFIG_SINGLE_VALUE_SIZE to "Breite der Einzelwerte",

            Labels.PLACEHOLDERS to "Platzhalter",

            Labels.EXPORT_BUTTON to "Exportieren",

            Labels.IMPORT_ERROR_INVALID_FILE_TYPE to "Unbekannter Dateityp",
            Labels.IMPORT_ERROR_CORRUPT_FILE to "Korrupte Datei",
            Labels.IMPORT_ERROR_WRONG_VERSION to "Falsche Dateiversion",

            Labels.ICON_SELECT_WINDOW to "Icon auswählen",

            Labels.UNNAMED_PROJECT to "Unbenanntes Projekt",

            /*
            #########################################   APPBAR/ACTION LABELS   #########################################
            */
            Labels.APP_BAR_GROUP_FILE to "Datei",
            Labels.ACTION_SAVE to "Datei speichern",
            Labels.ACTION_SAVE_AS to "Datei speichern unter",
            Labels.ACTION_LOAD_DATA to "Neue Simulationsdatei laden",
            Labels.ACTION_CLOSE to "Datei schließen",

            Labels.APP_BAR_GROUP_SETTINGS to "Einstellungen",
            Labels.ACTION_OPEN_SETTINGS to "Einstellungen öffnen",

            Labels.APP_BAR_GROUP_HELP to "Hilfe",
            Labels.ACTION_OPEN_HELP to "Hilfe öffnen",

            Labels.ACTION_SAVE_AS_DIRECTORY to "Verzeichnis auswählen",
            Labels.ACTION_SAVE_AS_PROJECT_NAME to "Projektname",

            Labels.FILE_ALREADY_EXISTS to "Datei exisitiert bereits und wird überschrieben",

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

            /*
            #######################################   Status Message Labels   ##########################################
            */

            Labels.STATUS_MSG_PROJECT_SAVED to "Projekt gespeichert",
            Labels.STATUS_MSG_PROJECT_LOADED to "Projekt geladen",
            Labels.STATUS_MSG_PROJECT_CREATED to "Projekt erstellt",
            Labels.STATUS_MSG_PROJECT_CLOSED to "Projekt geschlossen",
            Labels.STATUS_MSG_SIMULATION_DATA_LOADED to "Simulationsdatei geladen",

            /*
            #########################################   USER GUIDE LABELS   ############################################
            */

            Labels.USER_GUIDE to "SurViz Benutzerhandbuch",

            Labels.USER_GUIDE_SURVIZ to "SurViz",
            Labels.USER_GUIDE_SURVIZ_DESCRIPTION to "SurViz ist ein Konfigurationstool für die Erstellung von visuellen stated-preference Befragungen im Bereich der Verkehrsforschung. Ein SurViz Projekt basiert auf einer Ngene-Datei, die mittels des Softwaretools Ngene erzeugt wird.",

            Labels.USER_GUIDE_START_SCREEN to "Startbildschirm",
            Labels.USER_GUIDE_START_SCREEN_DESCRIPTION to "Auf dem Startbildschirm lässt sich das letzte Projekt laden, ein neues Projekt erstellen, eine bereits angelegte Projektdatei laden oder die Einstellungen öffnen.\n",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT to "Letztes Projekt laden",
            Labels.USER_GUIDE_START_SCREEN_LAST_PROJECT_DESCRIPTION to "Durch Klicken auf die Schaltfläche \$Letztes Projekt Laden\$ wird automatisch das letzte bearbeitete und gespeicherte SurViz Projekt geladen.",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT to "Ein neues Projekt erstellen",
            Labels.USER_GUIDE_START_SCREEN_NEW_PROJECT_DESCRIPTION to "Durch Klicken auf die Schaltfläche \$Neues Projekt\$ öffnet sich automatisch der Explorer, damit eine Ngene-Datei ausgewählt werden kann.\n" + "Nach der Auswahl einer gültigen Datei wird automatisch ein neues Projekt aufgrund dieser Datei erstellt.",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT to "Eine bereits angelegte Projektdatei laden",
            Labels.USER_GUIDE_START_SCREEN_LOAD_PROJECT_DESCRIPTION to "Durch Klicken auf die Schaltfläche \$Projekt laden\$ öffnet sich automatisch der Explorer, damit eine Projektdatei ausgewählt werden kann.\n" + "Nach der Auswahl einer gültigen Datei wird automatisch das gewählte SurViz Projekt geladen.",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS to "Einstellungen öffnen",
            Labels.USER_GUIDE_START_SCREEN_SETTINGS_DESCRIPTION to "Durch Klicken auf die Schaltfläche \$Einstellungen\$ öffnet sich ein neues Fenster mit den Einstellungen des Programms.",
            Labels.USER_GUIDE_PROJECT_SCREEN to "Projektbildschirm",
            Labels.USER_GUIDE_PROJECT_SCREEN_DESCRIPTION to "Im Projektbildschirm lässt sich zu jeder Zeit das Dateimenü, über die Schaltfläche \$Datei\$, öffnen.\n" + "Außerdem kann zwischen den Reitern \$Einzelwerte\$, \$Situationen\$ und \$Export\$ ausgewählt werden.\n" + "Nach dem Erstellen einer neuen oder öffnen einer bereits bestehenden Projektdatei ist standardmäßig der Reiter \$Einzelwerte\$ ausgewählt.\n" + "Im Projektbildschirm wird zu jeder Zeit eine \$Vorschau\$ der Konfiguration einer Situation angezeigt",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW to "Situation eines Blocks in Vorschau anzeigen",
            Labels.USER_GUIDE_PROJECT_SCREEN_PREVIEW_DESCRIPTION to "In jedem der Reiter kann rechts oben in den Textfeldern \$Block\$ und \$Situation\$ die gewünschte Situation eines Blocks angegeben werden, um diese in der Vorschau anzuzeigen.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR to "Dateimenü",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_DESCRIPTION to "Über das Dateimenü lässt sich die gerade geöffnete Projektdatei speichern oder schließen.\n" + "Außerdem kann hier eine neue Ngene File geladen werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE to "Projekt Speichern",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_SAVE_DESCRIPTION to "Nach dem Öffnen des Dateimenüs kann die derzeitige Projektkonfiguration, durch Klicken auf \$Datei speichern\$, gespeichert werden.\n" + "Beim erstmaligen Speichern eines Projekts muss der Speicherpfad der Datei festgelegt werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE to "Projekt schließen",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW to "Neue Simulationsdatei laden",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_NEW_DESCRIPTION to "Durch das Laden einer neuer Simulationsdatei wird die alte Ngene Datei mit der neu ausgewählten überschrieben. Wenn die neue Datei die selbe Struktur wie die alte besitzt, gehen keine Einstellungen verloren.",
            Labels.USER_GUIDE_PROJECT_SCREEN_APPBAR_PROJECT_CLOSE_DESCRIPTION to "Nach dem Öffnen des Dateimenüs kann durch Klicken auf \$Datei schließen\$, das Projekt geschlossen werden, um zum Startbildschirm zurückzukehren.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE to "Einzelwerte Reiter",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_DESCRIPTION to "Im Reiter \$Einzelwerte\$ können Einzelwerte konfiguriert werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD to "Einzelwert hinzufügen und konfigurieren",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_ADD_DESCRIPTION to "Durch Klicken auf die Schaltfläche \$+ Neu\$ kann ein neuer Einzelwert hinzugefügt werden.\n" + "\n" + "Dem Einzelwert kann im Textfeld \$Einheit\$ eine Einheit zugewiesen werden.\n" + "\n" + "Dem Einzelwert kann im Textfeld \$Spaltenschema\$ ein Spaltenschema zugewiesen werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter \$Einzelwert Spaltenschema\$ zu finden.\n" + "\n" + "Durch Klicken der \$Icon-Schaltfläche\$ unter dem Textfeld Spaltenschema kann ein Icon für den Einzelwert ausgewählt werden.\n" + "\n" + "Durch Klicken der Schaltfläche \$+Stufe hinzufügen\$, kann ein eine Iconstufe hinzugefügt werden.\n" + "\n" + "Einer Iconstufe kann ein eigenes Icon hinzugefügt werden. Außerdem kann im Textfeld neben der Schaltfläche für das Icon der Stufe eine untere Schwelle festgelegt werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter \$Mehrstufige Icons\$ zu finden.\n" + "Diese gibt an, ab welchem Wert des Einzelwerts das Icon wechselt.\n" + "\n" + "Mit der Schaltfläche \$Einzelwert-Spalten für alle Situationen setzen\$ kann der Wert der Einzelwert-Spalten Option für alle Situationen gleichzeitig gesetzt werden. Weitere Informationen hierzu sind im Benutzerhandbuch unter \$Einzelwert-Spalten für alle Situationen setzen\$ zu finden.\n\n" + "Die Reihenfolge der Einzelwerte kann durch Anklicken der \$Schaltfläche mit den drei übereinanderliegenden Strichen\$, mittels Drag and Drop, angepasst werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME to "Einzelwert Spaltenschema",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SCHEME_DESCRIPTION to "Durch die Angabe eines Spaltenschemas kann SurViz aus den Spalten jeder Situation automatisch eine Liste der Spalten erstellen, die durch Aufsummieren der Werte, den Wert dieses Einzelwerts angibt.\n" + "Das Schema kann in Form eines Regulären Ausdrucks (Regex) angegeben werden.\n" + "\n" + "Beispiel:\n" + "Es existieren die Spalten\n" + ENUMERATION_SIGN + " \$car.cost_maut\$\n" + ENUMERATION_SIGN + " \$car.cost_benzin\$\n" + ENUMERATION_SIGN + " \$car.fz\$\n" + ENUMERATION_SIGN + " \$car.abgang\$\n" + ENUMERATION_SIGN + " \$car.zugang\$\n" + "zu der Situation \$car\$.\n" + "\n" + "Durch das Schema \$cost\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + ENUMERATION_SIGN + " \$car.cost_maut\$\n" + ENUMERATION_SIGN + " \$car.cost_benzin\$\n" + "Jedoch würde in diesem Beispiel bereits das Schema \$c\$ reichen, um die oben aufgeführten Spalten auszuwählen.\n" + "\n" + "Durch das Schema \$fz\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + ENUMERATION_SIGN + " \$car.fz\$\n" + "Durch das Schema \$(fz|abgang|zugang)\$ werden die folgenden Spalten in der Situation \$car\$ ausgewählt:\n" + ENUMERATION_SIGN + " \$car.fz\$\n" + ENUMERATION_SIGN + " \$car.abgang\$\n" + ENUMERATION_SIGN + " \$car.zugang\$\n",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS to "Einzelwert-Spalten für alle Situationen setzen",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_SET_ALL_COLUMNS_DESCRIPTION to "Mit der Schaltfläche \$Einzelwert-Spalten für alle Situationen setztn\$ kann die 'Einzelwert-Spalten' option aus dem Situationen Fenster, " +
                    "für diesen Einzelwert und für alle Situationen gleichzeitig festgelegt werden. " +
                    "\$Achtung:\$ Hierbei werden die bisherigen Einstellungen dieses Einzelwerts " +
                    "in der 'Einzelwert Spalten' Option in allen Situationen überschrieben! Änderungen an der 'Einzelwert-Spalten' Option in einzelnen Situationen, können auch nach der Nutzung der \$Einzelwert-Spalten für alle Situationen setzen\$ Funktion, weiterhin vorgenommen werden.",

            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS to "Mehrstufige Icons",
            Labels.USER_GUIDE_PROJECT_SCREEN_SINGLE_VALUE_LEVELS_DESCRIPTION to "Durch Klicken der Schaltfläche \$+Stufe hinzufügen\$, kann ein Iconstufe hinzugefügt werden. Jeder Iconstufe kann ein eigenes Icon, so wie ein unterer Grenzwert zugewiesen werden. Ist kein unterer Grenzwert angegeben (Grenzwert auf 0.0), wird die Stufe ignoriert. Erreicht der Wert des Einzelwerts einer Situation die gegebene Untergrenze, so wechselt das Icon des Einzelwerts auf das Icon dieser Stufe. Es können beliebig viele Iconstufen hinzugefügt werden. Außerdem müssen die Stufen in keiner spezifischen Reihenfolge angegeben werden.",

            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION to "Situationen Reiter",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_DESCRIPTION to "Im Reiter \$Situationen\$ können die einzelnen Situationen eines projekts konfiguriert werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT to "Situation konfigurieren",
            Labels.USER_GUIDE_PROJECT_SCREEN_SITUATION_EDIT_DESCRIPTION to "Um eine Situation zu konfigurieren, muss diese zunächst ausgewählt werden.\n" + "\n" + "Im Textfeld \$Name\$ kann der Situation ein Name zugewiesen werden.\n" + "\n" + "Unter dem Textfeld Name kann eine Farbe, durch Angeben eines Hex-Codes oder durch Verändern der Farbregler, festgelegt werden.\n" + "\n" + "Unter \$Einzelwert Spalten\$ kann jedem Einzelwert zugewiesen werden, welche Spalten für die Berechnung des Wertes benutzt werden. Durch Klicken auf die Schaltfläche neben dem Icon, des jeweiligen Eigenwerts, öffnet sich ein Dropdown-Menü. In diesem kann die Art der Spaltenberechnung ausgewählt werden. Diese Option kann auch für alle Situationen gleichzeitig, mittels der Schaltfläche \$Einzelwert-Spalten für alle Situationen setzen\$ im Einzelwerte Fenster, gesetzt werden.\n" + "\n" + "Wird \$Schema\$ ausgewählt, wird die zuvor durch das Schema definierte Liste an Spalten verwendet.\n" + "Wird \$Null\$ ausgewählt, ist diese Spalte immer Null.\n" + "Wird \$Zeitlinie\$ ausgewählt, werden die Spalten der Zeitlinie ausgewählt.\n" + "Wird \$Auswählen\$ ausgewählt, können die Spalten per Hand ausgewählt werden.\n" + "Unter Zeitlinie kann der Zeitstrahl der Situation angepasst werden. Einzelne Abschnitte des Zeitstrahl können mittels des \$+\$ hinzugefügt werden. Durch Anklicken der \$Icon Schaltfläche\$ kann, wie bei den Einzelwerten, jedem Abschnitt ein Icon zugewiesen werden. Neben der \$Icon-Schaltfläche\$ kann die Spalte angegeben werden, die den Wert dieses Abschnitts enthält. Außerdem kann der Typ der Linie angegeben werden. Genauso wie bei den Einzelwerten kann die Reihenfolge der Abschnitte, durch Drag and Drop angepasst werden.",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT to "Export Reiter",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_DESCRIPTION to "Im Reiter \$Export\$ kann das projekt als \$PNG\$ oder \$HTML\$ exportiert werden.\n" + "Zum Exportieren des Projekts kann die \$Größe\$ und \$Skalierung\$ der Zeitlinie angegeben werden. Außerdem kann ein \$Dateipfad\$ und ein \$Schema für den Dateinamen\$ angegeben werden",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME to "Schema für Dateinamen",
            Labels.USER_GUIDE_PROJECT_SCREEN_EXPORT_SCHEME_DESCRIPTION to "Im Textfeld für die Dateinamen der Dateien sollten Platzhalter benutzt werden, um ein Schema für die Dateinamen anzugeben. Jeder Platzhalter muss mit einem '\$'-Zeichen beginnen und enden. Folgende Platzhalter können benutzt werden:\n" + ENUMERATION_SIGN + " \$block\$\n" + ENUMERATION_SIGN + " \$situation\$\n" + ENUMERATION_SIGN + " \$option\$\n" + "\n" + "\$block\$ wird beim Erstellen des Dateinamens mit der Nummer des Blocks ersetzt.\n" + "\n" + "\$situation\$ wird beim Erstellen des Dateinamens mit der Nummer der Situation ersetzt.\n" + "\n" + "\$option\$ wird beim Erstellen des Dateinamens mit der Nummer der Auswahloption ersetzt. Die Ersetzung von \$option\$ erfolgt jedoch nur, wenn 'Alle Optionen separat exportieren' aktiviert ist.",

            Labels.USER_GUIDE_HOTKEYS to "Hotkeys",
            Labels.USER_GUIDE_HOTKEYS_DESCRIPTION to "Folgende Hotkeys können verwendet werden:\n\n" + ENUMERATION_SIGN + "\$'F1'\$ öffnet das Hilfefenster\n" + ENUMERATION_SIGN + "\$'Strg + Umschalt + P'\$ öffnet die Einstellungen\n" + ENUMERATION_SIGN + "\$'Strg + L'\$ um eine neue Simulationsdatei zu laden\n" + ENUMERATION_SIGN + "\$'Strg + Umschalt + S'\$ Projekt speichern unter ...\n" + ENUMERATION_SIGN + "\$'Strg + S'\$ Projekt speichern\n" + ENUMERATION_SIGN + "\$'Strg + Z'\$ Macht die letzte Aktion rückgängig. Funktioniert nur bei Textfeldern und der Farbauswahl.\n",


            Labels.USER_GUIDE_PNG_START_SCREEN to "userguide/USER_GUIDE_PNG_START_SCREEN_DE.png",
            Labels.USER_GUIDE_PNG_FILE_MENU to "userguide/USER_GUIDE_PNG_FILE_MENU_DE.png",
            Labels.USER_GUIDE_PNG_SINGLE_VALUES to "userguide/USER_GUIDE_PNG_SINGLE_VALUES_DE.png",
            Labels.USER_GUIDE_PNG_SITUATIONS to "userguide/USER_GUIDE_PNG_SITUATIONS_DE.png",
            Labels.USER_GUIDE_PNG_EXPORT to "userguide/USER_GUIDE_PNG_EXPORT_DE.png",
            Labels.USER_GUIDE_PNG_SCHEME_FILE_NAMES to "userguide/USER_GUIDE_PNG_SCHEME_FILE_NAMES_DE.png",
            Labels.USER_GUIDE_PNG_ICON_LEVELS to "userguide/USER_GUIDE_PNG_ICON_LEVELS_DE.png",
            Labels.USER_GUIDE_PNG_ICON_LEVELS_EXAMPLE to "userguide/USER_GUIDE_PNG_ICON_LEVELS_EXAMPLE.png",
            /*
            #########################################   SETTINGS LABELS   ##############################################
            */

            Labels.SETTINGS_CHANGE_LANGUAGE to "Sprache ändern",

            Labels.LANGUAGE_ENGLISH to "Englisch",
            Labels.LANGUAGE_GERMAN to "Deutsch",

            Labels.SETTINGS_CHANGE_DARK_MODE to "Dunkel-Modus",

            Labels.PREVIEW to "Vorschau",
            Labels.SETTINGS_999_TITLE to "999 Werte",

            /*
            #########################################   INFO LABELS   ##################################################
            */

            Labels.OPEN_HElP_INFO_BOX to "Hier klicken um mehr zu erfahren (oder F1 drücken).",

            Labels.SINGLE_VALUE_SCHEME_INFO_TITLE to "Schema für eine automatische Spaltenauswahl",
            Labels.SINGLE_VALUE_SCHEME_INFO_DESCRIPTION to "Durch hinzufügen eines Regulären Ausdrucks (Regex) kann SurViz automatisch\nSpalten der Ngene Datei, deren namen durch den Regex beschrieben werden,\ndiesem Einzelwert zuweisen.\n\nFür die automatische Auswahl der Spalten muss bei der jeweiligen Situation\nunter 'Einzelwert-Spalten' die Wahlmöglichkeit 'Schema' ausgewählt sein.\n\nIst kein Schema angegeben, jedoch 'Schema' ausgewählt, so werden alle\nmöglichen Spalten dieser Situation gewählt.",

            Labels.SINGLE_VALUE_LEVEL_INFO_TITLE to "Mehrstufige Icons",
            Labels.SINGLE_VALUE_LEVEL_INFO_DESCRIPTION to "Durch Hinzufügen einer Stufe wechselt das Icon dieses Einzelwerts,\nab der angegebenen Untergrenze, auf das gewählte Icon dieser Stufe.",

            Labels.TIMELINE_SCALING_INFO_TITLE to "Empfohlene Skalierung basierend auf kleinstem Zeitabschnitt",
            Labels.TIMELINE_SCALING_INFO_DESCRIPTION to "- 1: ~45 min\n" + "- 5: ~10 min\n" + "- 10: ~5 min\n" + "- 15: ~3 min\n" + "- 20: ~2 min",

            Labels.SINGLE_VALUE_DECIMAL_INFO_TITLE to "Erzwingt das darstellen aller Dezimalstellen",
            Labels.SINGLE_VALUE_DECIMAL_INFO_DESCRIPTION to "Ist diese Einstellung aktiviert, werden alle Dezimalstellen dargestellt, auch wenn sie keine neue Information bereitstellen",
            Labels.SINGLE_VALUE_ALL_COLUMNS_INFO_TITLE to "Einzelwert-Spalten für alle Situationen setzen",
            Labels.SINGLE_VALUE_ALL_COLUMNS_INFO_DESCRIPTION to "Hier kann die 'Einzelwert-Spalten' option aus dem Situationen Fenster,\nfür diesen Einzelwert und für alle Situationen gleichzeitig festgelegt werden.\n\nAchtung: Hierbei werden die bisherigen Einstellungen dieses Einzelwerts\nin der 'Einzelwert Spalten' Option in allen Situationen überschrieben!",
            Labels.SINGLE_VALUE_ALPHA_INFO_TITLE to "Legt die Transparenz von \"0\" Einzelwerten fest",
            Labels.SINGLE_VALUE_ALPHA_INFO_DESCRIPTION to "Ist der numerische Wert eines Einzelwerts \"0\" und \"Null\"-Spalte ist nicht ausgewählt,\n" + "so wird die Transparent des Texts und des Icons hierdurch festgelegt",
            Labels.SINGLE_VALUE_DIVIDER_TITLE to "Fügt einen Trennstrich am Ende des Einzelwerts hinzu",

            Labels.SINGLE_VALUE_SIZE_TITLE to "Ändert die Breite der Einzelwerte",

            Labels.PREVIEW_WARNING_TITLE to "Bild zu schmal",
            Labels.PREVIEW_WARNING_DESCRIPTION to "Das bild ist zu schmal um alle Inhalte korrekt wiederzugeben.\nDas Bild müsste:",
            Labels.PREVIEW_WARNING_FIX to "breit sein.\nÄndern der Bildeinstellungen kann dieses Problem beheben.",

            Labels.PREVIEW_999_TITLE to "Option enthält \"999-Wert\"",
            Labels.PREVIEW_999_DESCRIPTION to "Diese Option enthält den festgelegten \"999-Wert\"\nund wird deshalb nicht exportiert",

            Labels.SINGLE_VALUE_DUMMY_INFO_TITLE to "Setzt den Einzelwert in den Dummy Modus",
            Labels.SINGLE_VALUE_DUMMY_INFO_DESCRIPTION to "Einzelwerte im Dummy Modus erlauben es dem Nutzer den\nWert des Einzelwerts einem oder mehreren\nTexten zuzuweisen, die anstatt des Werts ansich dargestellt werden",

            Labels.RANGED_FIELD_INFO_TITLE to "Bereichsfeld",
            Labels.RANGED_FIELD_INFO_DESCRIPTION to "Bereichsfeld erlaubt die Eingabe von Zahlen oder\nZahlenbereichen, getrennt durch ein Komma.\nEin Zahlenbereich wird durch zwei Zahlen\ndargestellt, die durch ein '-' getrennt sind.\n" +
                    "\n" +
                    "Beispiel: 1,2-4,8-10\n" +
                    "resultiert in den folgenden Werten: 1,2,3,4,8,9,10",

            Labels.SITUATION_OVERRIDE_SCALE_INFO_TITLE to "Erlaubt dem Nutzer einen\nSkalierungsfaktor für die Situation festzulegen",

            Labels.PREVIEW_SITUATION_OVERRIDE_INFO_TITLE to "Diese Situation überschreibt die generellen Bildeinstellungen",
            /*
            #########################################   MISC/ERROR LABELS   ############################################
            */

            Labels.IMAGE_CREATE_ERROR to "Fehler beim Erstellen des Bildes",
            Labels.SITUATION_NOT_FOUND to "Situation konnte nicht gefunden werden",

            Labels.IMPORT_ICON to "Neues Icon importieren",

            Labels.EXPORT_DIALOG_APPLY_FIX to "Fehler beheben",
            Labels.EXPORT_DIALOG_OPEN_FOLDER to "Ordner öffnen",
            Labels.EXPORT_DIALOG_FIX_ALL to "Alle beheben",

            Labels.OVERRIDE_DATA_NOT_FITTING to "Das Schema der Simulationsdatei entspricht nicht dem aktuellen Schema",
            Labels.OVERRIDE_DATA_ANYWAYS to "Trotzdem laden",

            Labels.CLOSE_DIALOG_TITLE to "Anwendung schließen",
            Labels.CLOSE_DIALOG_TEXT to "Ungespeicherte Änderungen können verloren gehen",
            Labels.CLOSE_DIALOG_CONFIRM to "Schließen",

            Labels.BLOCK_NOT_FOUND to "Block konnte nicht gefunden werden",

            Labels.INVALID_SEGMENT to "Ungültiger Bereich",

            Labels.ILLEGAL_CHAR to "Verbotenes Zeichen in Dateiname",

            Labels.LOWER_THRESHOLD to "Untergrenze",

            Labels.COLOR to "Farbe",
            Labels.VALUE to "Wert",
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