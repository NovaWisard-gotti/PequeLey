package com.educalab.pequeley.data.local.seed

data class StoryChoiceSeed(
    val label: String,
    val leadsToSceneOrder: Int?,
    val isEnding: Boolean,
    val consequenceText: String
)

data class StorySceneSeed(
    val orderIndex: Int,
    val text: String,
    val choices: List<StoryChoiceSeed>
)

data class StorySeed(
    val code: String,
    val title: String,
    val summary: String,
    val mechanicType: String,
    val scenes: List<StorySceneSeed>
)
