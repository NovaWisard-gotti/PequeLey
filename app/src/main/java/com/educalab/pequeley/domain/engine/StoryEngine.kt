package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.StoryChoiceModel
import com.educalab.pequeley.domain.model.StoryModel
import com.educalab.pequeley.domain.model.StorySceneModel

class InvalidStoryStateException(message: String) : Exception(message)

data class StorySession(
    val story: StoryModel,
    val currentSceneOrder: Int = 0,
    val visitedOrders: List<Int> = emptyList(),
    val finished: Boolean = false
) {
    val currentScene: StorySceneModel?
        get() = story.scenes.firstOrNull { it.orderIndex == currentSceneOrder }
}

/**
 * Motor de historias interactivas ramificadas (Archivo de Historias,
 * Sala de las Decisiones). Cada elección puede llevar a una escena
 * distinta o cerrar la historia (final).
 */
class StoryEngine {

    fun start(story: StoryModel): StorySession {
        if (story.scenes.isEmpty()) {
            throw InvalidStoryStateException("La historia '${story.code}' no tiene escenas.")
        }
        val first = story.scenes.sortedBy { it.orderIndex }.firstOrNull()
            ?: throw InvalidStoryStateException("La historia '${story.code}' no tiene una escena inicial válida.")
        return StorySession(story = story, currentSceneOrder = first.orderIndex, visitedOrders = listOf(first.orderIndex))
    }

    fun choose(session: StorySession, choice: StoryChoiceModel): StorySession {
        if (session.finished) {
            throw InvalidStoryStateException("La historia ya finalizó.")
        }
        val scene = session.currentScene
            ?: throw InvalidStoryStateException("No hay escena activa.")
        val belongs = scene.choices.any { it.label == choice.label && it.leadsToSceneOrder == choice.leadsToSceneOrder }
        if (!belongs) {
            throw InvalidStoryStateException("La opción '${choice.label}' no pertenece a la escena actual.")
        }
        if (choice.isEnding || choice.leadsToSceneOrder == null) {
            return session.copy(finished = true)
        }
        val nextScene = session.story.scenes.firstOrNull { it.orderIndex == choice.leadsToSceneOrder }
            ?: throw InvalidStoryStateException("La historia apunta a una escena inexistente (${choice.leadsToSceneOrder}).")
        return session.copy(
            currentSceneOrder = nextScene.orderIndex,
            visitedOrders = session.visitedOrders + nextScene.orderIndex
        )
    }

    fun isComplete(session: StorySession): Boolean = session.finished

    /** Cuántos finales distintos tiene una historia (para rejugabilidad). */
    fun countEndings(story: StoryModel): Int =
        story.scenes.fold(0) { acc, scene -> acc + scene.choices.count { it.isEnding } }
}
