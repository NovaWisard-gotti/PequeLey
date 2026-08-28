package com.educalab.pequeley.domain.model

data class CharacterModel(
    val code: String,
    val name: String,
    val role: String,
    val personality: String,
    val shapeSeed: Int,
    val paletteSeed: Int,
    val accessorySeed: Int
)

data class CharacterExpressionModel(
    val characterCode: String,
    val mood: Mood,
    val description: String
)
