package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.ConsequenceModel
import com.educalab.pequeley.domain.model.DecisionModel

class InvalidConsequenceStateException(message: String) : Exception(message)

data class ConsequenceComparison(
    val decisionA: DecisionModel,
    val decisionB: DecisionModel,
    val consequenceA: ConsequenceModel,
    val consequenceB: ConsequenceModel,
    val betterChoice: DecisionModel?
)

/**
 * Motor del "Espejo de Consecuencias" (Módulo 10): permite comparar
 * qué ocurre si el niño elige una acción u otra, sin castigar,
 * solo mostrando resultados para reflexionar.
 */
class ConsequenceEngine {

    fun firstConsequence(decision: DecisionModel): ConsequenceModel {
        return decision.consequences.firstOrNull()
            ?: throw InvalidConsequenceStateException(
                "La decisión '${decision.label}' no tiene consecuencias registradas."
            )
    }

    /**
     * Compara dos decisiones alternativas para una misma situación,
     * indicando cuál produce un resultado más positivo (si alguna).
     * Nunca declara "ganador" cuando ambas son igualmente positivas:
     * en ese caso betterChoice es null, respetando que puede no
     * existir una única solución correcta.
     */
    fun compare(decisionA: DecisionModel, decisionB: DecisionModel): ConsequenceComparison {
        val consequenceA = firstConsequence(decisionA)
        val consequenceB = firstConsequence(decisionB)

        val better = when {
            consequenceA.isPositive && !consequenceB.isPositive -> decisionA
            consequenceB.isPositive && !consequenceA.isPositive -> decisionB
            else -> null
        }

        return ConsequenceComparison(
            decisionA = decisionA,
            decisionB = decisionB,
            consequenceA = consequenceA,
            consequenceB = consequenceB,
            betterChoice = better
        )
    }

    /** Texto reflexivo breve (nunca solo "Correcto"/"Incorrecto"). */
    fun reflectionFor(consequence: ConsequenceModel): String {
        return if (consequence.isPositive) {
            "${consequence.outcomeText} Esto ayuda a que todos se sientan mejor."
        } else {
            "${consequence.outcomeText} Se puede intentar de otra manera: conversando y escuchando."
        }
    }
}
