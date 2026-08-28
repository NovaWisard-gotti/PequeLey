package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.HouseRoomEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseRoomDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(rooms: List<HouseRoomEntity>)

    @Query("SELECT * FROM house_room ORDER BY orderIndex ASC")
    fun observeAll(): Flow<List<HouseRoomEntity>>

    @Query("SELECT * FROM house_room ORDER BY orderIndex ASC")
    suspend fun getAll(): List<HouseRoomEntity>

    @Query("SELECT * FROM house_room WHERE code = :code")
    suspend fun getByCode(code: String): HouseRoomEntity?

    @Query("SELECT COUNT(*) FROM house_room")
    suspend fun count(): Int
}
