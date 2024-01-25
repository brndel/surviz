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

    /**
     * unfinished test.
     * get initial SingleValueOrder with getter
     */
    @Test
    fun testSwapSingleValueOrder() {
        projectConfig.addSingleValue()
        projectConfig.addSingleValue()
        //val initialOrder

        val indexA = 0
        val indexB = 1

        // Get valid UUID keys from the current projectConfig
        //val validKeyA = initialOrder[indexA]
        //val validKeyB = initialOrder[indexB]

        projectConfig.swapSingleValueOrder(indexA, indexB)
        //val newOrder

        // Ensure that the order has been swapped correctly
        //assertEquals(validKeyA, newOrder[indexB])
        //assertEquals(validKeyB, newOrder[indexA])
    }
}