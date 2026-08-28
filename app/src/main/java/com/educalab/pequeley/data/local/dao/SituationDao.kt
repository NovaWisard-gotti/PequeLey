package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.*

@Dao
interface SituationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSituations(situations: List<DailySituationEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSteps(steps: List<SituationStepEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDecisions(decisions: List<DecisionEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertConsequences(consequences: List<DecisionConsequenceEntity>)

    @Query("SELECT * FROM daily_situation WHERE roomCode = :roomCode")
    suspend fun situationsForRoom(roomCode: String): List<DailySituationEntity>

    @Query("SELECT * FROM daily_situation WHERE code = :code")
    suspend fun getSituation(code: String): DailySituationEntity?

    @Query("SELECT * FROM situation_step WHERE situationCode = :code ORDER BY orderIndex ASC")
    suspend fun stepsFor(code: String): List<SituationStepEntity>

    @Query("SELECT * FROM decision WHERE situationCode = :code AND stepOrderIndex = :stepOrder")
    suspend fun decisionsFor(code: String, stepOrder: Int): List<DecisionEntity>

    @Query("SELECT * FROM decision_consequence WHERE decisionId = :decisionId")
    suspend fun consequencesFor(decisionId: Long): List<DecisionConsequenceEntity>

    @Query("SELECT COUNT(*) FROM daily_situation")
    suspend fun count(): Int
}
