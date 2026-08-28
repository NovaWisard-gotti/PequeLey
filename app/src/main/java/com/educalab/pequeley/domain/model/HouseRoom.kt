package com.educalab.pequeley.domain.model

data class HouseRoom(
    val code: String,
    val name: String,
    val description: String,
    val orderIndex: Int,
    val illustrationSeed: Int,
    val colorHex: String,
    val requiredLevelToUnlock: Int,
    val unlocked: Boolean = false
)

enum class RoomModuleState { LOCKED, AVAILABLE, STARTED, COMPLETED, MASTERED }
