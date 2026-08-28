package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "character_expression",
    foreignKeys = [ForeignKey(entity = CharacterEntity::class, parentColumns = ["code"], childColumns = ["characterCode"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("characterCode")]
)
data class CharacterExpressionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val characterCode: String,
    val mood: String,
    val description: String
)
