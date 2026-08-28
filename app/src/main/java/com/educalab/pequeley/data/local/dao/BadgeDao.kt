package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.BadgeEntity
import com.educalab.pequeley.data.local.entity.UserBadgeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BadgeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(badges: List<BadgeEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun awardBadge(userBadge: UserBadgeEntity): Long

    @Query("SELECT * FROM badge")
    suspend fun getAll(): List<BadgeEntity>

    @Query("SELECT * FROM user_badge WHERE userId = :userId")
    fun observeEarned(userId: Long): Flow<List<UserBadgeEntity>>

    @Query("SELECT * FROM user_badge WHERE userId = :userId")
    suspend fun earnedForUser(userId: Long): List<UserBadgeEntity>

    @Query("SELECT COUNT(*) FROM user_badge WHERE userId = :userId AND badgeCode = :code")
    suspend fun hasBadge(userId: Long, code: String): Int

    @Query("SELECT COUNT(*) FROM badge")
    suspend fun count(): Int
}
