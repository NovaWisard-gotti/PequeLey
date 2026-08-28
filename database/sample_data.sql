-- ============================================================
-- PequeLey — Datos de muestra (sample_data.sql)
-- ------------------------------------------------------------
-- NOTA IMPORTANTE: la fuente de verdad de los datos semilla de la
-- aplicación es el código Kotlin en
-- app/src/main/java/com/educalab/pequeley/data/local/seed/
-- (SeedRooms, SeedConcepts, SeedCharacters, SeedSituations,
-- SeedStories, SeedBadges, SeedResponsibilities, SeedRights,
-- SeedChallenges + SeedRunner), que se ejecuta automáticamente la
-- primera vez que Room crea la base de datos, e inserta:
--   8 habitaciones · 12 conceptos · 15 personajes · 32 situaciones
--   20 historias · 15 desafíos · 12 insignias · 10 responsabilidades
--   8 derechos
--
-- Este archivo .sql es una MUESTRA REPRESENTATIVA en SQL puro del
-- mismo contenido, útil para inspección directa de la base de datos,
-- documentación o carga manual en una herramienta externa de SQLite.
-- ============================================================

-- ---------------- Habitaciones (las 8 completas) ----------------
INSERT INTO house_room (code, name, description, orderIndex, illustrationSeed, colorHex, requiredLevelToUnlock) VALUES
('reglas', 'Habitación de las Reglas', 'Descubre por qué los acuerdos ayudan a que todo funcione mejor.', 0, 101, '#F6A93B', 1),
('derechos', 'Biblioteca de los Derechos', 'Libros ilustrados que cuentan historias sobre cosas que todos merecemos.', 1, 102, '#5B8DEF', 1),
('responsabilidades', 'Taller de Responsabilidades', 'Objetos que necesitan cuidado: aprende qué significa ser responsable.', 2, 103, '#E2725B', 1),
('acuerdos', 'Sala de los Acuerdos', 'Una mesa donde se construyen acuerdos escuchando a todos.', 3, 104, '#8FD4C1', 2),
('convivencia', 'Patio de la Convivencia', 'Pequeños conflictos cotidianos que puedes ayudar a resolver.', 4, 105, '#F2C14E', 2),
('decisiones', 'Sala de las Decisiones', 'Un pequeño escenario donde tus decisiones cambian la historia.', 5, 106, '#B185DB', 3),
('respeto', 'Jardín del Respeto', 'Cada acción positiva ayuda a que el jardín florezca.', 6, 107, '#6FCF97', 3),
('historias', 'Archivo de Historias', 'Una colección de historias interactivas para vivir una y otra vez.', 7, 108, '#EF9CC2', 4);

-- ---------------- Conceptos (los 12 completos) ----------------
INSERT INTO legal_concept (code, title, everydayExplanation, illustrationSeed) VALUES
('regla', 'Regla', 'Un acuerdo que ayuda a convivir y saber cómo actuar.', 201),
('derecho', 'Derecho', 'Algo importante que todas las personas deben poder disfrutar y respetar.', 202),
('responsabilidad', 'Responsabilidad', 'Algo que debemos cumplir para ayudar a que todo funcione bien.', 203),
('respeto', 'Respeto', 'Tratar a los demás como te gustaría que te trataran a ti.', 204),
('justicia', 'Justicia', 'Buscar una solución que sea respetuosa y razonable para todos.', 205),
('acuerdo', 'Acuerdo', 'Una decisión que varias personas aceptan después de conversar.', 206),
('convivencia', 'Convivencia', 'Vivir y compartir espacios con otras personas de buena manera.', 207),
('consecuencia', 'Consecuencia', 'Lo que ocurre después de una decisión que tomamos.', 208),
('solucion', 'Solución', 'Una idea que ayuda a resolver un problema entre personas.', 209),
('dialogo', 'Diálogo', 'Hablar y escuchar para entenderse mejor.', 210),
('cuidado', 'Cuidado', 'Prestar atención a algo o alguien para que esté bien.', 211),
('responsabilidad_compartida', 'Responsabilidad compartida', 'Cuando varias personas cuidan juntas de algo.', 212);

