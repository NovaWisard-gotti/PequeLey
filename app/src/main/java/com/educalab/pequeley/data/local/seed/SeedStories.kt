package com.educalab.pequeley.data.local.seed

/** 20 historias interactivas (mínimo requerido), cada una con mecánica y ramas propias. */
object SeedStories {

    val ALL: List<StorySeed> = listOf(
        StorySeed("story_turno_ana", "El turno de Ana", "Ana espera su turno para jugar con la pelota.", "DIALOGUE_CHOICE", listOf(
            StorySceneSeed(0, "Ana quiere jugar con la pelota, pero otro niño la tiene desde hace rato.", listOf(
                StoryChoiceSeed("Pedirla con calma", 1, false, "El otro niño la escucha."),
                StoryChoiceSeed("Quitársela de las manos", null, true, "El otro niño se enoja y ya no quiere compartir nada.")
            )),
            StorySceneSeed(1, "El niño piensa un momento y le dice que sí, que jueguen juntos.", listOf(
                StoryChoiceSeed("Agradecer y jugar juntos", null, true, "Terminan jugando los dos y se hacen amigos.")
            ))
        )),
        StorySeed("story_juguete_prestado", "El juguete prestado", "Un juguete prestado se pierde por un momento.", "BUILD_SOLUTION", listOf(
            StorySceneSeed(0, "Prestaste tu juguete favorito y ahora no lo encuentras por ningún lado.", listOf(
                StoryChoiceSeed("Preguntar con calma dónde quedó", 1, false, "Buscan juntos."),
                StoryChoiceSeed("Acusar sin preguntar", null, true, "Se genera una pelea y nadie ayuda a buscar.")
            )),
            StorySceneSeed(1, "Entre los dos revisan la mochila y aparece el juguete, solo se había traspapelado.", listOf(
                StoryChoiceSeed("Alegrarse juntos", null, true, "El juguete aparece y la amistad sigue igual de fuerte.")
            ))
        )),
        StorySeed("story_pelota_parque", "La pelota del parque", "La pelota rueda hacia un jardín ajeno.", "OBSERVE_AND_ACT", listOf(
            StorySceneSeed(0, "La pelota cayó en el jardín de una casa. Nadie sabe si tocar o no.", listOf(
                StoryChoiceSeed("Tocar el timbre y pedir permiso", null, true, "La persona sonríe y devuelve la pelota amablemente."),
                StoryChoiceSeed("Saltar la reja sin avisar", null, true, "La persona se asusta y se genera un problema innecesario.")
            ))
        )),
        StorySeed("story_acuerdo_equipo", "El acuerdo del equipo", "El equipo debe decidir quién empieza el juego.", "BUILD_SOLUTION", listOf(
            StorySceneSeed(0, "Todos quieren ser los primeros en jugar.", listOf(
                StoryChoiceSeed("Proponer un sorteo justo", 1, false, "El grupo acepta la idea."),
                StoryChoiceSeed("Que decida el más grande", null, true, "Los más pequeños sienten que no cuenta su opinión.")
            )),
            StorySceneSeed(1, "El sorteo se hace y todos aceptan el resultado sin quejarse.", listOf(
                StoryChoiceSeed("Empezar a jugar contentos", null, true, "El juego comienza con buen ánimo para todos.")
            ))
        )),
        StorySeed("story_discusion_error", "Una discusión por error", "Dos amigos recuerdan una historia distinta.", "COMPARE_CONSEQUENCES", listOf(
            StorySceneSeed(0, "Dos amigos discuten porque cada uno recuerda el juego de forma distinta.", listOf(
                StoryChoiceSeed("Escuchar las dos versiones con calma", 1, false, "Ambos cuentan su parte."),
                StoryChoiceSeed("Insistir en tener la razón", null, true, "La discusión se pone más tensa.")
            )),
            StorySceneSeed(1, "Al escucharse, descubren que ambos tenían un poco de razón.", listOf(
                StoryChoiceSeed("Reírse juntos del malentendido", null, true, "Terminan riéndose y siguen jugando como antes.")
            ))
        )),
        StorySeed("story_fila_comedor", "La fila del comedor", "Alguien intenta colarse en la fila.", "OBSERVE_AND_ACT", listOf(
            StorySceneSeed(0, "Un niño intenta colarse justo delante tuyo en la fila del comedor.", listOf(
                StoryChoiceSeed("Recordarle amablemente el orden", null, true, "El niño se disculpa y se pone al final."),
                StoryChoiceSeed("Empujarlo hacia atrás", null, true, "Se arma un pequeño problema y la fila se desordena.")
            ))
        )),
        StorySeed("story_libro_perdido", "El libro perdido", "Un libro de la biblioteca no aparece.", "BUILD_SOLUTION", listOf(
            StorySceneSeed(0, "El libro que sacaste de la biblioteca no aparece en tu mochila.", listOf(
                StoryChoiceSeed("Avisar y buscar con ayuda", 1, false, "Piden ayuda para buscarlo."),
                StoryChoiceSeed("Esconder que se perdió", null, true, "El problema crece y es más difícil de resolver después.")
            )),
            StorySceneSeed(1, "Entre todos revisan el salón y el libro aparece bajo un escritorio.", listOf(
                StoryChoiceSeed("Devolverlo con cuidado", null, true, "El libro vuelve a la biblioteca sano y salvo.")
            ))
        )),
        StorySeed("story_columpio_compartido", "El columpio compartido", "Dos niños quieren el mismo columpio.", "BUILD_SOLUTION", listOf(
            StorySceneSeed(0, "Solo hay un columpio libre y dos niños llegan al mismo tiempo.", listOf(
                StoryChoiceSeed("Proponer turnos con tiempo", null, true, "Ambos disfrutan el columpio sin pelear."),
                StoryChoiceSeed("Empujarse para llegar primero", null, true, "Alguien se lastima y ambos terminan sin jugar.")
            ))
        )),
        StorySeed("story_nuevo_amigo", "El amigo nuevo", "Un niño nuevo llega a la clase.", "DIALOGUE_CHOICE", listOf(
            StorySceneSeed(0, "Un niño nuevo llega a la clase y no conoce a nadie.", listOf(
                StoryChoiceSeed("Presentarte y mostrarle la escuela", 1, false, "El niño se siente bienvenido."),
                StoryChoiceSeed("Ignorarlo el primer día", null, true, "El niño se siente solo en su primer día.")
            )),
            StorySceneSeed(1, "Se hacen amigos rápido y él te cuenta de dónde viene.", listOf(
                StoryChoiceSeed("Invitarlo a jugar en el recreo", null, true, "Comienzan una nueva amistad.")
            ))
        )),
        StorySeed("story_planta_olvidada", "La planta olvidada", "Una planta necesita cuidado constante.", "CARE_OBJECT", listOf(
            StorySceneSeed(0, "La planta del salón lleva días sin agua y las hojas empiezan a caer.", listOf(
                StoryChoiceSeed("Organizar un turno para regarla", null, true, "La planta se recupera gracias al cuidado de todos."),
                StoryChoiceSeed("Esperar que alguien más lo haga", null, true, "Nadie la riega y la planta se pone peor.")
            ))
        )),
        StorySeed("story_secreto_preocupante", "El secreto que preocupa", "Un amigo cuenta algo que le preocupa.", "DIALOGUE_CHOICE", listOf(
            StorySceneSeed(0, "Un amigo te cuenta, muy preocupado, que alguien lo trata mal y te pide guardar el secreto.", listOf(
                StoryChoiceSeed("Contarlo a un adulto de confianza", 1, false, "Buscas ayuda aunque te pidió silencio."),
                StoryChoiceSeed("Guardarlo todo en silencio", null, true, "Tu amigo sigue sin recibir ayuda.")
            )),
            StorySceneSeed(1, "Un adulto escucha con calma y ayuda a que la situación de tu amigo mejore.", listOf(
                StoryChoiceSeed("Acompañar a tu amigo en el proceso", null, true, "Tu amigo se siente más seguro y acompañado.")
            ))
        )),
        StorySeed("story_dibujo_manchado", "El dibujo manchado", "Un dibujo se mancha por accidente.", "DIALOGUE_CHOICE", listOf(
            StorySceneSeed(0, "Sin querer, manchas el dibujo de un compañero al chocar la mesa.", listOf(
                StoryChoiceSeed("Pedir disculpas y ofrecer ayudar", null, true, "El compañero agradece la honestidad y lo arreglan juntos."),
                StoryChoiceSeed("Hacer como si no pasó nada", null, true, "El compañero descubre lo ocurrido y se siente engañado.")
            ))
        )),
        StorySeed("story_grupo_que_excluye", "El grupo que no dejaba jugar", "Un grupo excluye a un compañero.", "DIALOGUE_CHOICE", listOf(
            StorySceneSeed(0, "Un grupo le dice a un compañero que no puede jugar con ellos.", listOf(
                StoryChoiceSeed("Proponer que se sume", 1, false, "El grupo lo piensa mejor."),
                StoryChoiceSeed("No decir nada", null, true, "El compañero se queda triste y solo.")
            )),
            StorySceneSeed(1, "El grupo acepta incluirlo y el juego se vuelve más divertido con más personas.", listOf(
                StoryChoiceSeed("Celebrar juntos", null, true, "Todos terminan jugando contentos.")
            ))
        )),
        StorySeed("story_burla_patio", "La burla en el patio", "Alguien se burla de un compañero.", "OBSERVE_AND_ACT", listOf(
            StorySceneSeed(0, "Un grupo se ríe de la forma de hablar de un compañero nuevo.", listOf(
                StoryChoiceSeed("Pedir que paren y acompañarlo", null, true, "El compañero se siente respaldado."),
                StoryChoiceSeed("Reírte también", null, true, "El compañero se siente muy mal.")
            ))
        )),
        StorySeed("story_regla_inventada", "La regla que inventamos", "Un grupo crea un juego nuevo.", "BUILD_SOLUTION", listOf(
            StorySceneSeed(0, "El grupo quiere inventar un juego nuevo, pero no hay reglas todavía.", listOf(
                StoryChoiceSeed("Preguntar ideas de todos", 1, false, "Escuchan varias propuestas."),
                StoryChoiceSeed("Decidir solo tú las reglas", null, true, "Algunos no quieren jugar porque no los tomaron en cuenta.")
            )),
            StorySceneSeed(1, "Combinan las mejores ideas de cada uno en un juego nuevo.", listOf(
                StoryChoiceSeed("Empezar a jugar todos juntos", null, true, "El juego nuevo le gusta a todo el grupo.")
            ))
        )),
        StorySeed("story_espacio_dibujo", "El espacio para dibujar", "Dos niños comparten una mesa pequeña.", "BUILD_SOLUTION", listOf(
            StorySceneSeed(0, "La mesa de dibujo es pequeña y dos niños la quieren usar al mismo tiempo.", listOf(
                StoryChoiceSeed("Dividir la mesa en dos partes", null, true, "Ambos dibujan cómodos, cada uno en su espacio."),
                StoryChoiceSeed("Empujar las cosas del otro", null, true, "Se arruinan los dibujos y ambos se enojan.")
            ))
        )),
        StorySeed("story_ayuda_tarea", "La ayuda con la tarea", "Un compañero no entiende un ejercicio.", "DIALOGUE_CHOICE", listOf(
            StorySceneSeed(0, "Un compañero levanta la mano porque no entiende un ejercicio.", listOf(
                StoryChoiceSeed("Explicarle con paciencia", null, true, "El compañero logra entender y lo agradece."),
                StoryChoiceSeed("Decirle que es fácil y seguir en lo tuyo", null, true, "El compañero se siente más confundido.")
            ))
        )),
        StorySeed("story_opinion_grupo", "Mi opinión en el grupo", "El grupo decide una actividad distinta a la que te gusta.", "DIALOGUE_CHOICE", listOf(
            StorySceneSeed(0, "Todos quieren dibujar, pero a ti te gustaría armar un rompecabezas.", listOf(
                StoryChoiceSeed("Compartir tu idea con respeto", 1, false, "El grupo te escucha."),
                StoryChoiceSeed("Quedarte callado y molesto", null, true, "Terminas haciendo algo que no disfrutas.")
            )),
            StorySceneSeed(1, "Deciden alternar entre las dos actividades durante la semana.", listOf(
                StoryChoiceSeed("Aceptar el nuevo plan", null, true, "Todos terminan disfrutando ambas actividades.")
            ))
        )),
        StorySeed("story_error_reparado", "El error que se reparó", "Olvidaste devolver algo prestado.", "BUILD_SOLUTION", listOf(
            StorySceneSeed(0, "Se te olvidó devolver el borrador que te prestó un compañero hace días.", listOf(
                StoryChoiceSeed("Devolverlo y disculparte", null, true, "Tu compañero lo entiende y confía en volver a prestarte cosas."),
                StoryChoiceSeed("Quedarte con él sin decir nada", null, true, "Tu compañero deja de confiar en prestarte sus cosas.")
            ))
        )),
        StorySeed("story_agradecimiento", "Gracias, amigo", "Alguien te ayudó esta semana.", "REFLECTION", listOf(
            StorySceneSeed(0, "Recuerdas que un compañero te ayudó a entender un tema difícil.", listOf(
                StoryChoiceSeed("Agradecerle directamente", null, true, "Tu compañero se siente valorado y contento."),
                StoryChoiceSeed("No decir nada", null, true, "Pierdes la oportunidad de fortalecer esa amistad.")
            ))
        ))
    )
}
