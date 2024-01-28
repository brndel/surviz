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
 * Test class for SingleValueIcon
 */
class SingleValueIconTest {
    @Test
    fun testGetIcon(){
        val levels = SnapshotStateList<SingleValueIconLevel?>()
        levels.add(SingleValueIconLevel(mutableStateOf("0.0"), mutableStateOf(0.0)))
        levels.add(SingleValueIconLevel(mutableStateOf("10.0"), mutableStateOf(10.0)))
        levels.add(SingleValueIconLevel(mutableStateOf("5.0"), mutableStateOf(5.0)))
        levels.add(SingleValueIconLevel(mutableStateOf("7.5"), mutableStateOf(7.5)))
        val singleValueIcon = SingleValueIcon(levels)
        assertEquals("0.0", singleValueIcon.getIcon(-5.0))
        assertEquals("0.0", singleValueIcon.getIcon(0.0))
        assertEquals("0.0", singleValueIcon.getIcon(2.5))
        assertEquals("5.0", singleValueIcon.getIcon(5.0))
        assertEquals("5.0", singleValueIcon.getIcon(7.0))
        assertEquals("7.5", singleValueIcon.getIcon(7.5))
        assertEquals("7.5", singleValueIcon.getIcon(7.6))
        assertEquals("10.0", singleValueIcon.getIcon(10.0))
        assertEquals("10.0", singleValueIcon.getIcon(15.0))
    }
}