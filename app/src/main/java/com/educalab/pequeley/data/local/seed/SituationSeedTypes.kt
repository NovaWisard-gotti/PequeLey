package com.educalab.pequeley.data.local.seed

data class DecisionSeed(
    val label: String,
    val description: String,
    val outcomeText: String,
    val isPositive: Boolean,
    val gardenImpact: Int,
    val xpAward: Int
)

data class SituationSeed(
    val code: String,
    val room: String,
    val title: String,
    val summary: String,
    val difficulty: Int,
    val mechanicType: String,
    val narrationText: String,
    val decisionPrompt: String,
    val options: List<DecisionSeed>
)
