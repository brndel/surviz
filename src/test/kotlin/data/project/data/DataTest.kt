package data.project.data

import androidx.compose.ui.graphics.ImageBitmap
import data.io.importer.Importer
import data.io.importer.NgeneImporter
import data.project.Project
import data.project.ProjectData
import data.project.config.SituationConfig
import data.resources.exceptions.FileTypeException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.lang.Thread.sleep
import java.nio.file.Path

class DataTest {
    @Test
    fun blockTest(){
        val block1: Block = Block(1)
        val block2: Block = Block (2)
        val block3: Block = Block (2)
        block3.situations.add(Situation(0, listOf<SituationOption>()))
        val int: Int = 10

        // equals() tests
        // test for same block
        assertTrue(block1.equals(block1))
        // test for not BLock type
        assertFalse(block1.equals(int))
        // test for wrong id
        assertFalse(block1.equals(block2))
        // test for options size
        assertFalse(block2.equals(block3))
        // test for options
        val situation: Situation = Situation(0, listOf<SituationOption>(SituationOption("String", mapOf())))
        block2.situations.add(situation)
        assertFalse(block2.equals(block3))
        // test for equal
        val block4: Block = Block(2)
        block4.situations.add(situation)
        assertTrue(block2.equals(block4))
    }

    @Test
    fun situationTest(){
        val situation1 = Situation(1, listOf())
        val situation2 = Situation(2, listOf())
        val situation3 = Situation(1, listOf(SituationOption("String", mapOf())))
        val situation4 = Situation(1, listOf(SituationOption("String2", mapOf())))

        val int = 10

        // equals tests
        // test for same situation
        assertTrue(situation1.equals(situation1))

        assertFalse(situation1.equals(situation2))
        assertFalse(situation1.equals(int))
        assertFalse(situation3.equals(situation4))
    }

    @Test
    fun situationOptionTest(){
        val situationOption1 = SituationOption("String1", mapOf())
        val situationOption2 = SituationOption("String2", mapOf())
        val situationOption3 = SituationOption("String2", mapOf(Pair("a", 1.0)))
        val situationOption4 = SituationOption("String2", mapOf(Pair("b", 1.0)))
        val situationOption5 = SituationOption("String2", mapOf(Pair("b", 2.0)))
        val situationOption6 = SituationOption("String2", mapOf(Pair("b", 2.0), Pair("c", 2.0)))

        val int = 10

        // equals tests for situationOption
        assertTrue(situationOption2.equals(situationOption2))
        assertFalse(situationOption1.equals(situationOption2))
        assertFalse(situationOption1.equals(int))
        assertFalse(situationOption3.equals(situationOption4))
        assertFalse(situationOption5.equals(situationOption4))
        assertFalse(situationOption5.equals(situationOption6))
    }

    @Test
    fun dataSchemeTest(){
        val dataScheme1 = DataScheme(mutableListOf(DataSchemeOption("String", listOf())))
        val dataScheme2 = DataScheme(mutableListOf())
        val dataScheme3 = DataScheme(mutableListOf(DataSchemeOption("String2", listOf())))

        // compareTo tests for DataScheme
        dataScheme1.compareTo(dataScheme2)
        dataScheme1.compareTo(dataScheme3)
    }

    @Test
    fun dataSchemeOptionTest(){
        val int = 10
        val dataSchemeOption1 = DataSchemeOption("String", listOf())
        val dataSchemeOption2 = DataSchemeOption("String", listOf("String","String2"))
        val dataSchemeOption3 = DataSchemeOption("String", listOf("String2"))
        val dataSchemeOption4 = DataSchemeOption("String", listOf("String3","String4"))

        //equals tests for dataSchemeOption
        assertFalse(dataSchemeOption1.equals(int))
        assertFalse(dataSchemeOption1.equals(dataSchemeOption2))
        assertFalse(dataSchemeOption2.equals(dataSchemeOption3))
        assertFalse(dataSchemeOption2.equals(dataSchemeOption4))
    }

    @Test
    fun projectDataTest(){
        // tests for getSituation()
        val projectData1 = ProjectData(DataScheme(mutableListOf()), listOf())
        // getSituations() test for null (block and situation don't exist)
        assertNull(projectData1.getSituations(1, 1))
        // getSituations test for block and situation exist
        val situation = Situation(1, listOf())
        val projectDataWithBlockAndSituation = ProjectData(DataScheme(mutableListOf()), listOf(Block(1, listOf(situation))))
        assertEquals(projectDataWithBlockAndSituation.getSituations(0, 0), situation)

        val int = 10
        // tests for equals()
        // test for wrong type
        assertFalse(projectData1.equals(int))
        // test for DateScheme compareTo is false
        val projectData2 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", listOf("String")))), listOf())
        assertFalse(projectData2.equals(projectData1))
        //test for different block size
        val projectData3 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", listOf("String")))), listOf(Block(1)))
        val projectData4 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", listOf("String")))), listOf(Block(1), Block(2)))
        assertFalse(projectData3.equals(projectData4))
        //test for same block size but different blocks
        val projectData5 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", listOf("String")))), listOf(Block(1), Block(3)))
        val projectData6 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", listOf("String")))), listOf(Block(1), Block(2)))
        assertFalse(projectData5.equals(projectData6))
    }
}

