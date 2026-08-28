package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.entity.CharacterEntity
import com.educalab.pequeley.data.local.entity.CharacterExpressionEntity

/**
 * 15 personajes. La apariencia visual de cada uno se genera de forma
 * paramétrica en ui/illustration/CharacterArt.kt a partir de
 * shapeSeed/paletteSeed/accessorySeed, de modo que cada personaje es
 * visualmente distinto sin depender de arte bitmap importado.
 */
object SeedCharacters {

    val ALL = listOf(
        CharacterEntity("lexi", "Lexi", "Guía de la casa", "Curiosa, buena oyente, busca soluciones", 1, 1, 1),
        CharacterEntity("ana", "Ana", "Vecina del patio", "Alegre y un poco impaciente", 2, 2, 2),
        CharacterEntity("marco", "Marco", "Amigo del taller", "Distraído pero muy responsable cuando se lo proponen", 3, 3, 3),
        CharacterEntity("sofia", "Sofía", "Amiga del jardín", "Le encanta el fútbol y compartir", 4, 4, 4),
        CharacterEntity("iker", "Iker", "Amigo del jardín", "Tranquilo, le encanta dibujar", 5, 5, 5),
        CharacterEntity("leo", "Leo", "Bibliotecario junior", "Cuidadoso con los libros y objetos prestados", 6, 6, 6),
        CharacterEntity("mia", "Mía", "Amiga de la sala de acuerdos", "Buena para proponer ideas", 7, 7, 7),
        CharacterEntity("tomas", "Tomás", "Amigo del patio", "A veces se enoja rápido pero aprende rápido también", 8, 8, 8),
        CharacterEntity("valentina", "Valentina", "Amiga de las decisiones", "Reflexiva, piensa antes de actuar", 9, 9, 9),
        CharacterEntity("nico", "Nico", "Amigo responsable", "Cuida las plantas del taller", 10, 10, 10),
        CharacterEntity("emma", "Emma", "Narradora del archivo de historias", "Le encanta contar y escuchar historias", 11, 11, 11),
        CharacterEntity("diego", "Diego", "Amigo del comedor", "Siempre respeta la fila", 12, 12, 12),
        CharacterEntity("luna", "Luna", "Amiga del jardín del respeto", "Escucha con atención a quien está triste", 13, 13, 13),
        CharacterEntity("hugo", "Hugo", "Amigo de la biblioteca", "Curioso sobre los derechos de todos", 14, 14, 14),
        CharacterEntity("clara", "Clara", "Amiga constructora de acuerdos", "Le gusta unir ideas de todos", 15, 15, 15)
    )

    val EXPRESSIONS = listOf(
        CharacterExpressionEntity(0, "lexi", "NEUTRAL", "Lexi observando con atención."),
        CharacterExpressionEntity(0, "lexi", "HAPPY", "Lexi sonriendo al ver una buena decisión."),
        CharacterExpressionEntity(0, "lexi", "THINKING", "Lexi pensando en voz alta."),
        CharacterExpressionEntity(0, "lexi", "PROUD", "Lexi orgullosa del progreso del niño."),
        CharacterExpressionEntity(0, "ana", "NEUTRAL", "Ana esperando su turno."),
        CharacterExpressionEntity(0, "ana", "SURPRISED", "Ana sorprendida por una idea nueva."),
        CharacterExpressionEntity(0, "ana", "HAPPY", "Ana contenta de compartir el juego."),
        CharacterExpressionEntity(0, "marco", "THINKING", "Marco recordando regar la planta."),
        CharacterExpressionEntity(0, "marco", "PROUD", "Marco orgulloso de haber cuidado su planta."),
        CharacterExpressionEntity(0, "sofia", "HAPPY", "Sofía feliz de jugar en equipo."),
        CharacterExpressionEntity(0, "iker", "CALM", "Iker dibujando tranquilo."),
        CharacterExpressionEntity(0, "leo", "NEUTRAL", "Leo cuidando un libro prestado."),
        CharacterExpressionEntity(0, "mia", "THINKING", "Mía proponiendo una idea para el acuerdo."),
        CharacterExpressionEntity(0, "tomas", "SURPRISED", "Tomás sorprendido por la consecuencia de no escuchar."),
        CharacterExpressionEntity(0, "tomas", "CALM", "Tomás más tranquilo después de conversar."),
        CharacterExpressionEntity(0, "valentina", "THINKING", "Valentina pensando antes de decidir."),
        CharacterExpressionEntity(0, "nico", "PROUD", "Nico orgulloso de sus responsabilidades cumplidas."),
        CharacterExpressionEntity(0, "emma", "HAPPY", "Emma contando una nueva historia."),
        CharacterExpressionEntity(0, "diego", "NEUTRAL", "Diego respetando la fila del comedor."),
        CharacterExpressionEntity(0, "luna", "CALM", "Luna escuchando a un amigo triste."),
        CharacterExpressionEntity(0, "hugo", "SURPRISED", "Hugo sorprendido al descubrir un nuevo derecho."),
        CharacterExpressionEntity(0, "clara", "HAPPY", "Clara feliz de construir un acuerdo entre todos.")
    )
}
