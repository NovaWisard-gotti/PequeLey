package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.AgreementModel
import com.educalab.pequeley.domain.model.AgreementSymbols

class InvalidAgreementException(message: String) : Exception(message)

/**
 * Motor de construcción de acuerdos (Sala de los Acuerdos / Mesa de
 * construcción / Mis Grandes Acuerdos). El niño combina símbolos como
 * CONVERSAR + ESCUCHAR + PROPONER TURNOS + ACEPTAR EL ACUERDO.
 */
class AgreementEngine {

    companion object {
        const val MIN_ITEMS = 2
        const val MAX_ITEMS = 6
    }

    /** Construye un acuerdo a partir de una lista ordenada de códigos de símbolo. */
    fun build(title: String, situationCode: String?, symbolCodes: List<String>, now: Long): AgreementModel {
        val trimmedTitle = title.trim()
        if (trimmedTitle.isEmpty()) {
            throw InvalidAgreementException("El acuerdo necesita un título.")
        }
        if (symbolCodes.isEmpty()) {
            throw InvalidAgreementException("Un acuerdo no puede estar vacío: elige al menos $MIN_ITEMS acciones.")
        }
        if (symbolCodes.size < MIN_ITEMS) {
            throw InvalidAgreementException("Añade al menos $MIN_ITEMS acciones para construir un acuerdo sólido.")
        }
        if (symbolCodes.size > MAX_ITEMS) {
            throw InvalidAgreementException("Un acuerdo no puede tener más de $MAX_ITEMS acciones.")
        }
        val validCodes = AgreementSymbols.ALL.map { it.code }.toSet()
        val unknown = symbolCodes.filter { it !in validCodes }
        if (unknown.isNotEmpty()) {
            throw InvalidAgreementException("Elementos desconocidos en el acuerdo: $unknown")
        }
        return AgreementModel(
            title = trimmedTitle,
            situationCode = situationCode,
            items = symbolCodes,
            createdAt = now
        )
    }

    /** Determina si un acuerdo es "sólido": incluye escuchar o conversar y termina en un cierre. */
    fun isSolidAgreement(agreement: AgreementModel): Boolean {
        val hasListening = agreement.items.any { it == "escuchar" || it == "conversar" }
        val hasClosure = agreement.items.lastOrNull() in setOf("aceptar_acuerdo", "pedir_disculpas", "reparar")
        return hasListening && hasClosure
    }

    /** Combina dos propuestas de símbolos eliminando duplicados consecutivos. */
    fun combineProposals(proposalA: List<String>, proposalB: List<String>): List<String> {
        val combined = proposalA + proposalB
        val result = mutableListOf<String>()
        for (code in combined) {
            if (result.lastOrNull() != code) result.add(code)
        }
        return result.take(MAX_ITEMS)
    }
}
