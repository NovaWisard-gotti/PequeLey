package com.educalab.pequeley.domain.model

enum class BadgeCriteriaType {
    SITUATIONS_COMPLETED,
    STORIES_COMPLETED,
    AGREEMENTS_CREATED,
    CHALLENGES_COMPLETED,
    ROOMS_UNLOCKED,
    POSITIVE_CONSEQUENCES,
    GARDEN_LEVEL,
    TOTAL_XP
}

data class BadgeModel(
    val code: String,
    val title: String,
    val description: String,
    val illustrationSeed: Int,
    val criteriaType: BadgeCriteriaType,
    val criteriaValue: Int,
    val earned: Boolean = false
)
