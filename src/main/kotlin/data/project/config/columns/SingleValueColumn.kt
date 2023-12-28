package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

interface SingleValueColumn {
    fun getValue(singleValueConfig : SingleValueConfig, situation : Situation) : Double
}