INSERT INTO concept_story (conceptCode, title, body, illustrationSeed) VALUES
('regla', 'El semáforo del parque', 'En el parque hay una regla: esperar antes de cruzar. Gracias a esa regla, nadie se lastima.', 301),
('derecho', 'El derecho a jugar', 'Todos los niños tienen derecho a jugar, sin importar si son buenos o nuevos en el juego.', 303),
('justicia', 'El columpio compartido', 'Dos niños querían el mismo columpio. En vez de pelear, decidieron turnarse: eso fue justo.', 307),
('dialogo', 'Hablar antes de enojarse', 'En vez de gritar, los amigos se sentaron a hablar. Así entendieron mejor el problema.', 312);

-- ---------------- Personajes (los 15 completos) ----------------
INSERT INTO character (code, name, role, personality, shapeSeed, paletteSeed, accessorySeed) VALUES
('lexi', 'Lexi', 'Guía de la casa', 'Curiosa, buena oyente, busca soluciones', 1, 1, 1),
('ana', 'Ana', 'Vecina del patio', 'Alegre y un poco impaciente', 2, 2, 2),
('marco', 'Marco', 'Amigo del taller', 'Distraído pero muy responsable cuando se lo proponen', 3, 3, 3),
('sofia', 'Sofía', 'Amiga del jardín', 'Le encanta el fútbol y compartir', 4, 4, 4),
('iker', 'Iker', 'Amigo del jardín', 'Tranquilo, le encanta dibujar', 5, 5, 5),
('leo', 'Leo', 'Bibliotecario junior', 'Cuidadoso con los libros y objetos prestados', 6, 6, 6),
('mia', 'Mía', 'Amiga de la sala de acuerdos', 'Buena para proponer ideas', 7, 7, 7),
('tomas', 'Tomás', 'Amigo del patio', 'A veces se enoja rápido pero aprende rápido también', 8, 8, 8),
('valentina', 'Valentina', 'Amiga de las decisiones', 'Reflexiva, piensa antes de actuar', 9, 9, 9),
('nico', 'Nico', 'Amigo responsable', 'Cuida las plantas del taller', 10, 10, 10),
('emma', 'Emma', 'Narradora del archivo de historias', 'Le encanta contar y escuchar historias', 11, 11, 11),
('diego', 'Diego', 'Amigo del comedor', 'Siempre respeta la fila', 12, 12, 12),
('luna', 'Luna', 'Amiga del jardín del respeto', 'Escucha con atención a quien está triste', 13, 13, 13),
('hugo', 'Hugo', 'Amigo de la biblioteca', 'Curioso sobre los derechos de todos', 14, 14, 14),
('clara', 'Clara', 'Amiga constructora de acuerdos', 'Le gusta unir ideas de todos', 15, 15, 15);

INSERT INTO character_expression (characterCode, mood, description) VALUES
('lexi', 'HAPPY', 'Lexi sonriendo al ver una buena decisión.'),
('lexi', 'THINKING', 'Lexi pensando en voz alta.'),
('ana', 'HAPPY', 'Ana contenta de compartir el juego.'),
('tomas', 'CALM', 'Tomás más tranquilo después de conversar.');

-- ---------------- Situación de ejemplo completa (con pasos, decisiones y consecuencias) ----------------
INSERT INTO daily_situation (code, roomCode, title, summary, difficulty, mechanicType, illustrationSeed) VALUES
('sit_turno_columpio', 'reglas', 'El turno del columpio', 'Dos niños quieren usar el mismo columpio.', 2, 'DIALOGUE_CHOICE', 501);

INSERT INTO situation_step (situationCode, orderIndex, stepType, prompt, illustrationSeed) VALUES
('sit_turno_columpio', 0, 'NARRATION', 'Hay un solo columpio libre y dos niños corren hacia él al mismo tiempo.', 502),
('sit_turno_columpio', 1, 'DECISION', '¿Qué propones?', 503);

INSERT INTO decision (id, situationCode, stepOrderIndex, label, description) VALUES
(9001, 'sit_turno_columpio', 1, 'Contar hasta 20 por turno', 'Proponer un tiempo justo para cada uno'),
(9002, 'sit_turno_columpio', 1, 'Empujar para llegar primero', 'Adelantarse sin avisar');

