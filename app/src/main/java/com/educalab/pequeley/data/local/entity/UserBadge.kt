package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_badge",
    foreignKeys = [
        ForeignKey(entity = UserProfileEntity::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = BadgeEntity::class, parentColumns = ["code"], childColumns = ["badgeCode"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["userId", "badgeCode"], unique = true)]
)
data class UserBadgeEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val badgeCode: String,
    val earnedAt: Long
)
