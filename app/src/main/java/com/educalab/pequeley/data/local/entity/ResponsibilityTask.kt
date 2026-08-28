package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "responsibility_task")
data class ResponsibilityTaskEntity(
    @PrimaryKey val code: String,
    val title: String,
    val description: String,
    val objectIllustrationSeed: Int,
    val careAction: String
)
