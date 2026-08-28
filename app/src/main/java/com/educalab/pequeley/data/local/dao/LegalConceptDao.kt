package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.ConceptStoryEntity
import com.educalab.pequeley.data.local.entity.LegalConceptEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LegalConceptDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertConcepts(concepts: List<LegalConceptEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStories(stories: List<ConceptStoryEntity>)

    @Query("SELECT * FROM legal_concept")
    fun observeAll(): Flow<List<LegalConceptEntity>>

    @Query("SELECT * FROM legal_concept")
    suspend fun getAll(): List<LegalConceptEntity>

    @Query("SELECT * FROM concept_story WHERE conceptCode = :code")
    suspend fun storiesFor(code: String): List<ConceptStoryEntity>

    @Query("SELECT COUNT(*) FROM legal_concept")
    suspend fun count(): Int
}
