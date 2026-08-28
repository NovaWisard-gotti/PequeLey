package com.educalab.pequeley.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.educalab.pequeley.data.local.entity.ResponsibilityTaskEntity

@Dao
interface ResponsibilityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(tasks: List<ResponsibilityTaskEntity>)

    @Query("SELECT * FROM responsibility_task")
    suspend fun getAll(): List<ResponsibilityTaskEntity>

    @Query("SELECT COUNT(*) FROM responsibility_task")
    suspend fun count(): Int
}
