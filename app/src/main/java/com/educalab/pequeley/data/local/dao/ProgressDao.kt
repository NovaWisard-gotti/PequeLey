package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.ProgressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProgressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(progress: ProgressEntity): Long

    @Query("SELECT * FROM progress WHERE userId = :userId AND roomCode = :roomCode LIMIT 1")
    suspend fun get(userId: Long, roomCode: String): ProgressEntity?

    @Query("SELECT * FROM progress WHERE userId = :userId")
    fun observeForUser(userId: Long): Flow<List<ProgressEntity>>

    @Query("SELECT * FROM progress WHERE userId = :userId")
    suspend fun allForUser(userId: Long): List<ProgressEntity>
}
