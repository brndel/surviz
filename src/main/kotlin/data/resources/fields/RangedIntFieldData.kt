package data.resources.fields

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import data.resources.exceptions.InvalidSegmentException

class RangedIntFieldData(
    initialValue: String,
    label: String
) : FieldData(label) {

    val value: MutableState<String> = mutableStateOf(initialValue)
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
            ints.add(segment.toInt())
        } else {
            val range = segment.split("-")
            val start = range[0].toInt()
            val end = range[1].toInt()

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