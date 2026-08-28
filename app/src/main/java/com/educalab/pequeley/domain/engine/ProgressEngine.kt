package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.HouseRoom
import com.educalab.pequeley.domain.model.RoomModuleState
import com.educalab.pequeley.domain.model.RoomProgress

/**
 * Motor de progresión: calcula nivel a partir de XP, decide qué
 * habitaciones deben desbloquearse y en qué estado visual se muestra
 * cada módulo (bloqueado / disponible / iniciado / completado / dominado).
 */
class ProgressEngine {

    companion object {
        // Curva de nivel simple y predecible: nivel N requiere 100*(N-1) XP acumulada.
        const val XP_PER_LEVEL = 100

        /** Umbral de situaciones completadas en una sala para considerarla "dominada". */
        const val MASTERY_THRESHOLD = 5
    }

    fun levelForXp(totalXp: Int): Int {
        if (totalXp < 0) return 1
        return (totalXp / XP_PER_LEVEL) + 1
    }

    fun xpToNextLevel(totalXp: Int): Int {
        val level = levelForXp(totalXp)
        val nextLevelXp = level * XP_PER_LEVEL
        return (nextLevelXp - totalXp).coerceAtLeast(0)
    }

    fun levelProgressRatio(totalXp: Int): Float {
        val xpIntoLevel = totalXp % XP_PER_LEVEL
        return xpIntoLevel.toFloat() / XP_PER_LEVEL.toFloat()
    }

    /** Decide si una sala debe pasar de bloqueada a disponible dado el nivel actual. */
    fun shouldUnlock(room: HouseRoom, currentLevel: Int): Boolean =
        !room.unlocked && currentLevel >= room.requiredLevelToUnlock

    fun stateFor(room: HouseRoom, progress: RoomProgress?): RoomModuleState {
        if (!room.unlocked) return RoomModuleState.LOCKED
        val p = progress ?: return RoomModuleState.AVAILABLE
        return when {
            p.masteryLevel >= MASTERY_THRESHOLD -> RoomModuleState.MASTERED
            p.situationsCompleted > 0 && p.situationsCompleted >= 3 -> RoomModuleState.COMPLETED
            p.situationsCompleted > 0 -> RoomModuleState.STARTED
            else -> RoomModuleState.AVAILABLE
        }
    }

    /** Progreso global 0..1 de la casa completa, usado en la Puerta Principal. */
    fun overallHouseProgress(rooms: List<HouseRoom>, progresses: List<RoomProgress>): Float {
        if (rooms.isEmpty()) return 0f
        val byCode = progresses.associateBy { it.roomCode }
        val states = rooms.map { stateFor(it, byCode[it.code]) }
        val completedOrBetter = states.count {
            it == RoomModuleState.COMPLETED || it == RoomModuleState.MASTERED
        }
        return completedOrBetter.toFloat() / rooms.size.toFloat()
    }
}
