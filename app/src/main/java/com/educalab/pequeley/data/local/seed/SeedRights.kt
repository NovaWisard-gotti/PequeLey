package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.entity.RightLessonEntity

/** 8 derechos básicos de la Biblioteca de los Derechos (Módulo 2). */
object SeedRights {
    val ALL = listOf(
        RightLessonEntity("derecho_aprender", "Derecho a aprender",
            "Todos merecemos un lugar donde aprender tranquilos.",
            "En la escuela, cada niño tiene un lugar para aprender a su propio ritmo, sin miedo a equivocarse.", 601),
        RightLessonEntity("derecho_jugar", "Derecho a jugar",
            "Jugar es importante para todos, sin importar si eres nuevo o tienes menos práctica.",
            "Dos niños quieren participar en una actividad. Todos encuentran una forma de que ambos jueguen.", 602),
        RightLessonEntity("derecho_cuidado", "Derecho a recibir cuidado",
            "Todos merecemos que alguien se preocupe por nuestro bienestar.",
            "Cuando alguien se cae en el recreo, otros compañeros lo ayudan y avisan a un adulto.", 603),
        RightLessonEntity("derecho_respeto", "Derecho a ser respetado",
            "Todas las personas merecen ser tratadas con respeto.",
            "Un compañero nuevo habla distinto. El grupo lo escucha con respeto en vez de burlarse.", 604),
        RightLessonEntity("derecho_opinar", "Derecho a expresar una opinión",
            "Tu opinión importa, incluso si es diferente a la de los demás.",
            "En un grupo, alguien propone una idea distinta y todos la escuchan antes de decidir.", 605),
        RightLessonEntity("derecho_seguridad", "Derecho a sentirse seguro",
            "Todos merecemos sentirnos seguros en la escuela y en casa.",
            "Cuando algo te preocupa, puedes contarlo a un adulto de confianza para sentirte más seguro.", 606),
        RightLessonEntity("derecho_descanso", "Derecho a descansar",
            "Descansar y jugar es tan importante como estudiar.",
            "Después de estudiar, el grupo se toma un momento para jugar y descansar la mente.", 607),
        RightLessonEntity("derecho_identidad", "Derecho a ser tú mismo",
            "Puedes tener gustos distintos a los demás y eso está bien.",
            "A algunos les gusta el fútbol, a otros dibujar. Cada uno puede disfrutar lo suyo sin burlas.", 608)
    )
}
