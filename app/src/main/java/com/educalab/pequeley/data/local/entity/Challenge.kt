package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "challenge")
data class ChallengeEntity(
    @PrimaryKey val code: String,
    val title: String,
    val description: String,
    val situationRef: String,
    val difficulty: Int
)
