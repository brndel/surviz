package data.project.config

import androidx.compose.runtime.mutableDoubleStateOf
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
 * Test class for SingleValueIcon
 */
class SingleValueIconTest {
    @Test
    fun testGetIcon(){
        val levels = SnapshotStateList<SingleValueIconLevel>()
        val levelsEmpty = SnapshotStateList<SingleValueIconLevel>()
        levels.add(SingleValueIconLevel(mutableStateOf("2.5"), mutableDoubleStateOf(2.5)))
        levels.add(SingleValueIconLevel(mutableStateOf("10.0"), mutableDoubleStateOf(10.0)))
        levels.add(SingleValueIconLevel(mutableStateOf("5.0"), mutableDoubleStateOf(5.0)))
        levels.add(SingleValueIconLevel(mutableStateOf("7.5"), mutableDoubleStateOf(7.5)))
        val singleValueIcon1 = SingleValueIcon(mutableStateOf("base"), levels)
        val singleValueIcon2 = SingleValueIcon(mutableStateOf("base"), levelsEmpty)
        val singleValueIcon3 = SingleValueIcon(mutableStateOf(null), levels)
        val singleValueIcon4 = SingleValueIcon(mutableStateOf(null), levelsEmpty)

        assertEquals("base", singleValueIcon1.getIcon(-5.0))
        assertEquals("base", singleValueIcon1.getIcon(0.0))
        assertEquals("base", singleValueIcon1.getIcon(2.0))
        assertEquals("2.5", singleValueIcon1.getIcon(2.5))
        assertEquals("5.0", singleValueIcon1.getIcon(5.0))
        assertEquals("5.0", singleValueIcon1.getIcon(7.0))
        assertEquals("7.5", singleValueIcon1.getIcon(7.5))
        assertEquals("7.5", singleValueIcon1.getIcon(7.6))
        assertEquals("10.0", singleValueIcon1.getIcon(10.0))
        assertEquals("10.0", singleValueIcon1.getIcon(15.0))

        assertEquals("base", singleValueIcon2.getIcon(-5.0))
        assertEquals("base", singleValueIcon2.getIcon(0.0))
        assertEquals("base", singleValueIcon2.getIcon(2.0))
        assertEquals("base", singleValueIcon2.getIcon(2.5))
        assertEquals("base", singleValueIcon2.getIcon(5.0))
        assertEquals("base", singleValueIcon2.getIcon(7.0))
        assertEquals("base", singleValueIcon2.getIcon(7.5))
        assertEquals("base", singleValueIcon2.getIcon(7.6))
        assertEquals("base", singleValueIcon2.getIcon(10.0))
        assertEquals("base", singleValueIcon2.getIcon(15.0))

        assertEquals(null, singleValueIcon3.getIcon(-5.0))
        assertEquals(null, singleValueIcon3.getIcon(0.0))
        assertEquals(null, singleValueIcon3.getIcon(2.0))
        assertEquals("2.5", singleValueIcon3.getIcon(2.5))
        assertEquals("5.0", singleValueIcon3.getIcon(5.0))
        assertEquals("5.0", singleValueIcon3.getIcon(7.0))
        assertEquals("7.5", singleValueIcon3.getIcon(7.5))
        assertEquals("7.5", singleValueIcon3.getIcon(7.6))
        assertEquals("10.0", singleValueIcon3.getIcon(10.0))
        assertEquals("10.0", singleValueIcon3.getIcon(15.0))

        assertEquals(null, singleValueIcon4.getIcon(-5.0))
        assertEquals(null, singleValueIcon4.getIcon(0.0))
        assertEquals(null, singleValueIcon4.getIcon(2.0))
        assertEquals(null, singleValueIcon4.getIcon(2.5))
        assertEquals(null, singleValueIcon4.getIcon(15.0))
    }
}