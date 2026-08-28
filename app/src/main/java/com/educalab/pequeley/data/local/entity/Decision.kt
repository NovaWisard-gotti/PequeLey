package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "decision",
    foreignKeys = [ForeignKey(entity = DailySituationEntity::class, parentColumns = ["code"], childColumns = ["situationCode"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("situationCode")]
)
data class DecisionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val situationCode: String,
    val stepOrderIndex: Int,
    val label: String,
    val description: String
)
