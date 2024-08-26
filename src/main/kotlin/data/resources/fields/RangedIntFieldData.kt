package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import data.resources.exceptions.InvalidSegmentException

/**
 * Ranged int field data hold string values that represent a range of integers
 *
 * @constructor
 *
 * @param initialValue initial value of the field
 * @param label label of the field
 */
class RangedIntFieldData(
    initialValue: String,
    label: String
) : FieldData(label) {

    val value: MutableState<String> = mutableStateOf(initialValue)

    /**
     * Get value returns a list of integers from the string value
     * @throws InvalidSegmentException if one or more segments are not a valid
     * @return list of integers
     */
    override fun getValue(): Any {
        val ints: ArrayList<Int> = ArrayList()

        val segments = value.value.split(",")
        for (segment in segments) {
            if (segment.matches(Regex("^\\d+$")) || segment.matches(Regex("^\\d+-\\d+$"))) {
                ints.addAll(getInts(segment))
            } else {
                throw InvalidSegmentException(segment)
            }
        }
        return ints
    }

    private fun getInts(segment: String): ArrayList<Int> {
        val ints = ArrayList<Int>()
        if (segment.matches(Regex("^\\d+$"))) {
            val value = segment.toIntOrNull() ?: throw InvalidSegmentException(segment)
            ints.add(value)
        } else {
            val range = segment.split("-")
            val start = range[0].toIntOrNull() ?: throw InvalidSegmentException(segment)
            val end = range[1].toIntOrNull() ?: throw InvalidSegmentException(segment)

            if (start > end) {
                throw InvalidSegmentException(segment)
            }

            for (i in start..end) {
                ints.add(i)
            }
        }
        return ints
    }
}