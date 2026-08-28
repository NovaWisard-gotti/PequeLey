package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Registra qué situaciones o historias ya vivió el niño, para mostrar
 * la marca de "completado" en la lista sin bloquear el acceso: puede
 * volver a jugarlas cuantas veces quiera.
 */
@Entity(
    tableName = "completed_activity",
    foreignKeys = [ForeignKey(entity = UserProfileEntity::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index(value = ["userId", "activityCode"], unique = true), Index(value = ["userId"])]
)
data class CompletedActivityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val activityCode: String,
    val completedAt: Long
)
