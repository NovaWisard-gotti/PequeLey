package com.educalab.pequeley.domain.model

data class StoryModel(
    val code: String,
    val title: String,
    val summary: String,
    val mechanicType: MechanicType,
    val coverIllustrationSeed: Int,
    val scenes: List<StorySceneModel> = emptyList()
)

data class StorySceneModel(
    val id: Long,
    val orderIndex: Int,
    val text: String,
    val illustrationSeed: Int,
    val choices: List<StoryChoiceModel> = emptyList()
)

data class StoryChoiceModel(
    val label: String,
    val leadsToSceneOrder: Int?,
    val isEnding: Boolean,
    val consequenceText: String
)
