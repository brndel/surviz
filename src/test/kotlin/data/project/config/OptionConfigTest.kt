package data.project.config

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

/**
 * Test class for ProjectConfiguration
 */
class OptionConfigTest {

    private lateinit var optionConfig: OptionConfig

    @BeforeEach
    fun setUp() {
        optionConfig = OptionConfig()
    }

    @Test
    fun testAddTimelineEntry() {
        optionConfig.addTimelineEntry()
        assertTrue(optionConfig.getTimeline().size == 1)
        optionConfig.addTimelineEntry()
        assertTrue(optionConfig.getTimeline().size == 2)
    }
    @Test
    fun testRemoveTimelineEntry() {
        optionConfig.addTimelineEntry()
        optionConfig.addTimelineEntry()
        optionConfig.addTimelineEntry()
        assertTrue(optionConfig.getTimeline().size == 3)

        optionConfig.removeTimelineEntry(optionConfig.getTimeline()[1])
        // Test if removing an entry works.
        assertTrue(optionConfig.getTimeline().size == 2)

        val timelineEntry = optionConfig.getTimeline()[1]
        optionConfig.removeTimelineEntry(timelineEntry)
        assertTrue(optionConfig.getTimeline().size == 1)
        optionConfig.removeTimelineEntry(timelineEntry)
        // Test if removing an entry twice will be ignored
        assertTrue(optionConfig.getTimeline().size == 1)

        optionConfig.removeTimelineEntry(optionConfig.getTimeline()[0])
        // Test if timeline is empty after removing all entry's
        assertTrue(optionConfig.getTimeline().isEmpty())
    }
    @Test
    fun testSwapTimelineOrder(){
        optionConfig.addTimelineEntry()
        optionConfig.addTimelineEntry()
        optionConfig.addTimelineEntry()

        optionConfig.getTimeline()[0].column.value = "index0"
        optionConfig.getTimeline()[2].column.value = "index2"

        val originalIndex0 = optionConfig.getTimeline()[0]
        val originalIndex2 = optionConfig.getTimeline()[2]

        // Test if swapping works
        optionConfig.swapTimelineOrder(0, 2)
        assertEquals(optionConfig.getTimeline()[0], originalIndex2)
        assertEquals(optionConfig.getTimeline()[2], originalIndex0)

        // Test if swapping back works
        optionConfig.swapTimelineOrder(2, 0)
        assertEquals(optionConfig.getTimeline()[2], originalIndex2)
        assertEquals(optionConfig.getTimeline()[0], originalIndex0)

        // Test if swapping operation will be ignored if invalid indexes are used
        val originalIndex1 = optionConfig.getTimeline()[1]
        optionConfig.swapTimelineOrder(0, 3)
        optionConfig.swapTimelineOrder(-1, Int.MAX_VALUE)
        assertEquals(optionConfig.getTimeline()[2], originalIndex2)
        assertEquals(optionConfig.getTimeline()[0], originalIndex0)
        assertEquals(optionConfig.getTimeline()[1], originalIndex1)
    }
    @Test
    fun testGetColumns(){
        val id1 = UUID.randomUUID()
        val id2 = UUID.randomUUID()

        // Test if new key will be mapped to new SingleValueColumn
        optionConfig.getColumns(id1)
        assertTrue(optionConfig.singleValueColumns.size == 1)
        optionConfig.getColumns(id2)
        assertTrue(optionConfig.singleValueColumns.size == 2)
        // Test if already used key won't create new map entry
        optionConfig.getColumns(id1)
        assertTrue(optionConfig.singleValueColumns.size == 2)
    }
}