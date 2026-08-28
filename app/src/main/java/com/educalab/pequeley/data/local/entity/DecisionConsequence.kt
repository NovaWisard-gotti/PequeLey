package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "decision_consequence",
    foreignKeys = [ForeignKey(entity = DecisionEntity::class, parentColumns = ["id"], childColumns = ["decisionId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("decisionId")]
)
data class DecisionConsequenceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val decisionId: Long,
    val outcomeText: String,
    val isPositive: Boolean,
    val gardenImpact: Int,
    val xpAward: Int
)
