package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "daily_situation",
    foreignKeys = [ForeignKey(entity = HouseRoomEntity::class, parentColumns = ["code"], childColumns = ["roomCode"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("roomCode")]
)
data class DailySituationEntity(
    @PrimaryKey val code: String,
    val roomCode: String,
    val title: String,
    val summary: String,
    val difficulty: Int,
    val mechanicType: String,
    val illustrationSeed: Int
)
