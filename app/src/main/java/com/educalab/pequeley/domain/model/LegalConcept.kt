package com.educalab.pequeley.domain.model

data class LegalConcept(
    val code: String,
    val title: String,
    val everydayExplanation: String,
    val illustrationSeed: Int,
    val stories: List<ConceptStory> = emptyList()
)

data class ConceptStory(
    val id: Long,
    val title: String,
    val body: String,
    val illustrationSeed: Int
)
