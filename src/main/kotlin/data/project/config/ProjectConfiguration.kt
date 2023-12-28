package data.project.config

import java.util.UUID

class ProjectConfiguration constructor(
        val singleValueConfig : Map<UUID, SingleValueConfig>,
        val situationConfig : Map<String, SituationConfig>
)

{

}