package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.entity.BadgeEntity

/** Las 12 insignias listadas en la especificación, vinculadas a acciones reales. */
object SeedBadges {
    val ALL = listOf(
        BadgeEntity("primer_acuerdo", "Primer Acuerdo", "Creaste tu primer acuerdo en la casa.", 401, "AGREEMENTS_CREATED", 1),
        BadgeEntity("buen_oyente", "Buen Oyente", "Elegiste escuchar en varias situaciones.", 402, "POSITIVE_CONSEQUENCES", 5),
        BadgeEntity("cuidador_respeto", "Cuidador del Respeto", "El Jardín del Respeto creció gracias a ti.", 403, "GARDEN_LEVEL", 3),
        BadgeEntity("constructor_soluciones", "Constructor de Soluciones", "Construiste soluciones para varios problemas.", 404, "AGREEMENTS_CREATED", 3),
        BadgeEntity("amigo_responsable", "Amigo Responsable", "Completaste tareas de responsabilidad.", 405, "SITUATIONS_COMPLETED", 4),
        BadgeEntity("gran_observador", "Gran Observador", "Observaste con atención muchas situaciones.", 406, "SITUATIONS_COMPLETED", 8),
        BadgeEntity("guardian_reglas", "Guardián de las Reglas", "Comprendiste el sentido de varias reglas.", 407, "SITUATIONS_COMPLETED", 12),
        BadgeEntity("experto_acuerdos", "Experto en Acuerdos", "Creaste cinco acuerdos sólidos.", 408, "AGREEMENTS_CREATED", 5),
        BadgeEntity("protector_convivencia", "Protector de la Convivencia", "Ayudaste a resolver varios conflictos del patio.", 409, "CHALLENGES_COMPLETED", 3),
        BadgeEntity("pensador_justo", "Pensador Justo", "Comparaste consecuencias antes de decidir.", 410, "POSITIVE_CONSEQUENCES", 10),
        BadgeEntity("maestro_dialogo", "Maestro del Diálogo", "Completaste diez historias interactivas.", 411, "STORIES_COMPLETED", 10),
        BadgeEntity("pequeley_experto", "PequeLey Experto", "Desbloqueaste todas las habitaciones de la casa.", 412, "ROOMS_UNLOCKED", 8)
    )
}
