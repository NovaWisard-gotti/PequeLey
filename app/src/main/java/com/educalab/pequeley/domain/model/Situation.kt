package com.educalab.pequeley.domain.model

data class Situation(
    val code: String,
    val roomCode: String,
    val title: String,
    val summary: String,
    val difficulty: Int,
    val mechanicType: MechanicType,
    val illustrationSeed: Int,
    val steps: List<SituationStepModel> = emptyList()
)

data class SituationStepModel(
    val orderIndex: Int,
    val stepType: StepType,
    val prompt: String,
    val illustrationSeed: Int,
    val decisions: List<DecisionModel> = emptyList()
)

data class DecisionModel(
    val id: Long,
    val label: String,
    val description: String,
    val consequences: List<ConsequenceModel> = emptyList()
)

data class ConsequenceModel(
    val outcomeText: String,
    val isPositive: Boolean,
    val gardenImpact: Int,
    val xpAward: Int
)
