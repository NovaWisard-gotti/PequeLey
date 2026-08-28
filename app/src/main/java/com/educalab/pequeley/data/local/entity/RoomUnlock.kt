package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "room_unlock",
    foreignKeys = [
        ForeignKey(entity = UserProfileEntity::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = HouseRoomEntity::class, parentColumns = ["code"], childColumns = ["roomCode"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index(value = ["userId", "roomCode"], unique = true)]
)
data class RoomUnlockEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val roomCode: String,
    val unlocked: Boolean,
    val unlockedAt: Long? = null
)
