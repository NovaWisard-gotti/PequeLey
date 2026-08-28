package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.ChallengeAttemptEntity
import com.educalab.pequeley.data.local.entity.ChallengeEntity

@Dao
interface ChallengeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(challenges: List<ChallengeEntity>)

    @Insert
    suspend fun insertAttempt(attempt: ChallengeAttemptEntity): Long

    @Query("SELECT * FROM challenge")
    suspend fun getAll(): List<ChallengeEntity>

    @Query("SELECT * FROM challenge_attempt WHERE userId = :userId AND challengeCode = :code")
    suspend fun attemptsFor(userId: Long, code: String): List<ChallengeAttemptEntity>

    @Query("SELECT COUNT(*) FROM challenge")
    suspend fun count(): Int
}
