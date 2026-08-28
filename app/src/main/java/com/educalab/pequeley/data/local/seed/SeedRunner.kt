package com.educalab.pequeley.data.local.seed

import com.educalab.pequeley.data.local.PequeLeyDatabase
import com.educalab.pequeley.data.local.entity.*

/**
 * Orquesta la carga de datos semilla la primera vez que se crea la base
 * de datos, para que la instalación inicial ya se sienta completa
 * (sección 24 de la especificación maestra).
 */
object SeedRunner {

    suspend fun run(db: PequeLeyDatabase) {
        // Salvaguarda: si ya hay habitaciones, no volver a sembrar (evita duplicados).
        if (db.houseRoomDao().count() > 0) return

        db.houseRoomDao().insertAll(SeedRooms.ALL)

        db.legalConceptDao().insertConcepts(SeedConcepts.CONCEPTS)
        db.legalConceptDao().insertStories(SeedConcepts.STORIES)

        db.characterDao().insertAll(SeedCharacters.ALL)
        db.characterDao().insertExpressions(SeedCharacters.EXPRESSIONS)

        db.responsibilityDao().insertAll(SeedResponsibilities.ALL)
        db.rightLessonDao().insertAll(SeedRights.ALL)
        db.badgeDao().insertAll(SeedBadges.ALL)
        db.challengeDao().insertAll(SeedChallenges.ALL)

        seedSituations(db, SeedSituations.ALL)
        seedStories(db, SeedStories.ALL)
    }

    private suspend fun seedSituations(db: PequeLeyDatabase, seeds: List<SituationSeed>) {
        val situationEntities = seeds.map {
            DailySituationEntity(
                code = it.code, roomCode = it.room, title = it.title, summary = it.summary,
                difficulty = it.difficulty, mechanicType = it.mechanicType, illustrationSeed = it.code.hashCode() and 0xFFF
            )
        }
        db.situationDao().insertSituations(situationEntities)

        val stepEntities = mutableListOf<SituationStepEntity>()
        seeds.forEach { seed ->
            stepEntities += SituationStepEntity(
                situationCode = seed.code, orderIndex = 0, stepType = "NARRATION",
                prompt = seed.narrationText, illustrationSeed = seed.code.hashCode() and 0xFFF
            )
            stepEntities += SituationStepEntity(
                situationCode = seed.code, orderIndex = 1, stepType = "DECISION",
                prompt = seed.decisionPrompt, illustrationSeed = (seed.code.hashCode() * 7) and 0xFFF
            )
        }
        db.situationDao().insertSteps(stepEntities)

        // Las decisiones y consecuencias se insertan situación por situación
        // porque necesitamos el id autogenerado de cada decisión para
        // vincular su consecuencia correspondiente.
        seeds.forEach { seed ->
            val decisionEntities = seed.options.map { option ->
                DecisionEntity(situationCode = seed.code, stepOrderIndex = 1, label = option.label, description = option.description)
            }
            val ids = db.situationDao().insertDecisions(decisionEntities)
            val consequences = ids.zip(seed.options).map { (decisionId, option) ->
                DecisionConsequenceEntity(
                    decisionId = decisionId,
                    outcomeText = option.outcomeText,
                    isPositive = option.isPositive,
                    gardenImpact = option.gardenImpact,
                    xpAward = option.xpAward
                )
            }
            db.situationDao().insertConsequences(consequences)
        }
    }

    private suspend fun seedStories(db: PequeLeyDatabase, seeds: List<StorySeed>) {
        val storyEntities = seeds.map {
            StoryEntity(code = it.code, title = it.title, summary = it.summary, mechanicType = it.mechanicType, coverIllustrationSeed = it.code.hashCode() and 0xFFF)
        }
        db.storyDao().insertStories(storyEntities)

        seeds.forEach { seed ->
            val sceneEntities = seed.scenes.map { scene ->
                StorySceneEntity(storyCode = seed.code, orderIndex = scene.orderIndex, text = scene.text, illustrationSeed = (seed.code.hashCode() + scene.orderIndex) and 0xFFF)
            }
            val sceneIds = db.storyDao().insertScenes(sceneEntities)
            val choiceEntities = mutableListOf<StoryChoiceEntity>()
            sceneIds.zip(seed.scenes).forEach { (sceneId, scene) ->
                scene.choices.forEach { choice ->
                    choiceEntities += StoryChoiceEntity(
                        sceneId = sceneId, label = choice.label, leadsToSceneOrder = choice.leadsToSceneOrder,
                        isEnding = choice.isEnding, consequenceText = choice.consequenceText
                    )
                }
            }
            db.storyDao().insertChoices(choiceEntities)
        }
    }
}
