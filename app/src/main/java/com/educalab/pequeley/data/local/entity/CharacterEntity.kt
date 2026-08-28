package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Personaje de la casa. La apariencia se genera de forma paramétrica
 * (shapeSeed/paletteSeed/accessorySeed) mediante Compose Canvas — ver
 * ui/illustration/CharacterArt.kt — de modo que cada personaje resulta
 * visualmente distinto sin depender de arte bitmap externo.
 */
@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey val code: String,
    val name: String,
    val role: String,
    val personality: String,
    val shapeSeed: Int,
    val paletteSeed: Int,
    val accessorySeed: Int
)
