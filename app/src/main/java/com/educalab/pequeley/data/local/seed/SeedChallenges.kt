package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.entity.ChallengeEntity

/** 15 desafíos breves de justicia cotidiana (Módulo 12), repartidos por dificultad. */
object SeedChallenges {
    val ALL = listOf(
        ChallengeEntity("challenge_todos_quieren_jugar", "Todos quieren jugar", "Ayuda a que un grupo grande se organice para jugar por turnos.", "sit_turno_columpio", 1),
        ChallengeEntity("challenge_no_escuchado", "Alguien no fue escuchado", "Ayuda a que todos puedan dar su opinión antes de decidir.", "sit_opinar_grupo", 2),
        ChallengeEntity("challenge_objeto_danado", "Un objeto compartido se dañó", "Ayuda a reparar la situación sin buscar culpables antes de escuchar.", "sit_prestar_libro", 2),
        ChallengeEntity("challenge_recuerdos_distintos", "Dos versiones de la misma historia", "Ayuda a escuchar ambas versiones antes de sacar conclusiones.", "sit_malentendido", 3),
        ChallengeEntity("challenge_turno_ignorado", "Un turno no respetado", "Ayuda a recordar el orden sin pelear.", "sit_turno_ignorado", 1),
        ChallengeEntity("challenge_grupo_excluye", "Un grupo no incluye a alguien", "Ayuda a proponer que todos puedan participar.", "sit_grupo_excluye", 2),
        ChallengeEntity("challenge_objeto_tomado", "Algo desapareció sin permiso", "Ayuda a resolver la situación sin acusar sin pruebas.", "sit_objeto_tomado", 2),
        ChallengeEntity("challenge_secreto_preocupante", "Un secreto que preocupa", "Ayuda a decidir cuándo es importante pedir ayuda a un adulto.", "sit_decision_secreto", 3),
        ChallengeEntity("challenge_seguir_al_grupo", "Seguir al grupo o no", "Ayuda a decidir cuando el grupo quiere hacer algo que no está bien.", "sit_decision_grupo", 3),
        ChallengeEntity("challenge_reconocer_error", "Reconocer un error", "Ayuda a reparar un error con honestidad.", "sit_decision_error", 2),
        ChallengeEntity("challenge_burla_patio", "Una burla en el patio", "Ayuda a frenar una burla con respeto.", "sit_burla", 2),
        ChallengeEntity("challenge_permiso_antes", "Pedir permiso antes de usar algo", "Ayuda a recordar por qué pedir permiso es importante.", "sit_pedir_permiso", 1),
        ChallengeEntity("challenge_planta_descuidada", "Una planta sin cuidado", "Ayuda a organizar turnos de cuidado.", "sit_cuidar_planta", 1),
        ChallengeEntity("challenge_espacio_compartido", "Un espacio compartido pequeño", "Ayuda a dividir el espacio de forma justa.", "sit_espacio_compartido", 2),
        ChallengeEntity("challenge_error_reparado", "Un préstamo olvidado", "Ayuda a reparar el olvido con una disculpa sincera.", "sit_reparar_error", 2)
    )
}
