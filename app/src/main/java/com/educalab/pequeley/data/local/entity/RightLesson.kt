package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "right_lesson")
data class RightLessonEntity(
    @PrimaryKey val code: String,
    val title: String,
    val everydayExplanation: String,
    val storyText: String,
    val illustrationSeed: Int
)
