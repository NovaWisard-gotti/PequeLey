package com.educalab.pequeley.data.local.seed

/** 32 situaciones cotidianas (mínimo requerido: 30), repartidas en las 8 habitaciones. */
object SeedSituations {

    val ALL: List<SituationSeed> = listOf(
        // ---------- HABITACIÓN DE LAS REGLAS ----------
        SituationSeed(
            "sit_fila_comedor", "reglas", "La fila del comedor",
            "Todos esperan su turno para servirse la comida.",
            1, "OBSERVE_AND_ACT",
            "Es hora de comer. Hay una fila larga y alguien quiere pasar primero.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Esperar mi turno", "Formarme al final de la fila", "La fila avanza tranquila y nadie se molesta.", true, 4, 8),
                DecisionSeed("Colarme", "Pasar antes que los demás", "Varios niños se molestan y la fila se desordena.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_orden_juguetes", "reglas", "Ordenar la sala de juegos",
            "La sala de juegos quedó desordenada después de jugar.",
            1, "CARE_OBJECT",
            "Los juguetes están tirados por el suelo después del recreo.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Guardar los juguetes", "Ayudar a ordenar antes de irse", "La sala queda lista para que otros jueguen después.", true, 4, 8),
                DecisionSeed("Dejarlos tirados", "Salir corriendo sin ordenar", "Alguien puede tropezar y los juguetes se pueden dañar.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_turno_columpio", "reglas", "El turno del columpio",
            "Dos niños quieren usar el mismo columpio.",
            2, "DIALOGUE_CHOICE",
            "Hay un solo columpio libre y dos niños corren hacia él al mismo tiempo.",
            "¿Qué propones?",
            listOf(
                DecisionSeed("Contar hasta 20 por turno", "Proponer un tiempo justo para cada uno", "Ambos disfrutan el columpio sin pelear.", true, 5, 10),
                DecisionSeed("Empujar para llegar primero", "Adelantarse sin avisar", "El otro niño se siente mal y ya no quiere jugar contigo.", false, -3, 2)
            )
        ),
        SituationSeed(
            "sit_regla_juego", "reglas", "Inventar una regla de juego",
            "El grupo va a inventar un juego nuevo y necesita reglas.",
            2, "BUILD_SOLUTION",
            "Van a jugar algo nuevo, pero nadie sabe bien cómo. Hace falta ponerse de acuerdo en las reglas.",
            "¿Cómo empiezas?",
            listOf(
                DecisionSeed("Preguntar las ideas de todos", "Escuchar propuestas antes de decidir", "Se crea un juego que le gusta a todo el grupo.", true, 5, 10),
                DecisionSeed("Decidir tú solo las reglas", "Imponer tu idea sin preguntar", "Algunos no quieren jugar porque no los tomaron en cuenta.", false, -2, 3)
            )
        ),

        // ---------- BIBLIOTECA DE LOS DERECHOS ----------
        SituationSeed(
            "sit_participar_actividad", "derechos", "Todos quieren participar",
            "Un compañero nuevo quiere unirse al juego.",
            1, "DIALOGUE_CHOICE",
            "Un niño nuevo en la escuela mira desde lejos cómo juegan los demás.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Invitarlo a jugar", "Hacerle un espacio en el equipo", "El niño nuevo sonríe y se siente parte del grupo.", true, 5, 10),
                DecisionSeed("Seguir jugando sin invitarlo", "Ignorarlo", "El niño nuevo se siente solo y triste.", false, -3, 2)
            )
        ),
        SituationSeed(
            "sit_opinar_grupo", "derechos", "Dar tu opinión en el grupo",
            "El grupo decide qué actividad hacer y tú tienes una idea distinta.",
            2, "DIALOGUE_CHOICE",
            "Todos quieren dibujar, pero a ti te gustaría armar un rompecabezas.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Compartir tu idea con respeto", "Decir lo que piensas sin pelear", "El grupo escucha y deciden alternar entre las dos actividades.", true, 4, 8),
                DecisionSeed("Quedarte callado y molesto", "No decir nada aunque no te guste", "Terminas haciendo algo que no disfrutas y sigues molesto.", false, -1, 3)
            )
        ),
        SituationSeed(
            "sit_recreo_seguro", "derechos", "Sentirse seguro en el recreo",
            "Alguien empuja fuerte durante un juego.",
            2, "OBSERVE_AND_ACT",
            "Durante un juego, un niño empuja muy fuerte a otro sin querer.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Avisar a un adulto y ayudar al niño", "Buscar apoyo y ver que esté bien", "El niño se siente cuidado y el juego puede seguir con más cuidado.", true, 5, 10),
                DecisionSeed("Seguir jugando como si nada", "Ignorar lo que pasó", "El niño empujado se queda solo, sin saber si está bien.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_ayuda_tarea", "derechos", "Pedir ayuda con la tarea",
            "Un compañero no entiende un ejercicio.",
            1, "DIALOGUE_CHOICE",
            "Un compañero levanta la mano porque no entiende un ejercicio de clase.",
            "¿Qué haces si terminaste el tuyo?",
            listOf(
                DecisionSeed("Explicarle con paciencia", "Ayudarlo a entender", "El compañero logra entender y te lo agradece.", true, 4, 8),
                DecisionSeed("Decirle que es fácil y seguir en lo tuyo", "No ayudar", "El compañero se siente más confundido.", false, -1, 2)
            )
        ),

        // ---------- TALLER DE RESPONSABILIDADES ----------
        SituationSeed(
            "sit_cuidar_planta", "responsabilidades", "Cuidar la planta del salón",
            "La planta del salón necesita agua.",
            1, "CARE_OBJECT",
            "La planta del salón tiene las hojas un poco caídas: necesita agua.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Regarla con cuidado", "Darle el agua que necesita", "La planta se ve más fuerte al día siguiente.", true, 4, 8),
                DecisionSeed("No hacer nada", "Dejarla como está", "La planta se pone más débil.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_mochila_ordenada", "responsabilidades", "La mochila ordenada",
            "Es hora de guardar los materiales en la mochila.",
            1, "CARE_OBJECT",
            "Terminó la clase y hay que guardar los materiales antes de salir.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Guardar todo en su lugar", "Ordenar la mochila con calma", "Mañana encuentras todo rápido y sin apuro.", true, 3, 6),
                DecisionSeed("Meter todo de golpe", "Guardar sin orden", "Al día siguiente cuesta encontrar los materiales.", false, -1, 2)
            )
        ),
        SituationSeed(
            "sit_prestar_libro", "responsabilidades", "Cuidar un libro prestado",
            "Un amigo te prestó un libro que le gusta mucho.",
            2, "CARE_OBJECT",
            "Un amigo te prestó su libro favorito para que lo leas esta semana.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Cuidarlo y devolverlo a tiempo", "Tratarlo como si fuera tuyo", "Tu amigo confía en prestarte cosas de nuevo.", true, 5, 10),
                DecisionSeed("Dejarlo tirado en la mochila", "No cuidarlo", "El libro se daña y tu amigo se pone triste.", false, -3, 2)
            )
        ),
        SituationSeed(
            "sit_limpiar_mesa", "responsabilidades", "Limpiar después de comer",
            "Terminaste de comer y quedaron migas en la mesa.",
            1, "CARE_OBJECT",
            "Después de comer, la mesa quedó con migas y un vaso vacío.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Limpiar tu espacio", "Dejar la mesa lista para el siguiente", "La mesa queda limpia para quien llegue después.", true, 3, 6),
                DecisionSeed("Dejarlo todo así", "Levantarte sin limpiar", "La siguiente persona encuentra la mesa sucia.", false, -1, 2)
            )
        ),

        // ---------- SALA DE LOS ACUERDOS ----------
        SituationSeed(
            "sit_juguete_compartido", "acuerdos", "El mismo juguete",
            "Dos niños quieren usar el mismo juguete a la vez.",
            2, "BUILD_SOLUTION",
            "Ana y Marco quieren usar el mismo camión de juguete al mismo tiempo.",
            "¿Qué acuerdo propones?",
            listOf(
                DecisionSeed("Turnarse por tiempo", "Proponer minutos iguales para cada uno", "Ambos juegan tranquilos, sabiendo cuándo les toca.", true, 5, 10),
                DecisionSeed("Que se lo quede el más fuerte", "Dejar que gane quien empuje más", "El más pequeño se queda sin jugar y se siente mal.", false, -3, 2)
            )
        ),
        SituationSeed(
            "sit_horario_estudio", "acuerdos", "Ponerse de acuerdo para estudiar juntos",
            "Un grupo quiere estudiar junto mañana.",
            2, "BUILD_SOLUTION",
            "El grupo quiere reunirse a estudiar, pero cada uno propone un horario distinto.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Buscar un horario que sirva a todos", "Preguntar y ajustar entre todos", "Encuentran un horario que funciona para el grupo completo.", true, 4, 8),
                DecisionSeed("Imponer tu horario", "Decidir sin preguntar a los demás", "Algunos no pueden asistir y se sienten dejados de lado.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_juego_equipo", "acuerdos", "Elegir el juego del equipo",
            "El equipo no se pone de acuerdo en qué juego jugar.",
            2, "BUILD_SOLUTION",
            "Unos quieren jugar fútbol, otros prefieren las escondidas.",
            "¿Qué propones?",
            listOf(
                DecisionSeed("Alternar los juegos por turnos", "Jugar un rato de cada cosa", "Todos terminan jugando algo que les gusta.", true, 4, 8),
                DecisionSeed("Que gane el grupo más grande", "Decidir por mayoría sin conversar", "Los que querían el otro juego se quedan sin jugar lo que querían.", false, -1, 3)
            )
        ),
        SituationSeed(
            "sit_espacio_compartido", "acuerdos", "Compartir el espacio de dibujo",
            "Dos niños quieren usar la misma mesa para dibujar.",
            1, "BUILD_SOLUTION",
            "La mesa de dibujo es pequeña y dos niños quieren usarla al mismo tiempo.",
            "¿Qué acuerdan?",
            listOf(
                DecisionSeed("Dividir la mesa en dos espacios", "Compartir el lugar con cuidado", "Ambos dibujan cómodos, cada uno en su parte.", true, 4, 8),
                DecisionSeed("Empujar las cosas del otro", "No compartir el espacio", "Se arruinan los dibujos y ambos se enojan.", false, -3, 2)
            )
        ),

        // ---------- PATIO DE LA CONVIVENCIA ----------
        SituationSeed(
            "sit_objeto_tomado", "convivencia", "Alguien tomó un objeto sin permiso",
            "Un lápiz desapareció de la mesa de un compañero.",
            2, "OBSERVE_AND_ACT",
            "El lápiz favorito de un compañero desapareció de su mesa.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Ayudar a preguntar con calma", "Buscar el lápiz conversando, sin acusar", "Aparece el lápiz: alguien lo tomó sin darse cuenta y lo devuelve.", true, 4, 8),
                DecisionSeed("Acusar a alguien sin pruebas", "Señalar sin estar seguro", "Se genera una discusión y nadie se siente bien.", false, -3, 2)
            )
        ),
        SituationSeed(
            "sit_grupo_excluye", "convivencia", "Un grupo no deja participar a alguien",
            "Un grupo de amigos no deja jugar a un compañero.",
            2, "DIALOGUE_CHOICE",
            "Un grupo juega y le dice a un compañero que no puede unirse.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Proponer que se sume al juego", "Hablar con el grupo para incluirlo", "El compañero se une y el juego se vuelve más divertido.", true, 5, 10),
                DecisionSeed("No decir nada", "Quedarte callado", "El compañero se queda triste y solo.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_malentendido", "convivencia", "Una discusión por un malentendido",
            "Dos amigos discuten porque entendieron cosas distintas.",
            3, "COMPARE_CONSEQUENCES",
            "Dos amigos discuten fuerte: cada uno recuerda la historia de forma diferente.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Escuchar las dos versiones", "Dejar que cada uno cuente su parte", "Descubren que fue un malentendido y se ríen juntos.", true, 5, 10),
                DecisionSeed("Tomar partido sin escuchar a ambos", "Creerle solo a uno", "El otro se siente incomprendido y la discusión sigue.", false, -2, 3)
            )
        ),
        SituationSeed(
            "sit_turno_ignorado", "convivencia", "No respetar un turno",
            "Alguien se salta la fila del tobogán.",
            1, "OBSERVE_AND_ACT",
            "En la fila del tobogán, un niño se cuela sin avisar.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Recordar el turno con amabilidad", "Avisar con calma que hay una fila", "El niño se disculpa y respeta el turno.", true, 3, 6),
                DecisionSeed("Empujarlo para sacarlo", "Reaccionar con fuerza", "Se arma un problema más grande.", false, -3, 2)
            )
        ),

        // ---------- SALA DE LAS DECISIONES ----------
        SituationSeed(
            "sit_decision_ayudar", "decisiones", "Decidir si ayudar a un compañero",
            "Un compañero se cayó durante el recreo.",
            1, "OBSERVE_AND_ACT",
            "Un compañero se tropezó y se raspó la rodilla.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Ayudarlo a levantarse", "Acompañarlo a pedir ayuda", "El compañero se siente cuidado y agradecido.", true, 4, 8),
                DecisionSeed("Seguir jugando", "No detenerte a ayudar", "El compañero se siente solo en ese momento.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_decision_secreto", "decisiones", "Algo que te preocupa",
            "Un amigo te cuenta algo que le preocupa y te pide guardar el secreto.",
            3, "DIALOGUE_CHOICE",
            "Un amigo te cuenta que alguien lo trata mal y te pide que no lo cuentes a nadie.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Contarlo a un adulto de confianza", "Buscar ayuda aunque te pidieron silencio", "Un adulto puede ayudar a que tu amigo esté mejor y más seguro.", true, 5, 10),
                DecisionSeed("Guardarlo todo en silencio", "No decir nada a nadie", "Tu amigo sigue sin recibir ayuda.", false, -2, 3)
            )
        ),
        SituationSeed(
            "sit_decision_grupo", "decisiones", "Seguir al grupo o decir lo que piensas",
            "El grupo quiere hacer algo que no te parece bien.",
            3, "DIALOGUE_CHOICE",
            "El grupo quiere burlarse de un compañero y te invitan a hacerlo también.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Decir que no te parece bien", "Expresar tu desacuerdo con calma", "Algunos del grupo lo piensan mejor y paran.", true, 5, 10),
                DecisionSeed("Sumarte para no quedar fuera", "Hacer lo mismo que el grupo", "El compañero se siente muy mal.", false, -4, 2)
            )
        ),
        SituationSeed(
            "sit_decision_error", "decisiones", "Reconocer un error",
            "Rompiste sin querer el dibujo de un compañero.",
            2, "DIALOGUE_CHOICE",
            "Sin querer, chocaste una mesa y el dibujo de un compañero se manchó.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Pedir disculpas y ofrecer ayudar a repararlo", "Reconocer lo que pasó", "El compañero agradece la honestidad y arreglan el dibujo juntos.", true, 5, 10),
                DecisionSeed("Hacer como si no hubiera pasado", "Quedarte callado", "El compañero descubre lo ocurrido y se siente engañado.", false, -3, 2)
            )
        ),

        // ---------- JARDÍN DEL RESPETO ----------
        SituationSeed(
            "sit_escuchar_amigo", "respeto", "Escuchar a un amigo triste",
            "Un amigo está triste y quiere hablar.",
            1, "DIALOGUE_CHOICE",
            "Un amigo se sienta solo, con cara triste, durante el recreo.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Sentarte a escucharlo", "Acompañarlo y preguntar qué pasa", "Tu amigo se siente acompañado y un poco mejor.", true, 4, 8),
                DecisionSeed("Seguir de largo", "No acercarte", "Tu amigo se queda solo con su tristeza.", false, -2, 2)
            )
        ),
        SituationSeed(
            "sit_diferencia_gustos", "respeto", "Respetar gustos diferentes",
            "A un amigo le gusta algo distinto a lo que te gusta a ti.",
            1, "SORT_FACTS",
            "A Iker le encanta dibujar tranquilo; a ti te gusta correr y jugar fuerte.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Respetar lo que le gusta a cada uno", "Aceptar que son diferentes", "Ambos disfrutan su tiempo sin sentirse juzgados.", true, 3, 6),
                DecisionSeed("Burlarte de su gusto", "Decirle que es aburrido", "Tu amigo se siente mal por algo que le gusta.", false, -3, 2)
            )
        ),
        SituationSeed(
            "sit_burla", "respeto", "Alguien se burla de un compañero",
            "Un grupo se ríe de la forma de hablar de un compañero.",
            2, "OBSERVE_AND_ACT",
            "Algunos niños se burlan de cómo habla un compañero nuevo.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Pedir que paren y acompañarlo", "Defenderlo con calma", "El compañero se siente respaldado y menos solo.", true, 5, 10),
                DecisionSeed("Reírte también", "Sumarte a la burla", "El compañero se siente muy mal y avergonzado.", false, -4, 2)
            )
        ),
        SituationSeed(
            "sit_pedir_permiso", "respeto", "Pedir permiso antes de usar algo",
            "Quieres usar el balón que trajo un compañero.",
            1, "DIALOGUE_CHOICE",
            "Un compañero trajo un balón nuevo y quieres jugar con él.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Pedir permiso primero", "Preguntar antes de tomarlo", "Tu compañero dice que sí y juegan juntos contentos.", true, 3, 6),
                DecisionSeed("Tomarlo sin preguntar", "Usarlo sin avisar", "Tu compañero se molesta porque no le preguntaste.", false, -2, 2)
            )
        ),

        // ---------- ARCHIVO DE HISTORIAS ----------
        SituationSeed(
            "sit_cuento_compartido", "historias", "Compartir un cuento favorito",
            "Quieres compartir tu cuento favorito con la clase.",
            1, "DIALOGUE_CHOICE",
            "Es el momento de compartir cuentos y tienes uno que te encanta.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Compartirlo con entusiasmo", "Contarlo y dejar preguntas", "A tus compañeros les encanta y quieren leerlo también.", true, 4, 8),
                DecisionSeed("Quedarte callado por vergüenza", "No participar", "Te quedas con ganas de compartirlo.", false, 0, 2)
            )
        ),
        SituationSeed(
            "sit_registro_dia", "historias", "Recordar una buena decisión del día",
            "Antes de dormir, piensas en lo que pasó hoy.",
            1, "REFLECTION",
            "Al final del día, Lexi te pregunta qué buena decisión tomaste hoy.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Recordar y contarla", "Pensar en un momento en que ayudaste o escuchaste", "Te sientes bien al reconocer tus propias buenas acciones.", true, 3, 6),
                DecisionSeed("Decir que no pasó nada importante", "No pensar en el día", "Te pierdes la oportunidad de reconocer tu esfuerzo.", false, 0, 1)
            )
        ),
        SituationSeed(
            "sit_reparar_error", "historias", "Reparar un error cometido",
            "Olvidaste devolver un objeto prestado a tiempo.",
            2, "BUILD_SOLUTION",
            "Se te olvidó devolver el borrador que te prestó un compañero hace días.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Devolverlo y disculparte", "Reparar el olvido", "Tu compañero lo entiende y confía en volver a prestarte cosas.", true, 4, 8),
                DecisionSeed("Quedarte con él sin decir nada", "Evitar el tema", "Tu compañero deja de confiar en prestarte sus cosas.", false, -3, 2)
            )
        ),
        SituationSeed(
            "sit_agradecer", "historias", "Agradecer una ayuda recibida",
            "Un compañero te ayudó con algo esta semana.",
            1, "DIALOGUE_CHOICE",
            "Recuerdas que un compañero te ayudó a entender un tema difícil.",
            "¿Qué haces?",
            listOf(
                DecisionSeed("Agradecerle directamente", "Decirle gracias con tus palabras", "Tu compañero se siente valorado y contento.", true, 3, 6),
                DecisionSeed("No decir nada", "Dejarlo pasar", "Pierdes la oportunidad de fortalecer esa amistad.", false, 0, 1)
            )
        )
    )
}
