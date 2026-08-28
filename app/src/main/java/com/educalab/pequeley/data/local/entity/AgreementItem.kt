package com.educalab.pequeley.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "agreement_item",
    foreignKeys = [ForeignKey(entity = AgreementEntity::class, parentColumns = ["id"], childColumns = ["agreementId"], onDelete = ForeignKey.CASCADE)],
    indices = [Index("agreementId")]
)
data class AgreementItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val agreementId: Long,
    val symbolCode: String,
    val orderIndex: Int,
    val label: String
)
