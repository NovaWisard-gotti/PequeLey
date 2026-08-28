package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "story_choice",
    foreignKeys = [ForeignKey(entity = StorySceneEntity::class, parentColumns = ["id"], childColumns = ["sceneId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("sceneId")]
)
data class StoryChoiceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sceneId: Long,
    val label: String,
    val leadsToSceneOrder: Int?,
    val isEnding: Boolean,
    val consequenceText: String
)
