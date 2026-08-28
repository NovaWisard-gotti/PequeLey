package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "situation_step",
    foreignKeys = [ForeignKey(entity = DailySituationEntity::class, parentColumns = ["code"], childColumns = ["situationCode"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("situationCode")]
)
data class SituationStepEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val situationCode: String,
    val orderIndex: Int,
    val stepType: String,
    val prompt: String,
    val illustrationSeed: Int
)
