package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "garden_progress",
    foreignKeys = [ForeignKey(entity = UserProfileEntity::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index(value = ["userId"], unique = true)]
)
data class GardenProgressEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val growthLevel: Int = 0,
    val flowers: Int = 0,
    val paths: Int = 0,
    val animals: Int = 0,
    val lastUpdated: Long
)
