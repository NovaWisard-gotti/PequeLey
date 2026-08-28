package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "challenge_attempt",
    foreignKeys = [
        ForeignKey(entity = UserProfileEntity::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = ChallengeEntity::class, parentColumns = ["code"], childColumns = ["challengeCode"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("userId"), Index("challengeCode")]
)
data class ChallengeAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val challengeCode: String,
    val completedAt: Long?,
    val success: Boolean,
    val stepsData: String
)
