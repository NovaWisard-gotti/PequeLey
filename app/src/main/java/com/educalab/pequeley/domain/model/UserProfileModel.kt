package com.educalab.pequeley.domain.model

data class UserProfileModel(
    val id: Long = 0,
    val alias: String,
    val avatarId: Int,
    val createdAt: Long,
    val totalXp: Int = 0,
    val currentLevel: Int = 1,
    val soundEnabled: Boolean = true,
    val hapticEnabled: Boolean = true
)

data class GardenState(
    val growthLevel: Int = 0,
    val flowers: Int = 0,
    val paths: Int = 0,
    val animals: Int = 0
)

data class RoomProgress(
    val roomCode: String,
    val situationsCompleted: Int = 0,
    val storiesCompleted: Int = 0,
    val challengesCompleted: Int = 0,
    val agreementsCreated: Int = 0,
    val masteryLevel: Int = 0
)
