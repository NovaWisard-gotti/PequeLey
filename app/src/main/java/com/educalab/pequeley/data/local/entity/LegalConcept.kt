package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Concepto básico (Regla, Derecho, Acuerdo...) explicado en lenguaje de 8 años. */
@Entity(tableName = "legal_concept")
data class LegalConceptEntity(
    @PrimaryKey val code: String,
    val title: String,
    val everydayExplanation: String,
    val illustrationSeed: Int
)
