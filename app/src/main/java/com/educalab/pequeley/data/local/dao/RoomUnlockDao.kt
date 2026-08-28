package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.RoomUnlockEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RoomUnlockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(unlock: RoomUnlockEntity): Long

    @Query("SELECT * FROM room_unlock WHERE userId = :userId")
    fun observeForUser(userId: Long): Flow<List<RoomUnlockEntity>>

    @Query("SELECT * FROM room_unlock WHERE userId = :userId AND roomCode = :roomCode LIMIT 1")
    suspend fun get(userId: Long, roomCode: String): RoomUnlockEntity?

    @Query("UPDATE room_unlock SET unlocked = 1, unlockedAt = :time WHERE userId = :userId AND roomCode = :roomCode")
    suspend fun markUnlocked(userId: Long, roomCode: String, time: Long)
}
