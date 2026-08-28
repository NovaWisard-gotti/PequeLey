package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.entity.ResponsibilityTaskEntity

/** 10 objetos del Taller de Responsabilidades (mínimo temático del Módulo 3). */
object SeedResponsibilities {
    val ALL = listOf(
        ResponsibilityTaskEntity("mochila", "La mochila", "Guardar los materiales en su lugar cada día.", 501, "Ordenar"),
        ResponsibilityTaskEntity("juguete", "El juguete compartido", "Guardarlo con cuidado después de usarlo.", 502, "Guardar"),
        ResponsibilityTaskEntity("planta", "La planta del salón", "Regarla cuando sus hojas empiezan a caer.", 503, "Regar"),
        ResponsibilityTaskEntity("libro", "El libro prestado", "Cuidarlo y devolverlo a tiempo.", 504, "Cuidar"),
        ResponsibilityTaskEntity("mesa", "La mesa del comedor", "Limpiar el espacio después de comer.", 505, "Limpiar"),
        ResponsibilityTaskEntity("botella", "La botella de agua", "Guardarla en la mochila para no perderla.", 506, "Guardar"),
        ResponsibilityTaskEntity("materiales", "Los materiales de arte", "Ordenarlos en su caja al terminar.", 507, "Ordenar"),
        ResponsibilityTaskEntity("mascota_clase", "La mascota de la clase", "Darle de comer en el turno asignado.", 508, "Alimentar"),
        ResponsibilityTaskEntity("bicicleta", "La bicicleta", "Guardarla en un lugar seguro al llegar a casa.", 509, "Guardar"),
        ResponsibilityTaskEntity("ropa_deporte", "La ropa de deporte", "Prepararla la noche anterior para no olvidarla.", 510, "Preparar")
    )
}
