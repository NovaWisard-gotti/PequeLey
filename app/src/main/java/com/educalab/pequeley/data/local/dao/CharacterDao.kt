package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.CharacterEntity
import com.educalab.pequeley.data.local.entity.CharacterExpressionEntity

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpressions(expressions: List<CharacterExpressionEntity>)

    @Query("SELECT * FROM character")
    suspend fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM character WHERE code = :code")
    suspend fun getByCode(code: String): CharacterEntity?

    @Query("SELECT * FROM character_expression WHERE characterCode = :code")
    suspend fun expressionsFor(code: String): List<CharacterExpressionEntity>

    @Query("SELECT COUNT(*) FROM character")
    suspend fun count(): Int
}
