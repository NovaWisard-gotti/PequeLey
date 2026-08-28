package com.educalab.pequeley.domain.model

data class ResponsibilityTaskModel(
    val code: String,
    val title: String,
    val description: String,
    val objectIllustrationSeed: Int,
    val careAction: String,
    val completed: Boolean = false
)
