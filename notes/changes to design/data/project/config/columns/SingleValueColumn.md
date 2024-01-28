Changed:     fun getValue(singleValueConfig: SingleValueConfig, situation: Situation): Double
to:     fun getValue(singleValueConfig: SingleValueConfig, situationConfig: SituationConfig, situation: SituationOption): Double
because information about the type of option is needed