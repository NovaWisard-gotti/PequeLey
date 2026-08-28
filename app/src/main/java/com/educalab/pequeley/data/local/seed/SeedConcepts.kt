package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.entity.ConceptStoryEntity
import com.educalab.pequeley.data.local.entity.LegalConceptEntity

/** Los 12 conceptos principales, explicados siempre en lenguaje cotidiano (sección CONCEPTOS PRINCIPALES). */
object SeedConcepts {

    val CONCEPTS = listOf(
        LegalConceptEntity("regla", "Regla", "Un acuerdo que ayuda a convivir y saber cómo actuar.", 201),
        LegalConceptEntity("derecho", "Derecho", "Algo importante que todas las personas deben poder disfrutar y respetar.", 202),
        LegalConceptEntity("responsabilidad", "Responsabilidad", "Algo que debemos cumplir para ayudar a que todo funcione bien.", 203),
        LegalConceptEntity("respeto", "Respeto", "Tratar a los demás como te gustaría que te trataran a ti.", 204),
        LegalConceptEntity("justicia", "Justicia", "Buscar una solución que sea respetuosa y razonable para todos.", 205),
        LegalConceptEntity("acuerdo", "Acuerdo", "Una decisión que varias personas aceptan después de conversar.", 206),
        LegalConceptEntity("convivencia", "Convivencia", "Vivir y compartir espacios con otras personas de buena manera.", 207),
        LegalConceptEntity("consecuencia", "Consecuencia", "Lo que ocurre después de una decisión que tomamos.", 208),
        LegalConceptEntity("solucion", "Solución", "Una idea que ayuda a resolver un problema entre personas.", 209),
        LegalConceptEntity("dialogo", "Diálogo", "Hablar y escuchar para entenderse mejor.", 210),
        LegalConceptEntity("cuidado", "Cuidado", "Prestar atención a algo o alguien para que esté bien.", 211),
        LegalConceptEntity("responsabilidad_compartida", "Responsabilidad compartida", "Cuando varias personas cuidan juntas de algo.", 212)
    )

    val STORIES = listOf(
        ConceptStoryEntity(0, "regla", "El semáforo del parque", "En el parque hay una regla: esperar antes de cruzar. Gracias a esa regla, nadie se lastima.", 301),
        ConceptStoryEntity(0, "regla", "Las reglas del juego", "Cuando todos siguen las mismas reglas del juego, jugar es más divertido para todos.", 302),
        ConceptStoryEntity(0, "derecho", "El derecho a jugar", "Todos los niños tienen derecho a jugar, sin importar si son buenos o nuevos en el juego.", 303),
        ConceptStoryEntity(0, "derecho", "El derecho a aprender", "Cada niño merece un lugar donde pueda aprender tranquilo y sin miedo.", 304),
        ConceptStoryEntity(0, "responsabilidad", "La planta que necesitaba agua", "Marco olvidó regar la planta. Al día siguiente entendió que cuidarla era su responsabilidad.", 305),
        ConceptStoryEntity(0, "respeto", "Gustos diferentes", "A Sofía le gusta el fútbol; a Iker, dibujar. Ambos se respetan aunque les gusten cosas distintas.", 306),
        ConceptStoryEntity(0, "justicia", "El columpio compartido", "Dos niños querían el mismo columpio. En vez de pelear, decidieron turnarse: eso fue justo.", 307),
        ConceptStoryEntity(0, "acuerdo", "El horario de la pelota", "El grupo acordó usar la pelota por turnos de diez minutos cada uno.", 308),
        ConceptStoryEntity(0, "convivencia", "El comedor de la escuela", "Todos comen juntos, respetan la fila y bajan la voz: así conviven mejor.", 309),
        ConceptStoryEntity(0, "consecuencia", "El vaso derramado", "Ana corrió en el pasillo y derramó agua. Entendió que correr adentro tiene consecuencias.", 310),
        ConceptStoryEntity(0, "solucion", "Dos ideas, una solución", "Cuando dos amigos no se ponían de acuerdo, unieron sus ideas y encontraron algo mejor.", 311),
        ConceptStoryEntity(0, "dialogo", "Hablar antes de enojarse", "En vez de gritar, los amigos se sentaron a hablar. Así entendieron mejor el problema.", 312),
        ConceptStoryEntity(0, "cuidado", "El libro de la biblioteca", "Leo cuidó el libro prestado como si fuera propio, para que otro niño también pudiera disfrutarlo.", 313),
        ConceptStoryEntity(0, "responsabilidad_compartida", "El salón ordenado", "Todo el salón ayudó a ordenar: cuando todos cuidan, el trabajo es más fácil.", 314),
        ConceptStoryEntity(0, "justicia", "Escuchar las dos versiones", "Antes de decidir, Lexi escuchó a los dos niños. Así entendió mejor lo que había pasado.", 315)
    )
}
