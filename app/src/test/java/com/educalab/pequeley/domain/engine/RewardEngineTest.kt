package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.BadgeCriteriaType
import com.educalab.pequeley.domain.model.BadgeModel
import com.educalab.pequeley.domain.model.GardenState
import org.junit.Assert.*
import org.junit.Test

class RewardEngineTest {

    private val engine = RewardEngine()

    private val badgeAgreements = BadgeModel("primer_acuerdo", "Primer Acuerdo", "d", 1, BadgeCriteriaType.AGREEMENTS_CREATED, 1)
    private val badgeSituations = BadgeModel("gran_observador", "Gran Observador", "d", 2, BadgeCriteriaType.SITUATIONS_COMPLETED, 5)

    @Test
    fun `evaluateNewBadges awards badge when criteria met`() {
        val stats = PlayerStats(agreementsCreated = 1)
        val result = engine.evaluateNewBadges(listOf(badgeAgreements), emptySet(), stats)
        assertEquals(1, result.size)
        assertEquals("primer_acuerdo", result[0].code)
    }

    @Test
    fun `evaluateNewBadges does not award when criteria not met`() {
        val stats = PlayerStats(agreementsCreated = 0)
        val result = engine.evaluateNewBadges(listOf(badgeAgreements), emptySet(), stats)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `evaluateNewBadges excludes already earned badges (no duplicates)`() {
        val stats = PlayerStats(agreementsCreated = 5)
        val result = engine.evaluateNewBadges(listOf(badgeAgreements), setOf("primer_acuerdo"), stats)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `evaluateNewBadges handles multiple badges independently`() {
        val stats = PlayerStats(agreementsCreated = 1, situationsCompleted = 5)
        val result = engine.evaluateNewBadges(listOf(badgeAgreements, badgeSituations), emptySet(), stats)
        assertEquals(2, result.size)
    }

    @Test
    fun `evaluateNewBadges with empty badge list returns empty`() {
        val stats = PlayerStats(agreementsCreated = 100)
        val result = engine.evaluateNewBadges(emptyList(), emptySet(), stats)
        assertTrue(result.isEmpty())
    }

    @Test
    fun `evaluateNewBadges checks every criteria type without crashing`() {
        val allTypes = BadgeCriteriaType.values().mapIndexed { index, type ->
            BadgeModel("badge_$index", "t", "d", 1, type, 1)
        }
        val stats = PlayerStats(
            situationsCompleted = 1, storiesCompleted = 1, agreementsCreated = 1,
            challengesCompleted = 1, roomsUnlocked = 1, positiveConsequences = 1,
            gardenLevel = 1, totalXp = 1
        )
        val result = engine.evaluateNewBadges(allTypes, emptySet(), stats)
        assertEquals(allTypes.size, result.size)
    }

    @Test
    fun `applyGardenImpact with zero impact returns same state`() {
        val garden = GardenState(growthLevel = 2, flowers = 1)
        val result = engine.applyGardenImpact(garden, 0, 20)
        assertEquals(garden, result)
    }

    @Test
    fun `applyGardenImpact accumulates below threshold without growing`() {
        val garden = GardenState()
        val result = engine.applyGardenImpact(garden, 4, 0)
        assertEquals(0, result.growthLevel)
    }

    @Test
    fun `applyGardenImpact grows one level at 10 accumulated points`() {
        val garden = GardenState()
        val result = engine.applyGardenImpact(garden, 6, 4)
        assertEquals(1, result.growthLevel)
    }

    @Test
    fun `applyGardenImpact never grows past level 10 cap`() {
        val garden = GardenState(growthLevel = 10)
        val result = engine.applyGardenImpact(garden, 50, 100)
        assertEquals(10, result.growthLevel)
    }

    @Test
    fun `applyGardenImpact distributes decorations across flowers paths and animals`() {
        var garden = GardenState()
        var raw = 0
        repeat(3) {
            garden = engine.applyGardenImpact(garden, 10, raw)
            raw += 10
        }
        assertEquals(3, garden.growthLevel)
        assertEquals(1, garden.flowers)
        assertEquals(1, garden.paths)
        assertEquals(1, garden.animals)
    }

    @Test
    fun `applyGardenImpact with negative impact can reduce growth level`() {
        val garden = GardenState(growthLevel = 2)
        val result = engine.applyGardenImpact(garden, -25, 20)
        assertTrue(result.growthLevel < garden.growthLevel)
    }
}
