package data.project.config.columns

import data.project.config.SingleValueConfig
import data.project.data.Situation

class ListColumn constructor(
        columns : List<String>
)  : SingleValueColumn {
    override fun getValue(singleValueConfig : SingleValueConfig, situation : Situation) : Double{
        return 0.0
    }
}