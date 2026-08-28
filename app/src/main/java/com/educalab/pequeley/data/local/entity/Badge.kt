package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "badge")
data class BadgeEntity(
    @PrimaryKey val code: String,
    val title: String,
    val description: String,
    val illustrationSeed: Int,
    val criteriaType: String,
    val criteriaValue: Int
)
