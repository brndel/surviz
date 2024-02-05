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
    @Test
    fun testSwapTimelineOrder(){
        situationConfig.addTimelineEntry()
        situationConfig.addTimelineEntry()
        situationConfig.addTimelineEntry()

        situationConfig.getTimeline()[0].column.value = "index0"
        situationConfig.getTimeline()[2].column.value = "index2"

        val originalIndex0 = situationConfig.getTimeline()[0]
        val originalIndex2 = situationConfig.getTimeline()[2]

        // Test if swapping works
        situationConfig.swapTimelineOrder(0, 2)
        assertEquals(situationConfig.getTimeline()[0], originalIndex2)
        assertEquals(situationConfig.getTimeline()[2], originalIndex0)

        // Test if swapping back works
        situationConfig.swapTimelineOrder(2, 0)
        assertEquals(situationConfig.getTimeline()[2], originalIndex2)
        assertEquals(situationConfig.getTimeline()[0], originalIndex0)

        // Test if swapping operation will be ignored if invalid indexes are used
        val originalIndex1 = situationConfig.getTimeline()[1]
        situationConfig.swapTimelineOrder(0, 3)
        situationConfig.swapTimelineOrder(-1, Int.MAX_VALUE)
        assertEquals(situationConfig.getTimeline()[2], originalIndex2)
        assertEquals(situationConfig.getTimeline()[0], originalIndex0)
        assertEquals(situationConfig.getTimeline()[1], originalIndex1)
    }
    @Test
    fun testGetColumns(){
        val id1 = UUID.randomUUID()
        val id2 = UUID.randomUUID()

        // Test if new key will be mapped to new SingleValueColumn
        situationConfig.getColumns(id1)
        assertTrue(situationConfig.singleValueColumns.size == 1)
        situationConfig.getColumns(id2)
        assertTrue(situationConfig.singleValueColumns.size == 2)
        // Test if already used key won't create new map entry
        situationConfig.getColumns(id1)
        assertTrue(situationConfig.singleValueColumns.size == 2)
    }
}