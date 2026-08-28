package com.educalab.pequeley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.educalab.pequeley.data.local.entity.RightLessonEntity

@Dao
interface RightLessonDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(lessons: List<RightLessonEntity>)

    @Query("SELECT * FROM right_lesson")
    suspend fun getAll(): List<RightLessonEntity>

    @Query("SELECT COUNT(*) FROM right_lesson")
    suspend fun count(): Int
}
