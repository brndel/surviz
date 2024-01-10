package data.resources.fields

/**
 * Named fields can be used by classes to dynamically request UI-Elements to display
 * and store the users input through them.
 *
 * @property name name of the field
 * @property field specifies the type and holds the data
 * @constructor Create named field with given name and data
 * @see FieldData
 */
class NamedField(
    /**
     * Name of the field, could be used as an identifier of the field.
     */
    val name: String,

    /**
     * The actual data that the field holds.
     */
    val field: FieldData
)