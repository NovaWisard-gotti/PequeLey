package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/** Una habitación de la casa (Sala de los Acuerdos, Jardín del Respeto, etc.). */
@Entity(tableName = "house_room")
data class HouseRoomEntity(
    @PrimaryKey val code: String,
    val name: String,
    val description: String,
    val orderIndex: Int,
    val illustrationSeed: Int,
    val colorHex: String,
    val requiredLevelToUnlock: Int
)