INSERT INTO decision_consequence (decisionId, outcomeText, isPositive, gardenImpact, xpAward) VALUES
(9001, 'Ambos disfrutan el columpio sin pelear.', 1, 5, 10),
(9002, 'El otro niño se siente mal y ya no quiere jugar contigo.', 0, -3, 2);

-- ---------------- Historia de ejemplo completa (con escenas y elecciones) ----------------
INSERT INTO story (code, title, summary, mechanicType, coverIllustrationSeed) VALUES
('story_turno_ana', 'El turno de Ana', 'Ana espera su turno para jugar con la pelota.', 'DIALOGUE_CHOICE', 601);

INSERT INTO story_scene (id, storyCode, orderIndex, text, illustrationSeed) VALUES
(8001, 'story_turno_ana', 0, 'Ana quiere jugar con la pelota, pero otro niño la tiene desde hace rato.', 602),
(8002, 'story_turno_ana', 1, 'El niño piensa un momento y le dice que sí, que jueguen juntos.', 603);

INSERT INTO story_choice (sceneId, label, leadsToSceneOrder, isEnding, consequenceText) VALUES
(8001, 'Pedirla con calma', 1, 0, 'El otro niño la escucha.'),
(8001, 'Quitársela de las manos', NULL, 1, 'El otro niño se enoja y ya no quiere compartir nada.'),
(8002, 'Agradecer y jugar juntos', NULL, 1, 'Terminan jugando los dos y se hacen amigos.');

-- ---------------- Responsabilidades (las 10 completas) ----------------
INSERT INTO responsibility_task (code, title, description, objectIllustrationSeed, careAction) VALUES
('mochila', 'La mochila', 'Guardar los materiales en su lugar cada día.', 501, 'Ordenar'),
('juguete', 'El juguete compartido', 'Guardarlo con cuidado después de usarlo.', 502, 'Guardar'),
('planta', 'La planta del salón', 'Regarla cuando sus hojas empiezan a caer.', 503, 'Regar'),
('libro', 'El libro prestado', 'Cuidarlo y devolverlo a tiempo.', 504, 'Cuidar'),
('mesa', 'La mesa del comedor', 'Limpiar el espacio después de comer.', 505, 'Limpiar'),
('botella', 'La botella de agua', 'Guardarla en la mochila para no perderla.', 506, 'Guardar'),
('materiales', 'Los materiales de arte', 'Ordenarlos en su caja al terminar.', 507, 'Ordenar'),
('mascota_clase', 'La mascota de la clase', 'Darle de comer en el turno asignado.', 508, 'Alimentar'),
('bicicleta', 'La bicicleta', 'Guardarla en un lugar seguro al llegar a casa.', 509, 'Guardar'),
('ropa_deporte', 'La ropa de deporte', 'Prepararla la noche anterior para no olvidarla.', 510, 'Preparar');

-- ---------------- Derechos (los 8 completos) ----------------
INSERT INTO right_lesson (code, title, everydayExplanation, storyText, illustrationSeed) VALUES
('derecho_aprender', 'Derecho a aprender', 'Todos merecemos un lugar donde aprender tranquilos.', 'En la escuela, cada niño tiene un lugar para aprender a su propio ritmo, sin miedo a equivocarse.', 601),
('derecho_jugar', 'Derecho a jugar', 'Jugar es importante para todos, sin importar si eres nuevo o tienes menos práctica.', 'Dos niños quieren participar en una actividad. Todos encuentran una forma de que ambos jueguen.', 602),
('derecho_cuidado', 'Derecho a recibir cuidado', 'Todos merecemos que alguien se preocupe por nuestro bienestar.', 'Cuando alguien se cae en el recreo, otros compañeros lo ayudan y avisan a un adulto.', 603),
('derecho_respeto', 'Derecho a ser respetado', 'Todas las personas merecen ser tratadas con respeto.', 'Un compañero nuevo habla distinto. El grupo lo escucha con respeto en vez de burlarse.', 604),
('derecho_opinar', 'Derecho a expresar una opinión', 'Tu opinión importa, incluso si es diferente a la de los demás.', 'En un grupo, alguien propone una idea distinta y todos la escuchan antes de decidir.', 605),
('derecho_seguridad', 'Derecho a sentirse seguro', 'Todos merecemos sentirnos seguros en la escuela y en casa.', 'Cuando algo te preocupa, puedes contarlo a un adulto de confianza para sentirte más seguro.', 606),
('derecho_descanso', 'Derecho a descansar', 'Descansar y jugar es tan importante como estudiar.', 'Después de estudiar, el grupo se toma un momento para jugar y descansar la mente.', 607),
('derecho_identidad', 'Derecho a ser tú mismo', 'Puedes tener gustos distintos a los demás y eso está bien.', 'A algunos les gusta el fútbol, a otros dibujar. Cada uno puede disfrutar lo suyo sin burlas.', 608);

