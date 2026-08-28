package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.BadgeCriteriaType
import com.educalab.pequeley.domain.model.BadgeModel
import com.educalab.pequeley.domain.model.GardenState

/** Estadísticas acumuladas del jugador usadas para evaluar recompensas. */
data class PlayerStats(
    val situationsCompleted: Int = 0,
    val storiesCompleted: Int = 0,
    val agreementsCreated: Int = 0,
    val challengesCompleted: Int = 0,
    val roomsUnlocked: Int = 0,
    val positiveConsequences: Int = 0,
    val gardenLevel: Int = 0,
    val totalXp: Int = 0
)

/**
 * Motor de recompensas: decide qué insignias nuevas corresponde otorgar
 * y cómo crece el jardín visual en función de las acciones reales del niño.
 * Las recompensas siempre están vinculadas a acciones registradas
 * (nunca a compras, tiempo transcurrido ni presión social).
 */
class RewardEngine {

    /** Devuelve solo las insignias que el jugador ya cumple y aún no tiene. */
    fun evaluateNewBadges(allBadges: List<BadgeModel>, alreadyEarnedCodes: Set<String>, stats: PlayerStats): List<BadgeModel> {
        return allBadges.filter { badge ->
            badge.code !in alreadyEarnedCodes && meetsCriteria(badge, stats)
        }
    }

    private fun meetsCriteria(badge: BadgeModel, stats: PlayerStats): Boolean {
        val value = when (badge.criteriaType) {
            BadgeCriteriaType.SITUATIONS_COMPLETED -> stats.situationsCompleted
            BadgeCriteriaType.STORIES_COMPLETED -> stats.storiesCompleted
            BadgeCriteriaType.AGREEMENTS_CREATED -> stats.agreementsCreated
            BadgeCriteriaType.CHALLENGES_COMPLETED -> stats.challengesCompleted
            BadgeCriteriaType.ROOMS_UNLOCKED -> stats.roomsUnlocked
            BadgeCriteriaType.POSITIVE_CONSEQUENCES -> stats.positiveConsequences
            BadgeCriteriaType.GARDEN_LEVEL -> stats.gardenLevel
            BadgeCriteriaType.TOTAL_XP -> stats.totalXp
        }
        return value >= badge.criteriaValue
    }

    /**
     * Calcula el nuevo estado del jardín sumando el impacto de una consecuencia.
     * Cada 10 puntos de impacto acumulado sube growthLevel en 1 (hasta 10),
     * y cada 3 niveles desbloquea una nueva flor/camino/animal alternadamente.
     */
    fun applyGardenImpact(current: GardenState, impactPoints: Int, accumulatedRaw: Int): GardenState {
        if (impactPoints == 0) return current
        val newRaw = (accumulatedRaw + impactPoints).coerceAtLeast(0)
        val newGrowth = (newRaw / 10).coerceIn(0, 10)
        val grownSteps = newGrowth - current.growthLevel
        if (grownSteps <= 0) {
            return current.copy(growthLevel = newGrowth)
        }
        var flowers = current.flowers
        var paths = current.paths
        var animals = current.animals
        repeat(grownSteps) { stepIndex ->
            when ((current.growthLevel + stepIndex) % 3) {
                0 -> flowers++
                1 -> paths++
                else -> animals++
            }
        }
        return GardenState(growthLevel = newGrowth, flowers = flowers, paths = paths, animals = animals)
    }
}
