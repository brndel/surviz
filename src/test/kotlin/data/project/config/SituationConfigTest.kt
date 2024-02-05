package data.project.config

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import data.io.DataManager
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.*
import kotlin.test.BeforeTest

/**
 * Test class for ProjectConfiguration
 */
class SituationConfigTest {

    private lateinit var situationConfig: SituationConfig

    @BeforeEach
    fun setUp() {
        situationConfig = SituationConfig()
    }

    @Test
    fun testAddTimelineEntry() {
        situationConfig.addTimelineEntry()
        assertTrue(situationConfig.getTimeline().size == 1)
        situationConfig.addTimelineEntry()
        assertTrue(situationConfig.getTimeline().size == 2)
    }
    @Test
    fun testRemoveTimelineEntry() {
        situationConfig.addTimelineEntry()
        situationConfig.addTimelineEntry()
        situationConfig.addTimelineEntry()
        assertTrue(situationConfig.getTimeline().size == 3)

        situationConfig.removeTimelineEntry(situationConfig.getTimeline()[1])
        // Test if removing an entry works.
        assertTrue(situationConfig.getTimeline().size == 2)

        val timelineEntry = situationConfig.getTimeline()[1]
        situationConfig.removeTimelineEntry(timelineEntry)
        assertTrue(situationConfig.getTimeline().size == 1)
        situationConfig.removeTimelineEntry(timelineEntry)
        // Test if removing an entry twice will be ignored
        assertTrue(situationConfig.getTimeline().size == 1)

        situationConfig.removeTimelineEntry(situationConfig.getTimeline()[0])
        // Test if timeline is empty after removing all entry's
        assertTrue(situationConfig.getTimeline().isEmpty())
    }
}