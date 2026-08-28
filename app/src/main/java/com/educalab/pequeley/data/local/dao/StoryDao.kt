package com.educalab.pequeley.data.local.dao

import androidx.room.*
import com.educalab.pequeley.data.local.entity.*

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStories(stories: List<StoryEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertScenes(scenes: List<StorySceneEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertChoices(choices: List<StoryChoiceEntity>)

    @Query("SELECT * FROM story")
    suspend fun getAll(): List<StoryEntity>

    @Query("SELECT * FROM story WHERE code = :code")
    suspend fun getByCode(code: String): StoryEntity?

    @Query("SELECT * FROM story_scene WHERE storyCode = :code ORDER BY orderIndex ASC")
    suspend fun scenesFor(code: String): List<StorySceneEntity>

    @Query("SELECT * FROM story_choice WHERE sceneId = :sceneId")
    suspend fun choicesFor(sceneId: Long): List<StoryChoiceEntity>

    @Query("SELECT COUNT(*) FROM story")
    suspend fun count(): Int
}
