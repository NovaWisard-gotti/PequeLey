package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "story_scene",
    foreignKeys = [ForeignKey(entity = StoryEntity::class, parentColumns = ["code"], childColumns = ["storyCode"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("storyCode")]
)
data class StorySceneEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val storyCode: String,
    val orderIndex: Int,
    val text: String,
    val illustrationSeed: Int
)
