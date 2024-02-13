package data.io.utils

/**
 * Option id identifies an option or situation
 *
 * @property block ID of the block
 * @property situation ID of the situation
 * @property option Name of the option
 * @constructor Create empty Option id with at least an ID for block and situation
 */
data class OptionId(val block: Int, val situation: Int, val option: String? = null)
