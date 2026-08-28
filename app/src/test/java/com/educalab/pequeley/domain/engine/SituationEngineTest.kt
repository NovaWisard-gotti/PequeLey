package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SituationEngineTest {

    private lateinit var engine: SituationEngine
    private lateinit var decisionGood: DecisionModel
    private lateinit var decisionBad: DecisionModel
    private lateinit var situation: Situation

    @Before
    fun setUp() {
        engine = SituationEngine()
        decisionGood = DecisionModel(1, "Escuchar", "Escuchar a la otra persona",
            listOf(ConsequenceModel("Los dos entienden mejor lo ocurrido.", true, 5, 10)))
        decisionBad = DecisionModel(2, "Ignorar", "No escuchar",
            listOf(ConsequenceModel("La discusión continúa.", false, -2, 2)))
        val step0 = SituationStepModel(0, StepType.NARRATION, "Dos niños quieren el mismo juego.", 1)
        val step1 = SituationStepModel(1, StepType.DECISION, "¿Qué haces?", 2, listOf(decisionGood, decisionBad))
        situation = Situation("sit_turno", "convivencia", "El turno de Ana", "resumen", 1, MechanicType.DIALOGUE_CHOICE, 1, listOf(step0, step1))
    }

    @Test
    fun `start places session at first step`() {
        val session = engine.start(situation)
        assertEquals(0, session.currentIndex)
        assertFalse(session.finished)
    }

    @Test(expected = InvalidSituationStateException::class)
    fun `start throws for situation without steps`() {
        val empty = situation.copy(steps = emptyList())
        engine.start(empty)
    }

    @Test
    fun `advance moves to next step`() {
        val session = engine.start(situation)
        val advanced = engine.advance(session)
        assertEquals(1, advanced.currentIndex)
        assertFalse(advanced.finished)
    }

    @Test
    fun `advance on last step marks finished`() {
        var session = engine.start(situation)
        session = engine.advance(session) // step 1
        session = engine.advance(session) // beyond last -> finished
        assertTrue(session.finished)
    }

    @Test(expected = InvalidSituationStateException::class)
    fun `advance after finished throws`() {
        var session = engine.start(situation)
        session = engine.advance(session)
        session = engine.advance(session)
        engine.advance(session)
    }

    @Test
    fun `chooseDecision records decision and advances`() {
        var session = engine.start(situation)
        session = engine.advance(session) // move to decision step
        session = engine.chooseDecision(session, decisionGood)
        assertTrue(session.decisionsMade.contains(1L))
        assertTrue(session.finished)
    }

    @Test(expected = InvalidSituationStateException::class)
    fun `chooseDecision with foreign decision throws`() {
        var session = engine.start(situation)
        session = engine.advance(session)
        engine.chooseDecision(session, DecisionModel(999, "x", "x", emptyList()))
    }

    @Test(expected = InvalidSituationStateException::class)
    fun `chooseDecision after finished throws (double tap protection)`() {
        var session = engine.start(situation)
        session = engine.advance(session)
        session = engine.chooseDecision(session, decisionGood)
        engine.chooseDecision(session, decisionGood)
    }

    @Test
    fun `sessionXp sums consequence xp`() {
        var session = engine.start(situation)
        session = engine.advance(session)
        session = engine.chooseDecision(session, decisionGood)
        assertEquals(10, engine.sessionXp(session))
    }

    @Test
    fun `sessionXp is zero with no consequences shown`() {
        val session = engine.start(situation)
        assertEquals(0, engine.sessionXp(session))
    }

    @Test
    fun `positivityRatio is zero with empty consequences`() {
        val session = engine.start(situation)
        assertEquals(0f, engine.positivityRatio(session))
    }

    @Test
    fun `positivityRatio reflects negative choice`() {
        var session = engine.start(situation)
        session = engine.advance(session)
        session = engine.chooseDecision(session, decisionBad)
        assertEquals(0f, engine.positivityRatio(session))
    }

    @Test
    fun `progressRatio increases as steps complete`() {
        var session = engine.start(situation)
        assertEquals(0f, engine.progressRatio(session))
        session = engine.advance(session)
        assertEquals(0.5f, engine.progressRatio(session))
        session = engine.chooseDecision(session, decisionGood)
        assertEquals(1f, engine.progressRatio(session))
    }

    @Test
    fun `decision with no consequences does not crash session`() {
        val decisionNoConsequence = DecisionModel(3, "Callar", "No decir nada", emptyList())
        val stepWithBareDecision = SituationStepModel(0, StepType.DECISION, "¿Qué haces?", 1, listOf(decisionNoConsequence))
        val minimalSituation = situation.copy(steps = listOf(stepWithBareDecision))
        var session = engine.start(minimalSituation)
        session = engine.chooseDecision(session, decisionNoConsequence)
        assertEquals(0, engine.sessionXp(session))
        assertTrue(session.finished)
    }
}
