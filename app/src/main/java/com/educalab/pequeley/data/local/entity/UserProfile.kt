package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Perfil local del niño. Nunca contiene datos personales reales: solo alias y avatar. */
@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val alias: String,
    val avatarId: Int,
    val createdAt: Long,
    val totalXp: Int = 0,
    val currentLevel: Int = 1,
    val soundEnabled: Boolean = true,
    val hapticEnabled: Boolean = true
)
