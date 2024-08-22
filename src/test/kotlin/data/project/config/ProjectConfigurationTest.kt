package data.project.config

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

/**
 * Test class for ProjectConfiguration
 */
class ProjectConfigurationTest {

    private lateinit var projectConfig: ProjectConfiguration

    @BeforeEach
    fun setUp() {
        projectConfig = ProjectConfiguration()
    }

    @Test
    fun testAddSingleValue() {
        val initialSize = projectConfig.getSingleValues().size
        projectConfig.addSingleValue()
        val newSize = projectConfig.getSingleValues().size

        assertEquals(initialSize + 1, newSize)
    }

    @Test
    fun testRemoveSingleValue() {
        projectConfig.addSingleValue()
        projectConfig.addSingleValue()
        val initialSize = projectConfig.getSingleValues().size

        // Get a valid UUID key from the current projectConfig
        val validKey = projectConfig.getSingleValues().keys.first()
        val notValidKey = UUID.randomUUID()

        projectConfig.removeSingleValue(validKey)
        projectConfig.removeSingleValue(notValidKey)
        val newSize = projectConfig.getSingleValues().size

        // Ensure that the validKey is not in the projectConfig after removal
        assertFalse(projectConfig.getSingleValues().containsKey(validKey))
        assertEquals(initialSize - 1, newSize)
    }

    @Test
    fun testSwapSingleValueOrder() {
        projectConfig.addSingleValue()
        projectConfig.addSingleValue()
        val initialOrder = projectConfig.getSingleValueConfigOrder()

        val indexA = 0
        val indexB = 1

        // Get UUID keys from the current projectConfig
        val initialKeyA = initialOrder[indexA]
        val initialKeyB = initialOrder[indexB]

        // Swap Order of indexA and indexB
        projectConfig.swapSingleValueOrder(indexA, indexB)

        var newOrder = projectConfig.getSingleValueConfigOrder()

        // Ensure that the order has been swapped correctly
        assertEquals(initialKeyA, newOrder[indexB])
        assertEquals(initialKeyB, newOrder[indexA])

        projectConfig.swapSingleValueOrder(indexA, -1)
        newOrder = projectConfig.getSingleValueConfigOrder()
        assertEquals(initialKeyA, newOrder[indexB])
        assertEquals(initialKeyB, newOrder[indexA])

        projectConfig.swapSingleValueOrder(-1, indexB)
        newOrder = projectConfig.getSingleValueConfigOrder()
        assertEquals(initialKeyA, newOrder[indexB])
        assertEquals(initialKeyB, newOrder[indexA])

        projectConfig.swapSingleValueOrder(Int.MAX_VALUE, indexB)
        newOrder = projectConfig.getSingleValueConfigOrder()
        assertEquals(initialKeyA, newOrder[indexB])
        assertEquals(initialKeyB, newOrder[indexA])

        projectConfig.swapSingleValueOrder(indexA, Int.MAX_VALUE)
        newOrder = projectConfig.getSingleValueConfigOrder()
        assertEquals(initialKeyA, newOrder[indexB])
        assertEquals(initialKeyB, newOrder[indexA])
    }

    @Test
    fun testGetSituationConfig() {
        projectConfig.getOptionConfig("Test1")
        assertTrue(projectConfig.getOptionConfig().size == 1)
        projectConfig.getOptionConfig("Test2")
        assertTrue(projectConfig.getOptionConfig().size == 2)
        projectConfig.getOptionConfig("Test2")
        assertTrue(projectConfig.getOptionConfig().size == 2)
        assertEquals(projectConfig.getOptionConfig()["Test2"], projectConfig.getOptionConfig("Test2"))
    }
}