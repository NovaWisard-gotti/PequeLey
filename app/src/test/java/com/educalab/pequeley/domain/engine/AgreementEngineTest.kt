package com.educalab.pequeley.domain.engine

import org.junit.Assert.*
import org.junit.Test

class AgreementEngineTest {

    private val engine = AgreementEngine()

    @Test
    fun `build creates an agreement with valid data`() {
        val agreement = engine.build("Compartimos el juego", "sit_turno",
            listOf("conversar", "escuchar", "proponer_turnos", "aceptar_acuerdo"), 1000L)
        assertEquals(4, agreement.items.size)
        assertEquals("Compartimos el juego", agreement.title)
    }

    @Test(expected = InvalidAgreementException::class)
    fun `build rejects empty title`() {
        engine.build("   ", null, listOf("conversar", "escuchar"), 1000L)
    }

    @Test(expected = InvalidAgreementException::class)
    fun `build rejects empty item list`() {
        engine.build("Título", null, emptyList(), 1000L)
    }

    @Test(expected = InvalidAgreementException::class)
    fun `build rejects a single item (below minimum)`() {
        engine.build("Título", null, listOf("conversar"), 1000L)
    }

    @Test(expected = InvalidAgreementException::class)
    fun `build rejects more than MAX_ITEMS`() {
        val tooMany = listOf("conversar", "escuchar", "esperar", "compartir", "pedir_permiso", "reparar", "ayudar")
        engine.build("Título", null, tooMany, 1000L)
    }

    @Test(expected = InvalidAgreementException::class)
    fun `build rejects unknown symbol codes`() {
        engine.build("Título", null, listOf("conversar", "volar"), 1000L)
    }

    @Test
    fun `isSolidAgreement true when listening plus closure present`() {
        val agreement = engine.build("Título", null, listOf("escuchar", "aceptar_acuerdo"), 1000L)
        assertTrue(engine.isSolidAgreement(agreement))
    }

    @Test
    fun `isSolidAgreement false without listening`() {
        val agreement = engine.build("Título", null, listOf("esperar", "aceptar_acuerdo"), 1000L)
        assertFalse(engine.isSolidAgreement(agreement))
    }

    @Test
    fun `isSolidAgreement false without closure`() {
        val agreement = engine.build("Título", null, listOf("escuchar", "esperar"), 1000L)
        assertFalse(engine.isSolidAgreement(agreement))
    }

    @Test
    fun `combineProposals removes consecutive duplicates`() {
        val combined = engine.combineProposals(listOf("conversar", "escuchar"), listOf("escuchar", "reparar"))
        assertEquals(listOf("conversar", "escuchar", "reparar"), combined)
    }

    @Test
    fun `combineProposals caps result at MAX_ITEMS`() {
        val a = listOf("conversar", "escuchar", "esperar", "compartir")
        val b = listOf("pedir_permiso", "reparar", "ayudar")
        val combined = engine.combineProposals(a, b)
        assertTrue(combined.size <= AgreementEngine.MAX_ITEMS)
    }

    @Test
    fun `build trims surrounding whitespace from title`() {
        val agreement = engine.build("  Compartir el juego  ", null, listOf("conversar", "escuchar"), 1000L)
        assertEquals("Compartir el juego", agreement.title)
    }
}
