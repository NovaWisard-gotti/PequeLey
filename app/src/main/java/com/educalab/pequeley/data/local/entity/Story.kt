package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story")
data class StoryEntity(
    @PrimaryKey val code: String,
    val title: String,
    val summary: String,
    val mechanicType: String,
    val coverIllustrationSeed: Int
)
