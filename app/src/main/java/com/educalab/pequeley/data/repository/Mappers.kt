package com.educalab.pequeley.data.repository

import com.educalab.pequeley.data.local.entity.*
import com.educalab.pequeley.domain.model.*

fun UserProfileEntity.toModel() = UserProfileModel(id, alias, avatarId, createdAt, totalXp, currentLevel, soundEnabled, hapticEnabled)

fun HouseRoomEntity.toModel(unlocked: Boolean) = HouseRoom(code, name, description, orderIndex, illustrationSeed, colorHex, requiredLevelToUnlock, unlocked)

fun LegalConceptEntity.toModel(stories: List<ConceptStoryEntity>) = LegalConcept(
    code, title, everydayExplanation, illustrationSeed,
    stories.map { ConceptStory(it.id, it.title, it.body, it.illustrationSeed) }
)

fun CharacterEntity.toModel() = CharacterModel(code, name, role, personality, shapeSeed, paletteSeed, accessorySeed)

fun CharacterExpressionEntity.toModel() = CharacterExpressionModel(characterCode, Mood.valueOf(mood), description)

fun mechanicFromString(value: String): MechanicType = try {
    MechanicType.valueOf(value)
} catch (e: IllegalArgumentException) {
    MechanicType.OBSERVE_AND_ACT
}

fun stepTypeFromString(value: String): StepType = try {
    StepType.valueOf(value)
} catch (e: IllegalArgumentException) {
    StepType.NARRATION
}

fun DecisionConsequenceEntity.toModel() = ConsequenceModel(outcomeText, isPositive, gardenImpact, xpAward)

fun DecisionEntity.toModel(consequences: List<DecisionConsequenceEntity>) =
    DecisionModel(id, label, description, consequences.map { it.toModel() })

fun SituationStepEntity.toModel(decisions: List<DecisionModel>) =
    SituationStepModel(orderIndex, stepTypeFromString(stepType), prompt, illustrationSeed, decisions)

fun DailySituationEntity.toModel(steps: List<SituationStepModel>) =
    Situation(code, roomCode, title, summary, difficulty, mechanicFromString(mechanicType), illustrationSeed, steps)

fun StoryChoiceEntity.toModel() = StoryChoiceModel(label, leadsToSceneOrder, isEnding, consequenceText)

fun StorySceneEntity.toModel(choices: List<StoryChoiceModel>) = StorySceneModel(id, orderIndex, text, illustrationSeed, choices)

fun StoryEntity.toModel(scenes: List<StorySceneModel>) = StoryModel(code, title, summary, mechanicFromString(mechanicType), coverIllustrationSeed, scenes)

fun ResponsibilityTaskEntity.toModel(completed: Boolean) = ResponsibilityTaskModel(code, title, description, objectIllustrationSeed, careAction, completed)

fun RightLessonEntity.toModel(opened: Boolean) = RightLessonModel(code, title, everydayExplanation, storyText, illustrationSeed, opened)

fun ChallengeEntity.toModel(completed: Boolean) = ChallengeModel(code, title, description, situationRef, difficulty, completed)

fun BadgeEntity.toModel(earned: Boolean) = BadgeModel(code, title, description, illustrationSeed, BadgeCriteriaType.valueOf(criteriaType), criteriaValue, earned)

fun GardenProgressEntity.toModel() = GardenState(growthLevel, flowers, paths, animals)

fun ProgressEntity.toModel() = RoomProgress(roomCode, situationsCompleted, storiesCompleted, challengesCompleted, agreementsCreated, masteryLevel)
