package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: UserProfileEntity): Long

    @Update
    suspend fun update(profile: UserProfileEntity)

    @Query("SELECT * FROM user_profile WHERE id = :id")
    fun observe(id: Long): Flow<UserProfileEntity?>

    @Query("SELECT * FROM user_profile WHERE id = :id")
    suspend fun get(id: Long): UserProfileEntity?

    @Query("SELECT * FROM user_profile LIMIT 1")
    suspend fun getFirst(): UserProfileEntity?

    @Query("SELECT * FROM user_profile LIMIT 1")
    fun observeFirst(): Flow<UserProfileEntity?>

    @Query("UPDATE user_profile SET totalXp = totalXp + :amount WHERE id = :userId")
    suspend fun addXp(userId: Long, amount: Int)

    @Query("UPDATE user_profile SET currentLevel = :level WHERE id = :userId")
    suspend fun setLevel(userId: Long, level: Int)

    @Query("UPDATE user_profile SET soundEnabled = :enabled WHERE id = :userId")
    suspend fun setSoundEnabled(userId: Long, enabled: Boolean)

    @Query("UPDATE user_profile SET hapticEnabled = :enabled WHERE id = :userId")
    suspend fun setHapticEnabled(userId: Long, enabled: Boolean)
}
