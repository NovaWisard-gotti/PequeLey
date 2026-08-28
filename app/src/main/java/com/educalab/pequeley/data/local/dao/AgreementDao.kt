package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.AgreementEntity
import com.educalab.pequeley.data.local.entity.AgreementItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgreementDao {
    @Insert
    suspend fun insertAgreement(agreement: AgreementEntity): Long

    @Insert
    suspend fun insertItems(items: List<AgreementItemEntity>)

    @Query("SELECT * FROM agreement WHERE userId = :userId ORDER BY createdAt DESC")
    fun observeForUser(userId: Long): Flow<List<AgreementEntity>>

    @Query("SELECT * FROM agreement_item WHERE agreementId = :agreementId ORDER BY orderIndex ASC")
    suspend fun itemsFor(agreementId: Long): List<AgreementItemEntity>

    @Query("SELECT COUNT(*) FROM agreement WHERE userId = :userId")
    suspend fun countForUser(userId: Long): Int

    @Delete
    suspend fun deleteAgreement(agreement: AgreementEntity)
}
