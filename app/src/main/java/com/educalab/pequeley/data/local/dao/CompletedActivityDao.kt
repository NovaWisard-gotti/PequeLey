package com.educalab.pequeley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.educalab.pequeley.data.local.entity.CompletedActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedActivityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: CompletedActivityEntity): Long

    @Query("SELECT activityCode FROM completed_activity WHERE userId = :userId")
    suspend fun completedCodesForUser(userId: Long): List<String>

    @Query("SELECT activityCode FROM completed_activity WHERE userId = :userId")
    fun observeCompletedCodes(userId: Long): Flow<List<String>>
}
