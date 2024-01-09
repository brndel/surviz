package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

/**
 * This interface represents a type of single value column of the Ngene file.
 */
sealed interface SingleValueColumn {
    fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double
}