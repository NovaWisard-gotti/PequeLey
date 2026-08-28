package com.educalab.pequeley.domain.model

data class ChallengeModel(
    val code: String,
    val title: String,
    val description: String,
    val situationRef: String,
    val difficulty: Int,
    val completed: Boolean = false
)
