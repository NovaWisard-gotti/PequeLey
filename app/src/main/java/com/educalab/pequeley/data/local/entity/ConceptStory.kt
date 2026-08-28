package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "concept_story",
    foreignKeys = [ForeignKey(entity = LegalConceptEntity::class, parentColumns = ["code"], childColumns = ["conceptCode"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("conceptCode")]
)
data class ConceptStoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val conceptCode: String,
    val title: String,
    val body: String,
    val illustrationSeed: Int
)
