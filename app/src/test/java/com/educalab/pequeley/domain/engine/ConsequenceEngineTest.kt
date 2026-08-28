package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.ConsequenceModel
import com.educalab.pequeley.domain.model.DecisionModel
import org.junit.Assert.*
import org.junit.Test

class ConsequenceEngineTest {

    private val engine = ConsequenceEngine()

    private val positiveDecision = DecisionModel(1, "Escuchar", "desc",
        listOf(ConsequenceModel("Todo mejora.", true, 5, 10)))
    private val negativeDecision = DecisionModel(2, "Ignorar", "desc",
        listOf(ConsequenceModel("La discusión sigue.", false, -2, 2)))
    private val decisionNoConsequence = DecisionModel(3, "Vacío", "desc", emptyList())

    @Test
    fun `firstConsequence returns the first item`() {
        val consequence = engine.firstConsequence(positiveDecision)
        assertTrue(consequence.isPositive)
    }

    @Test(expected = InvalidConsequenceStateException::class)
    fun `firstConsequence throws when list is empty`() {
        engine.firstConsequence(decisionNoConsequence)
    }

    @Test
    fun `compare selects the positive decision as better`() {
        val result = engine.compare(positiveDecision, negativeDecision)
        assertEquals(positiveDecision, result.betterChoice)
    }

    @Test
    fun `compare returns null when both decisions are equally positive`() {
        val altPositive = DecisionModel(4, "Compartir", "desc",
            listOf(ConsequenceModel("También mejora.", true, 4, 8)))
        val result = engine.compare(positiveDecision, altPositive)
        assertNull(result.betterChoice)
    }

    @Test
    fun `compare returns null when both decisions are equally negative`() {
        val altNegative = DecisionModel(5, "Gritar", "desc",
            listOf(ConsequenceModel("Empeora.", false, -3, 1)))
        val result = engine.compare(negativeDecision, altNegative)
        assertNull(result.betterChoice)
    }

    @Test
    fun `reflectionFor positive consequence is encouraging and not a bare label`() {
        val text = engine.reflectionFor(engine.firstConsequence(positiveDecision))
        assertNotEquals("Correcto", text)
        assertTrue(text.length > 10)
    }

    @Test
    fun `reflectionFor negative consequence offers a path forward, not a punishment`() {
        val text = engine.reflectionFor(engine.firstConsequence(negativeDecision))
        assertNotEquals("Incorrecto", text)
        assertTrue(text.contains("conversando") || text.contains("escuchando"))
    }
}
