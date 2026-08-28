package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class StoryEngineTest {

    private lateinit var engine: StoryEngine
    private lateinit var story: StoryModel

    @Before
    fun setUp() {
        engine = StoryEngine()
        val choiceToScene1 = StoryChoiceModel("Hablar con Ana", 1, false, "Ana explica lo que pasó.")
        val choiceEndingA = StoryChoiceModel("Pedir disculpas", null, true, "Todos se sienten mejor.")
        val scene0 = StorySceneModel(1, 0, "Dos amigos discuten por un juguete.", 1, listOf(choiceToScene1, choiceEndingA))
        val choiceEndingB = StoryChoiceModel("Proponer turnos", null, true, "Deciden compartir por turnos.")
        val scene1 = StorySceneModel(2, 1, "Ana cuenta su versión.", 2, listOf(choiceEndingB))
        story = StoryModel("story_juguete", "El juguete prestado", "resumen", MechanicType.DIALOGUE_CHOICE, 1, listOf(scene0, scene1))
    }

    @Test
    fun `start selects the lowest order scene`() {
        val session = engine.start(story)
        assertEquals(0, session.currentSceneOrder)
    }

    @Test(expected = InvalidStoryStateException::class)
    fun `start throws for story without scenes`() {
        engine.start(story.copy(scenes = emptyList()))
    }

    @Test
    fun `choose navigates to the linked scene`() {
        var session = engine.start(story)
        val choice = session.currentScene!!.choices.first { it.leadsToSceneOrder == 1 }
        session = engine.choose(session, choice)
        assertEquals(1, session.currentSceneOrder)
        assertFalse(session.finished)
    }

    @Test
    fun `choose with isEnding finishes the story`() {
        var session = engine.start(story)
        val ending = session.currentScene!!.choices.first { it.isEnding }
        session = engine.choose(session, ending)
        assertTrue(session.finished)
    }

    @Test(expected = InvalidStoryStateException::class)
    fun `choose after finished throws`() {
        var session = engine.start(story)
        val ending = session.currentScene!!.choices.first { it.isEnding }
        session = engine.choose(session, ending)
        engine.choose(session, ending)
    }

    @Test(expected = InvalidStoryStateException::class)
    fun `choose with a foreign choice throws`() {
        val session = engine.start(story)
        val foreign = StoryChoiceModel("Opción inventada", 5, false, "x")
        engine.choose(session, foreign)
    }

    @Test
    fun `visitedOrders accumulates scene history`() {
        var session = engine.start(story)
        val choice = session.currentScene!!.choices.first { it.leadsToSceneOrder == 1 }
        session = engine.choose(session, choice)
        assertEquals(listOf(0, 1), session.visitedOrders)
    }

    @Test
    fun `countEndings counts all ending choices across scenes`() {
        assertEquals(2, engine.countEndings(story))
    }

    @Test
    fun `isComplete false before reaching an ending`() {
        val session = engine.start(story)
        assertFalse(engine.isComplete(session))
    }
}
