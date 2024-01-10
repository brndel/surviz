package data.resources.fields

/**
 * Named fields can be used by classes to dynamically request UI-Elements to display
 * and store the users input through them.
 *
 * @property name name of the field, gets used as an identifier for the configuration of an [Exporter]
 * @property field specifies the type and holds the data
 */
class NamedField(
    val name: String,

    val field: FieldData
)