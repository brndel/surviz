package data.io

import data.project.ProjectData
import data.project.data.Block
import data.project.data.DataScheme
import data.project.data.DataSchemeOption
import data.project.data.Situation
import data.project.data.SituationOption
import data.resources.exceptions.CorruptFileException
import data.resources.exceptions.FileTypeException
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.BeforeTest
import kotlin.test.assertFailsWith


class DataManagerTest {

    private lateinit var testProjectData: ProjectData

    @BeforeTest
    fun setup() {
        testProjectData = ProjectData(
            DataScheme(
                mutableListOf(
                    DataSchemeOption(
                        "fuss",
                        setOf("fz_fuss")
                    ),
                    DataSchemeOption(
                        "rad",
                        setOf("fz_rad")
                    ),
                    DataSchemeOption(
                        "car",
                        setOf("fz_miv", "zugang", "abgang", "cost_car")
                    ),
                    DataSchemeOption(
                        "oev_fuss",
                        setOf("fz_oev", "cost_oev", "freq_oev", "zugang_oevfuss")
                    ),
                    DataSchemeOption(
                        "shuttle_tb",
                        setOf("fz_oev", "cost_oev", "freq_oev", "zugang_oevfuss")
                    ),
                    DataSchemeOption(
                        "shuttle_od",
                        setOf("fz_oev", "cost_oev", "warten")
                    )
                )
            ),
            arrayListOf(
                Block(
                    1,
                    arrayListOf(
                        Situation(
                            1,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 135.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 40.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 30.0,
                                        "zugang" to 3.0,
                                        "abgang" to 0.0,
                                        "cost_car" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 26.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 5.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 26.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 1.5,
                                        "warten" to 7.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            2,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 15.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 5.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 4.0,
                                        "zugang" to 3.0,
                                        "abgang" to 0.0,
                                        "cost_car" to 5.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 5.0,
                                        "cost_oev" to 2.0,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 30.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 5.0,
                                        "cost_oev" to 2.0,
                                        "warten" to 7.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            3,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 45.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 20.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 25.0,
                                        "zugang" to 1.0,
                                        "abgang" to 5.0,
                                        "cost_car" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 20.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 5.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 32.0,
                                        "cost_oev" to 2.0,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 20.0,
                                        "cost_oev" to 2.5,
                                        "warten" to 17.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            4,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 135.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 40.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 25.0,
                                        "zugang" to 1.0,
                                        "abgang" to 5.0,
                                        "cost_car" to 5.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 5.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 32.0,
                                        "cost_oev" to 2.5,
                                        "warten" to 3.0
                                    )
                                )
                            )
                        )
                    )
                ),
                Block(
                    2,
                    arrayListOf(
                        Situation(
                            1,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 45.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 20.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 20.0,
                                        "zugang" to 3.0,
                                        "abgang" to 5.0,
                                        "cost_car" to 5.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 20.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 32.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 1.0,
                                        "warten" to 3.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            2,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 15.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 5.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 15.0,
                                        "zugang" to 1.0,
                                        "abgang" to 2.0,
                                        "cost_car" to 4.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 9.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 2.5,
                                        "warten" to 7.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            3,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 15.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 5.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 7.0,
                                        "zugang" to 3.0,
                                        "abgang" to 5.0,
                                        "cost_car" to 3.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 9.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 30.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 5.0,
                                        "cost_oev" to 2.0,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 5.0,
                                        "cost_oev" to 2.0,
                                        "warten" to 13.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            4,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 45.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 20.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 25.0,
                                        "zugang" to 1.0,
                                        "abgang" to 2.0,
                                        "cost_car" to 4.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 5.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 32.0,
                                        "cost_oev" to 2.0,
                                        "warten" to 13.0
                                    )
                                )
                            )
                        ),
                    )
                ), Block(
                    3,
                    arrayListOf(
                        Situation(
                            1,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 15.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 5.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 10.0,
                                        "zugang" to 3.0,
                                        "abgang" to 0.0,
                                        "cost_car" to 3.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 30.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 9.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 2.0,
                                        "warten" to 7.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            2,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 135.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 40.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 30.0,
                                        "zugang" to 3.0,
                                        "abgang" to 0.0,
                                        "cost_car" to 5.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 32.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 5.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 2.5,
                                        "warten" to 3.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            3,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 45.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 20.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 25.0,
                                        "zugang" to 1.0,
                                        "abgang" to 0.0,
                                        "cost_car" to 5.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 26.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 5.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 20.0,
                                        "cost_oev" to 2.0,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 26.0,
                                        "cost_oev" to 1.5,
                                        "warten" to 13.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            4,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 135.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 40.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 25.0,
                                        "zugang" to 3.0,
                                        "abgang" to 5.0,
                                        "cost_car" to 5.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 1.5,
                                        "warten" to 17.0
                                    )
                                )
                            )
                        )
                    )
                ), Block(
                    4,
                    arrayListOf(
                        Situation(
                            1,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 45.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 20.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 20.0,
                                        "zugang" to 3.0,
                                        "abgang" to 5.0,
                                        "cost_car" to 5.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 26.0,
                                        "cost_oev" to 2.0,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 20.0,
                                        "cost_oev" to 2.5,
                                        "warten" to 17.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            2,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 15.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 5.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 10.0,
                                        "zugang" to 1.0,
                                        "abgang" to 2.0,
                                        "cost_car" to 4.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 9.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 30.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 9.0,
                                        "cost_oev" to 2.5,
                                        "warten" to 7.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            3,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 15.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 5.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 7.0,
                                        "zugang" to 1.0,
                                        "abgang" to 0.0,
                                        "cost_car" to 3.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 5.0,
                                        "cost_oev" to 1.5,
                                        "freq_oev" to 10.0,
                                        "zugang_oevfuss" to 6.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 13.0,
                                        "cost_oev" to 2.0,
                                        "freq_oev" to 30.0,
                                        "zugang_oevfuss" to 10.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 5.0,
                                        "cost_oev" to 2.0,
                                        "warten" to 7.0
                                    )
                                )
                            )
                        ),
                        Situation(
                            4,
                            arrayListOf(
                                SituationOption(
                                    "fuss",
                                    mapOf("fz_fuss" to 135.0)
                                ),
                                SituationOption(
                                    "rad",
                                    mapOf("fz_rad" to 40.0)
                                ),
                                SituationOption(
                                    "car",
                                    mapOf(
                                        "fz_miv" to 20.0,
                                        "zugang" to 1.0,
                                        "abgang" to 5.0,
                                        "cost_car" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "oev_fuss",
                                    mapOf(
                                        "fz_oev" to 32.0,
                                        "cost_oev" to 2.5,
                                        "freq_oev" to 60.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_tb",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 1.0,
                                        "freq_oev" to 5.0,
                                        "zugang_oevfuss" to 2.0
                                    )
                                ),
                                SituationOption(
                                    "shuttle_od",
                                    mapOf(
                                        "fz_oev" to 50.0,
                                        "cost_oev" to 2.0,
                                        "warten" to 3.0
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )
    }

    @Test
    fun loadData() {
        val path = "src/test/resources/data/Sample.ngd"
        val file = File(path)
        val projectData = DataManager.loadData(file)
        // implementation of equals() in projectData is needed
        assertTrue(projectData == testProjectData)
    }

    @Test
    fun loadMissingFile() {
        val path = "src/test/resources/data/Sample.abc"
        val file = File(path)

        assertFailsWith<FileTypeException> {
            DataManager.loadData(file)
        }
    }

    @Test
    fun loadCorruptFile() {
        val path = "src/test/resources/data/Sample_Corrupt.ngd"
        val file = File(path)
        assertFailsWith<CorruptFileException> {
            DataManager.loadData(file)
        }
    }

    @Test
    fun saveData() {
    }
}