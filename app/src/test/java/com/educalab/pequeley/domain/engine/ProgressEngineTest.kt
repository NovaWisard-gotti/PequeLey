package com.educalab.pequeley.domain.engine

import com.educalab.pequeley.domain.model.HouseRoom
import com.educalab.pequeley.domain.model.RoomModuleState
import com.educalab.pequeley.domain.model.RoomProgress
import org.junit.Assert.*
import org.junit.Test

class ProgressEngineTest {

    private val engine = ProgressEngine()

    @Test
    fun `levelForXp returns 1 at zero xp`() {
        assertEquals(1, engine.levelForXp(0))
    }

    @Test
    fun `levelForXp returns 1 for negative xp (edge case)`() {
        assertEquals(1, engine.levelForXp(-50))
    }

    @Test
    fun `levelForXp increases every 100 xp`() {
        assertEquals(2, engine.levelForXp(100))
        assertEquals(3, engine.levelForXp(200))
        assertEquals(3, engine.levelForXp(250))
    }

    @Test
    fun `xpToNextLevel counts down within a level`() {
        assertEquals(40, engine.xpToNextLevel(60))
    }

    @Test
    fun `levelProgressRatio is fractional within level`() {
        assertEquals(0.6f, engine.levelProgressRatio(60), 0.001f)
    }

    @Test
    fun `shouldUnlock true when level meets requirement`() {
        val room = HouseRoom("acuerdos", "Sala", "desc", 1, 1, "#FFAA00", requiredLevelToUnlock = 2, unlocked = false)
        assertTrue(engine.shouldUnlock(room, 2))
        assertTrue(engine.shouldUnlock(room, 3))
    }

    @Test
    fun `shouldUnlock false when already unlocked`() {
        val room = HouseRoom("acuerdos", "Sala", "desc", 1, 1, "#FFAA00", requiredLevelToUnlock = 1, unlocked = true)
        assertFalse(engine.shouldUnlock(room, 5))
    }

    @Test
    fun `stateFor locked room returns LOCKED regardless of progress`() {
        val room = HouseRoom("acuerdos", "Sala", "desc", 1, 1, "#FFAA00", requiredLevelToUnlock = 1, unlocked = false)
        val progress = RoomProgress("acuerdos", situationsCompleted = 10, masteryLevel = 10)
        assertEquals(RoomModuleState.LOCKED, engine.stateFor(room, progress))
    }

    @Test
    fun `stateFor unlocked room with null progress is AVAILABLE`() {
        val room = HouseRoom("acuerdos", "Sala", "desc", 1, 1, "#FFAA00", requiredLevelToUnlock = 1, unlocked = true)
        assertEquals(RoomModuleState.AVAILABLE, engine.stateFor(room, null))
    }

    @Test
    fun `stateFor unlocked room with some progress is STARTED`() {
        val room = HouseRoom("acuerdos", "Sala", "desc", 1, 1, "#FFAA00", requiredLevelToUnlock = 1, unlocked = true)
        val progress = RoomProgress("acuerdos", situationsCompleted = 1)
        assertEquals(RoomModuleState.STARTED, engine.stateFor(room, progress))
    }

    @Test
    fun `stateFor unlocked room with 3+ situations is COMPLETED`() {
        val room = HouseRoom("acuerdos", "Sala", "desc", 1, 1, "#FFAA00", requiredLevelToUnlock = 1, unlocked = true)
        val progress = RoomProgress("acuerdos", situationsCompleted = 3)
        assertEquals(RoomModuleState.COMPLETED, engine.stateFor(room, progress))
    }

    @Test
    fun `stateFor unlocked room past mastery threshold is MASTERED`() {
        val room = HouseRoom("acuerdos", "Sala", "desc", 1, 1, "#FFAA00", requiredLevelToUnlock = 1, unlocked = true)
        val progress = RoomProgress("acuerdos", situationsCompleted = 6, masteryLevel = ProgressEngine.MASTERY_THRESHOLD)
        assertEquals(RoomModuleState.MASTERED, engine.stateFor(room, progress))
    }

    @Test
    fun `overallHouseProgress is zero for empty room list`() {
        assertEquals(0f, engine.overallHouseProgress(emptyList(), emptyList()))
    }

    @Test
    fun `overallHouseProgress counts completed and mastered rooms`() {
        val roomA = HouseRoom("a", "A", "d", 1, 1, "#FFAA00", 1, unlocked = true)
        val roomB = HouseRoom("b", "B", "d", 2, 1, "#FFAA00", 1, unlocked = true)
        val progressA = RoomProgress("a", situationsCompleted = 3)
        val progressB = RoomProgress("b", situationsCompleted = 0)
        val result = engine.overallHouseProgress(listOf(roomA, roomB), listOf(progressA, progressB))
        assertEquals(0.5f, result, 0.001f)
    }
}
