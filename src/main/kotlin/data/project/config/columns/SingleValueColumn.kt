package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

sealed interface SingleValueColumn {
    fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double
}