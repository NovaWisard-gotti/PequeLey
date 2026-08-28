package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.GardenProgressEntity
import com.educalab.pequeley.data.local.entity.UnlockedDecorationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GardenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(garden: GardenProgressEntity): Long

    @Query("SELECT * FROM garden_progress WHERE userId = :userId LIMIT 1")
    suspend fun get(userId: Long): GardenProgressEntity?

    @Query("SELECT * FROM garden_progress WHERE userId = :userId LIMIT 1")
    fun observe(userId: Long): Flow<GardenProgressEntity?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun unlockDecoration(decoration: UnlockedDecorationEntity): Long

    @Query("SELECT * FROM unlocked_decoration WHERE userId = :userId")
    suspend fun decorationsFor(userId: Long): List<UnlockedDecorationEntity>
}
