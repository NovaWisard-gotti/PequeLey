package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.ConsequenceModel
import com.educalab.pequeley.domain.model.DecisionModel
import com.educalab.pequeley.domain.model.Situation
import com.educalab.pequeley.domain.model.SituationStepModel

class InvalidSituationStateException(message: String) : Exception(message)

/**
 * Estado inmutable de una sesión de situación en curso.
 * `currentIndex` apunta al paso activo dentro de `situation.steps`.
 */
data class SituationSession(
    val situation: Situation,
    val currentIndex: Int = 0,
    val decisionsMade: List<Long> = emptyList(),
    val consequencesShown: List<ConsequenceModel> = emptyList(),
    val finished: Boolean = false
) {
    val currentStep: SituationStepModel?
        get() = situation.steps.getOrNull(currentIndex)

    val totalSteps: Int get() = situation.steps.size
}

/**
 * Motor de dominio para "vivir" una situación cotidiana:
 * observar -> comprender -> actuar -> observar consecuencias -> reflexionar.
 * No depende de Android ni de Room: es 100% testeable en JVM puro.
 */
class SituationEngine {

    fun start(situation: Situation): SituationSession {
        if (situation.steps.isEmpty()) {
            throw InvalidSituationStateException("La situación '${situation.code}' no tiene pasos definidos.")
        }
        return SituationSession(situation = situation, currentIndex = 0)
    }

    /** Avanza sin tomar decisión (pasos de narración/observación). */
    fun advance(session: SituationSession): SituationSession {
        if (session.finished) {
            throw InvalidSituationStateException("La situación ya finalizó; no se puede avanzar más.")
        }
        val nextIndex = session.currentIndex + 1
        return if (nextIndex >= session.totalSteps) {
            session.copy(finished = true)
        } else {
            session.copy(currentIndex = nextIndex)
        }
    }

    /**
     * Registra una decisión tomada en el paso actual y aplica su primera
     * consecuencia disponible (si existe). Lanza excepción si la decisión
     * no pertenece al paso activo, evitando estados inválidos.
     */
    fun chooseDecision(session: SituationSession, decision: DecisionModel): SituationSession {
        if (session.finished) {
            throw InvalidSituationStateException("No se pueden tomar decisiones: la situación ya finalizó.")
        }
        val step = session.currentStep
            ?: throw InvalidSituationStateException("No hay un paso activo.")
        val belongs = step.decisions.any { it.id == decision.id }
        if (!belongs) {
            throw InvalidSituationStateException(
                "La decisión '${decision.label}' no pertenece al paso actual (${step.orderIndex})."
            )
        }
        val consequence = decision.consequences.firstOrNull()
        val updatedConsequences = if (consequence != null) {
            session.consequencesShown + consequence
        } else session.consequencesShown

        val advanced = advance(session.copy(
            decisionsMade = session.decisionsMade + decision.id,
            consequencesShown = updatedConsequences
        ))
        return advanced
    }

    /** Progreso 0..1 basado en pasos completados. */
    fun progressRatio(session: SituationSession): Float {
        if (session.totalSteps == 0) return 0f
        val completed = if (session.finished) session.totalSteps else session.currentIndex
        return (completed.toFloat() / session.totalSteps.toFloat()).coerceIn(0f, 1f)
    }

    /** Suma de XP obtenida hasta el momento en la sesión. */
    fun sessionXp(session: SituationSession): Int =
        session.consequencesShown.fold(0) { acc, consequence -> acc + consequence.xpAward }

    /** Proporción de consecuencias positivas sobre el total mostradas. */
    fun positivityRatio(session: SituationSession): Float {
        if (session.consequencesShown.isEmpty()) return 0f
        val positives = session.consequencesShown.count { it.isPositive }
        return positives.toFloat() / session.consequencesShown.size.toFloat()
    }
}
