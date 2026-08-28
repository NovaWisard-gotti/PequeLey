package com.educalab.pequeley.domain.model

data class RightLessonModel(
    val code: String,
    val title: String,
    val everydayExplanation: String,
    val storyText: String,
    val illustrationSeed: Int,
    val opened: Boolean = false
)
