package com.educalab.pequeley.domain.model

/** Un símbolo disponible para construir acuerdos (Módulo 9 / 11). */
data class AgreementSymbol(
    val code: String,
    val label: String
)

data class AgreementModel(
    val id: Long = 0,
    val title: String,
    val situationCode: String?,
    val items: List<String>, // símbolos elegidos en orden
    val createdAt: Long
)

object AgreementSymbols {
    val ALL = listOf(
        AgreementSymbol("conversar", "Conversar"),
        AgreementSymbol("escuchar", "Escuchar"),
        AgreementSymbol("esperar", "Esperar el turno"),
        AgreementSymbol("compartir", "Compartir"),
        AgreementSymbol("pedir_permiso", "Pedir permiso"),
        AgreementSymbol("reparar", "Reparar"),
        AgreementSymbol("ayudar", "Ayudar"),
        AgreementSymbol("aceptar_acuerdo", "Aceptar el acuerdo"),
        AgreementSymbol("pedir_disculpas", "Pedir disculpas"),
        AgreementSymbol("proponer_turnos", "Proponer turnos")
    )
}