-- ---------------- Desafíos (muestra de 3 de los 15) ----------------
INSERT INTO challenge (code, title, description, situationRef, difficulty) VALUES
('challenge_todos_quieren_jugar', 'Todos quieren jugar', 'Ayuda a que un grupo grande se organice para jugar por turnos.', 'sit_turno_columpio', 1),
('challenge_no_escuchado', 'Alguien no fue escuchado', 'Ayuda a que todos puedan dar su opinión antes de decidir.', 'sit_opinar_grupo', 2),
('challenge_reconocer_error', 'Reconocer un error', 'Ayuda a reparar un error con honestidad.', 'sit_decision_error', 2);

-- ---------------- Insignias (las 12 completas) ----------------
INSERT INTO badge (code, title, description, illustrationSeed, criteriaType, criteriaValue) VALUES
('primer_acuerdo', 'Primer Acuerdo', 'Creaste tu primer acuerdo en la casa.', 401, 'AGREEMENTS_CREATED', 1),
('buen_oyente', 'Buen Oyente', 'Elegiste escuchar en varias situaciones.', 402, 'POSITIVE_CONSEQUENCES', 5),
('cuidador_respeto', 'Cuidador del Respeto', 'El Jardín del Respeto creció gracias a ti.', 403, 'GARDEN_LEVEL', 3),
('constructor_soluciones', 'Constructor de Soluciones', 'Construiste soluciones para varios problemas.', 404, 'AGREEMENTS_CREATED', 3),
('amigo_responsable', 'Amigo Responsable', 'Completaste tareas de responsabilidad.', 405, 'SITUATIONS_COMPLETED', 4),
('gran_observador', 'Gran Observador', 'Observaste con atención muchas situaciones.', 406, 'SITUATIONS_COMPLETED', 8),
('guardian_reglas', 'Guardián de las Reglas', 'Comprendiste el sentido de varias reglas.', 407, 'SITUATIONS_COMPLETED', 12),
('experto_acuerdos', 'Experto en Acuerdos', 'Creaste cinco acuerdos sólidos.', 408, 'AGREEMENTS_CREATED', 5),
('protector_convivencia', 'Protector de la Convivencia', 'Ayudaste a resolver varios conflictos del patio.', 409, 'CHALLENGES_COMPLETED', 3),
('pensador_justo', 'Pensador Justo', 'Comparaste consecuencias antes de decidir.', 410, 'POSITIVE_CONSEQUENCES', 10),
('maestro_dialogo', 'Maestro del Diálogo', 'Completaste diez historias interactivas.', 411, 'STORIES_COMPLETED', 10),
('pequeley_experto', 'PequeLey Experto', 'Desbloqueaste todas las habitaciones de la casa.', 412, 'ROOMS_UNLOCKED', 8);

-- ---------------- Usuario y progreso de ejemplo (para pruebas manuales) ----------------
INSERT INTO user_profile (id, alias, avatarId, createdAt, totalXp, currentLevel, soundEnabled, hapticEnabled) VALUES
(1, 'Explorador', 3, 1700000000000, 45, 1, 1, 1);

INSERT INTO room_unlock (userId, roomCode, unlocked, unlockedAt) VALUES
(1, 'reglas', 1, 1700000000000),
(1, 'derechos', 1, 1700000000000),
(1, 'responsabilidades', 1, 1700000000000);

INSERT INTO garden_progress (userId, growthLevel, flowers, paths, animals, lastUpdated) VALUES
(1, 1, 1, 0, 0, 1700000000000);

-- ============================================================
-- Fin de la muestra de datos
-- ============================================================
