package data.project.data

import data.project.ProjectData
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class DataTest {
    @Test
    fun blockTest(){
        val block1 = Block(1)
        val block2 = Block (2)
        val block3 = Block (2)
        block3.addSituation(listOf())

        // equals() tests
        // test for wrong id
        assertFalse(block1 == block2)
        // test for options size
        assertFalse(block2 == block3)
        // test for options
        val options = listOf(SituationOption("String", mapOf()))
        block2.addSituation(options)
        assertFalse(block2 == block3)
        // test for equal
        val block4: Block = Block(2)
        block4.addSituation(options)
        assertTrue(block2 == block4)
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
        val dataScheme1 = DataScheme(mutableListOf(DataSchemeOption("String", setOf())))
        val dataScheme2 = DataScheme(mutableListOf())
        val dataScheme3 = DataScheme(mutableListOf(DataSchemeOption("String2", setOf())))

        // compareTo tests for DataScheme
        dataScheme1.compareTo(dataScheme2)
        dataScheme1.compareTo(dataScheme3)
    }

    @Test
    fun dataSchemeOptionTest(){
        val int = 10
        val dataSchemeOption1 = DataSchemeOption("String", setOf())
        val dataSchemeOption2 = DataSchemeOption("String", setOf("String","String2"))
        val dataSchemeOption3 = DataSchemeOption("String", setOf("String2"))
        val dataSchemeOption4 = DataSchemeOption("String", setOf("String3","String4"))

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
        assertNull(projectData1.getSituation(1, 1))
        // getSituations test for block and situation exist
        val situation = Situation(1, listOf())
        val projectDataWithBlockAndSituation = ProjectData(DataScheme(mutableListOf()), listOf(Block(1, listOf(situation))))
        assertEquals(situation, projectDataWithBlockAndSituation.getSituation(1, 1))

        val int = 10
        // tests for equals()
        // test for wrong type
        assertFalse(projectData1.equals(int))
        // test for DateScheme compareTo is false
        val projectData2 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", setOf("String")))), listOf())
        assertFalse(projectData2.equals(projectData1))
        //test for different block size
        val projectData3 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", setOf("String")))), listOf(Block(1)))
        val projectData4 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", setOf("String")))), listOf(Block(1), Block(2)))
        assertFalse(projectData3.equals(projectData4))
        //test for same block size but different blocks
        val projectData5 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", setOf("String")))), listOf(Block(1), Block(3)))
        val projectData6 = ProjectData(DataScheme(mutableListOf(DataSchemeOption("String1", setOf("String")))), listOf(Block(1), Block(2)))
        assertFalse(projectData5.equals(projectData6))
    }
}

