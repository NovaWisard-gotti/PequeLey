package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.entity.HouseRoomEntity

/**
 * Las 8 habitaciones principales de la casa. requiredLevelToUnlock=1
 * en las primeras para que la instalación inicial ya tenga contenido
 * disponible (sección 24: "la app instalada debe sentirse completa"),
 * y niveles crecientes en las salas más avanzadas para dar sensación
 * de progresión real.
 */
object SeedRooms {
    val ALL = listOf(
        HouseRoomEntity(
            code = "reglas", name = "Habitación de las Reglas",
            description = "Descubre por qué los acuerdos ayudan a que todo funcione mejor.",
            orderIndex = 0, illustrationSeed = 101, colorHex = "#F6A93B", requiredLevelToUnlock = 1
        ),
        HouseRoomEntity(
            code = "derechos", name = "Biblioteca de los Derechos",
            description = "Libros ilustrados que cuentan historias sobre cosas que todos merecemos.",
            orderIndex = 1, illustrationSeed = 102, colorHex = "#5B8DEF", requiredLevelToUnlock = 1
        ),
        HouseRoomEntity(
            code = "responsabilidades", name = "Taller de Responsabilidades",
            description = "Objetos que necesitan cuidado: aprende qué significa ser responsable.",
            orderIndex = 2, illustrationSeed = 103, colorHex = "#E2725B", requiredLevelToUnlock = 1
        ),
        HouseRoomEntity(
            code = "acuerdos", name = "Sala de los Acuerdos",
            description = "Una mesa donde se construyen acuerdos escuchando a todos.",
            orderIndex = 3, illustrationSeed = 104, colorHex = "#8FD4C1", requiredLevelToUnlock = 2
        ),
        HouseRoomEntity(
            code = "convivencia", name = "Patio de la Convivencia",
            description = "Pequeños conflictos cotidianos que puedes ayudar a resolver.",
            orderIndex = 4, illustrationSeed = 105, colorHex = "#F2C14E", requiredLevelToUnlock = 2
        ),
        HouseRoomEntity(
            code = "decisiones", name = "Sala de las Decisiones",
            description = "Un pequeño escenario donde tus decisiones cambian la historia.",
            orderIndex = 5, illustrationSeed = 106, colorHex = "#B185DB", requiredLevelToUnlock = 3
        ),
        HouseRoomEntity(
            code = "respeto", name = "Jardín del Respeto",
            description = "Cada acción positiva ayuda a que el jardín florezca.",
            orderIndex = 6, illustrationSeed = 107, colorHex = "#6FCF97", requiredLevelToUnlock = 3
        ),
        HouseRoomEntity(
            code = "historias", name = "Archivo de Historias",
            description = "Una colección de historias interactivas para vivir una y otra vez.",
            orderIndex = 7, illustrationSeed = 108, colorHex = "#EF9CC2", requiredLevelToUnlock = 4
        )
    )
}
