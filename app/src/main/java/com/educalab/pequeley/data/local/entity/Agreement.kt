package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "agreement",
    foreignKeys = [ForeignKey(entity = UserProfileEntity::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("userId")]
)
data class AgreementEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val situationCode: String?,
    val title: String,
    val createdAt: Long
)
