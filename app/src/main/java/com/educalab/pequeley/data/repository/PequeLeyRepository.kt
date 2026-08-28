package com.educalab.pequeley.data.repository

import com.educalab.pequeley.data.local.PequeLeyDatabase
import com.educalab.pequeley.data.local.entity.*
import com.educalab.pequeley.domain.engine.*
import com.educalab.pequeley.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Repositorio único de PequeLey. Compone los DAOs de Room con los
 * motores de dominio puros, de modo que las reglas de negocio (subir de
 * nivel, desbloquear salas, otorgar insignias, hacer crecer el jardín)
 * viven en domain/engine y son testeables sin Android.
 */
class PequeLeyRepository(private val db: PequeLeyDatabase) {

    private val progressEngine = ProgressEngine()
    private val rewardEngine = RewardEngine()
    private val situationEngine = SituationEngine()
    private val storyEngine = StoryEngine()
    private val agreementEngine = AgreementEngine()
    private val consequenceEngine = ConsequenceEngine()

    // ---------------- Perfil ----------------

    fun observeProfile(): Flow<UserProfileModel?> = db.userProfileDao().observeFirst().map { it?.toModel() }

    suspend fun getOrCreateProfile(): UserProfileModel {
        val existing = db.userProfileDao().getFirst()
        if (existing != null) return existing.toModel()
        return createProfile("Explorador", avatarId = 1)
    }

    suspend fun createProfile(alias: String, avatarId: Int): UserProfileModel {
        val now = System.currentTimeMillis()
        val entity = UserProfileEntity(alias = alias.take(20), avatarId = avatarId, createdAt = now)
        val id = db.userProfileDao().insert(entity)
        db.gardenDao().upsert(GardenProgressEntity(userId = id, lastUpdated = now))
        // Desbloquear las salas de nivel inicial para que la casa no se sienta vacía.
        val rooms = db.houseRoomDao().getAll()
        rooms.filter { it.requiredLevelToUnlock <= 1 }.forEach { room ->
            db.roomUnlockDao().upsert(RoomUnlockEntity(userId = id, roomCode = room.code, unlocked = true, unlockedAt = now))
        }
        return entity.copy(id = id).toModel()
    }

    suspend fun setSoundEnabled(userId: Long, enabled: Boolean) = db.userProfileDao().setSoundEnabled(userId, enabled)
    suspend fun setHapticEnabled(userId: Long, enabled: Boolean) = db.userProfileDao().setHapticEnabled(userId, enabled)

    // ---------------- Casa / Habitaciones ----------------

    fun observeRooms(userId: Long): Flow<List<HouseRoom>> =
        db.roomUnlockDao().observeForUser(userId).map { unlocks ->
            val byCode = unlocks.associateBy { it.roomCode }
            db.houseRoomDao().getAll().map { room -> room.toModel(byCode[room.code]?.unlocked == true) }
        }

    suspend fun getRooms(userId: Long): List<HouseRoom> {
        val unlocks = db.roomUnlockDao().observeForUser(userId).first().associateBy { it.roomCode }
        return db.houseRoomDao().getAll().map { room -> room.toModel(unlocks[room.code]?.unlocked == true) }
    }

    suspend fun getRoomProgress(userId: Long, roomCode: String): RoomProgress? =
        db.progressDao().get(userId, roomCode)?.toModel()

    suspend fun allRoomProgress(userId: Long): List<RoomProgress> =
        db.progressDao().allForUser(userId).map { it.toModel() }

    fun roomState(room: HouseRoom, progress: RoomProgress?): RoomModuleState = progressEngine.stateFor(room, progress)

    suspend fun overallHouseProgress(userId: Long): Float {
        val rooms = getRooms(userId)
        val progresses = allRoomProgress(userId)
        return progressEngine.overallHouseProgress(rooms, progresses)
    }

    /** Revisa el progreso del usuario y desbloquea salas nuevas si ya completó las anteriores. */
    suspend fun refreshRoomUnlocks(userId: Long): List<HouseRoom> {
        val rooms = getRooms(userId)
        val progressByRoom = allRoomProgress(userId).associateBy { it.roomCode }
        val newlyUnlocked = mutableListOf<HouseRoom>()
        rooms.forEach { room ->
            if (progressEngine.shouldUnlock(room, rooms, progressByRoom)) {
                db.roomUnlockDao().upsert(RoomUnlockEntity(userId = userId, roomCode = room.code, unlocked = true, unlockedAt = System.currentTimeMillis()))
                newlyUnlocked += room
            }
        }
        return newlyUnlocked
    }

    /**
     * Punto único para otorgar XP: recalcula el nivel a partir del XP total
     * (nunca de forma incremental) y revisa desbloqueos de salas de inmediato.
     * Antes esto solo ocurría al completar situaciones, así que una sala podía
     * quedar bloqueada aunque el nivel ya alcanzara, si el XP llegó por una
     * historia, un acuerdo o un desafío.
     */
    private suspend fun grantXpAndRefreshUnlocks(userId: Long, xp: Int) {
        if (xp != 0) db.userProfileDao().addXp(userId, xp)
        val profile = db.userProfileDao().get(userId) ?: return
        val newLevel = progressEngine.levelForXp(profile.totalXp)
        if (newLevel != profile.currentLevel) db.userProfileDao().setLevel(userId, newLevel)
        refreshRoomUnlocks(userId)
    }

    private suspend fun markCompleted(userId: Long, activityCode: String) {
        db.completedActivityDao().insert(CompletedActivityEntity(userId = userId, activityCode = activityCode, completedAt = System.currentTimeMillis()))
    }

    suspend fun getCompletedActivityCodes(userId: Long): Set<String> =
        db.completedActivityDao().completedCodesForUser(userId).toSet()

    fun observeCompletedActivities(userId: Long): Flow<Set<String>> =
        db.completedActivityDao().observeCompletedCodes(userId).map { it.toSet() }

    // ---------------- Conceptos / Personajes / Responsabilidades / Derechos ----------------

    suspend fun getConcepts(): List<LegalConcept> =
        db.legalConceptDao().getAll().map { concept -> concept.toModel(db.legalConceptDao().storiesFor(concept.code)) }

    suspend fun getCharacters(): List<CharacterModel> = db.characterDao().getAll().map { it.toModel() }

    suspend fun getCharacterExpressions(code: String): List<CharacterExpressionModel> =
        db.characterDao().expressionsFor(code).map { it.toModel() }

    suspend fun getResponsibilityTasks(): List<ResponsibilityTaskModel> = db.responsibilityDao().getAll().map { it.toModel(completed = false) }

    suspend fun getRightLessons(): List<RightLessonModel> = db.rightLessonDao().getAll().map { it.toModel(opened = false) }

    // ---------------- Situaciones ----------------

    suspend fun getSituationsForRoom(roomCode: String): List<Situation> =
        db.situationDao().situationsForRoom(roomCode).map { loadFullSituation(it) }

    suspend fun getSituation(code: String): Situation? =
        db.situationDao().getSituation(code)?.let { loadFullSituation(it) }

    private suspend fun loadFullSituation(entity: DailySituationEntity): Situation {
        val steps = db.situationDao().stepsFor(entity.code).map { step ->
            val decisions = db.situationDao().decisionsFor(entity.code, step.orderIndex).map { decision ->
                decision.toModel(db.situationDao().consequencesFor(decision.id))
            }
            step.toModel(decisions)
        }
        return entity.toModel(steps)
    }

    fun startSituation(situation: Situation): SituationSession = situationEngine.start(situation)
    fun advanceSituation(session: SituationSession): SituationSession = situationEngine.advance(session)
    fun chooseSituationDecision(session: SituationSession, decision: DecisionModel): SituationSession =
        situationEngine.chooseDecision(session, decision)

    fun compareDecisions(a: DecisionModel, b: DecisionModel): ConsequenceComparison = consequenceEngine.compare(a, b)
    fun reflectionFor(consequence: ConsequenceModel): String = consequenceEngine.reflectionFor(consequence)

    /** Persiste el resultado de una sesión de situación terminada: XP, progreso, jardín e insignias nuevas. */
    suspend fun completeSituation(userId: Long, session: SituationSession): List<BadgeModel> {
        require(session.finished) { "La sesión debe estar finalizada antes de guardarla." }
        val xp = situationEngine.sessionXp(session)
        val gardenImpact = session.consequencesShown.fold(0) { acc, c -> acc + c.gardenImpact }

        val existing = db.progressDao().get(userId, session.situation.roomCode)
        val updatedProgress = (existing ?: ProgressEntity(userId = userId, roomCode = session.situation.roomCode, updatedAt = System.currentTimeMillis()))
            .copy(situationsCompleted = (existing?.situationsCompleted ?: 0) + 1, updatedAt = System.currentTimeMillis())
        db.progressDao().upsert(updatedProgress)

        applyGardenImpact(userId, gardenImpact)
        grantXpAndRefreshUnlocks(userId, xp)
        markCompleted(userId, session.situation.code)
        return evaluateAndAwardBadges(userId)
    }

    // ---------------- Historias ----------------

    suspend fun getStories(): List<StoryModel> = db.storyDao().getAll().map { loadFullStory(it) }

    suspend fun getStory(code: String): StoryModel? = db.storyDao().getByCode(code)?.let { loadFullStory(it) }

    private suspend fun loadFullStory(entity: StoryEntity): StoryModel {
        val scenes = db.storyDao().scenesFor(entity.code).map { scene ->
            scene.toModel(db.storyDao().choicesFor(scene.id).map { it.toModel() })
        }
        return entity.toModel(scenes)
    }

    fun startStory(story: StoryModel): StorySession = storyEngine.start(story)
    fun chooseStoryOption(session: StorySession, choice: StoryChoiceModel): StorySession = storyEngine.choose(session, choice)

    suspend fun completeStory(userId: Long, roomCode: String, storyCode: String): List<BadgeModel> {
        val existing = db.progressDao().get(userId, roomCode)
        val updated = (existing ?: ProgressEntity(userId = userId, roomCode = roomCode, updatedAt = System.currentTimeMillis()))
            .copy(storiesCompleted = (existing?.storiesCompleted ?: 0) + 1, updatedAt = System.currentTimeMillis())
        db.progressDao().upsert(updated)
        grantXpAndRefreshUnlocks(userId, 8)
        markCompleted(userId, storyCode)
        return evaluateAndAwardBadges(userId)
    }

    // ---------------- Acuerdos ----------------

    suspend fun getAgreements(userId: Long): List<AgreementModel> {
        val agreements = db.agreementDao().observeForUser(userId).first()
        return agreements.map { agreement ->
            val items = db.agreementDao().itemsFor(agreement.id).map { it.symbolCode }
            AgreementModel(agreement.id, agreement.title, agreement.situationCode, items, agreement.createdAt)
        }
    }

    suspend fun createAgreement(userId: Long, roomCode: String, title: String, situationCode: String?, symbolCodes: List<String>): AgreementModel {
        val built = agreementEngine.build(title, situationCode, symbolCodes, System.currentTimeMillis())
        val agreementId = db.agreementDao().insertAgreement(
            AgreementEntity(userId = userId, situationCode = built.situationCode, title = built.title, createdAt = built.createdAt)
        )
        val items = built.items.mapIndexed { index, code ->
            AgreementItemEntity(agreementId = agreementId, symbolCode = code, orderIndex = index, label = AgreementSymbols.ALL.first { it.code == code }.label)
        }
        db.agreementDao().insertItems(items)

        val existing = db.progressDao().get(userId, roomCode)
        val updated = (existing ?: ProgressEntity(userId = userId, roomCode = roomCode, updatedAt = System.currentTimeMillis()))
            .copy(agreementsCreated = (existing?.agreementsCreated ?: 0) + 1, updatedAt = System.currentTimeMillis())
        db.progressDao().upsert(updated)
        applyGardenImpact(userId, 5)
        grantXpAndRefreshUnlocks(userId, 10)

        return built.copy(id = agreementId)
    }

    fun isSolidAgreement(agreement: AgreementModel): Boolean = agreementEngine.isSolidAgreement(agreement)

    // ---------------- Desafíos ----------------

    suspend fun getChallenges(): List<ChallengeModel> = db.challengeDao().getAll().map { it.toModel(completed = false) }

    suspend fun recordChallengeAttempt(userId: Long, roomCode: String, challengeCode: String, success: Boolean, stepsData: String): List<BadgeModel> {
        db.challengeDao().insertAttempt(
            ChallengeAttemptEntity(userId = userId, challengeCode = challengeCode, completedAt = System.currentTimeMillis(), success = success, stepsData = stepsData)
        )
        if (success) {
            val existing = db.progressDao().get(userId, roomCode)
            val updated = (existing ?: ProgressEntity(userId = userId, roomCode = roomCode, updatedAt = System.currentTimeMillis()))
                .copy(challengesCompleted = (existing?.challengesCompleted ?: 0) + 1, updatedAt = System.currentTimeMillis())
            db.progressDao().upsert(updated)
            grantXpAndRefreshUnlocks(userId, 12)
            markCompleted(userId, challengeCode)
        }
        return evaluateAndAwardBadges(userId)
    }

    // ---------------- Jardín ----------------

    fun observeGarden(userId: Long): Flow<GardenState> = db.gardenDao().observe(userId).map { it?.toModel() ?: GardenState() }

    private suspend fun applyGardenImpact(userId: Long, impact: Int) {
        val current = db.gardenDao().get(userId) ?: GardenProgressEntity(userId = userId, lastUpdated = System.currentTimeMillis())
        val currentState = current.toModel()
        // El progreso en bruto se guarda tal cual (current.accumulatedImpact); antes se
        // reconstruía como growthLevel*10, lo que descartaba todo el avance parcial
        // dentro del nivel actual y hacía que el jardín pareciera no cambiar nunca.
        val newState = rewardEngine.applyGardenImpact(currentState, impact, current.accumulatedImpact)
        db.gardenDao().upsert(
            current.copy(
                growthLevel = newState.growthLevel, flowers = newState.flowers,
                paths = newState.paths, animals = newState.animals,
                accumulatedImpact = (current.accumulatedImpact + impact).coerceAtLeast(0),
                lastUpdated = System.currentTimeMillis()
            )
        )
    }

    // ---------------- Insignias ----------------

    fun observeEarnedBadges(userId: Long): Flow<List<String>> = db.badgeDao().observeEarned(userId).map { list -> list.map { it.badgeCode } }

    suspend fun getBadges(userId: Long): List<BadgeModel> {
        val earned = db.badgeDao().earnedForUser(userId).map { it.badgeCode }.toSet()
        return db.badgeDao().getAll().map { it.toModel(earned = it.code in earned) }
    }

    private suspend fun evaluateAndAwardBadges(userId: Long): List<BadgeModel> {
        val profile = db.userProfileDao().get(userId) ?: return emptyList()
        val allProgress = db.progressDao().allForUser(userId)
        val garden = db.gardenDao().get(userId)
        val roomsUnlocked = db.roomUnlockDao().observeForUser(userId).first().count { it.unlocked }

        val stats = PlayerStats(
            situationsCompleted = allProgress.sumSafe { it.situationsCompleted },
            storiesCompleted = allProgress.sumSafe { it.storiesCompleted },
            agreementsCreated = allProgress.sumSafe { it.agreementsCreated },
            challengesCompleted = allProgress.sumSafe { it.challengesCompleted },
            roomsUnlocked = roomsUnlocked,
            positiveConsequences = allProgress.sumSafe { it.situationsCompleted }, // aproximación: cada situación completada suma
            gardenLevel = garden?.growthLevel ?: 0,
            totalXp = profile.totalXp
        )

        val allBadges = db.badgeDao().getAll().map { it.toModel(earned = false) }
        val earnedCodes = db.badgeDao().earnedForUser(userId).map { it.badgeCode }.toSet()
        val newBadges = rewardEngine.evaluateNewBadges(allBadges, earnedCodes, stats)
        newBadges.forEach { badge ->
            db.badgeDao().awardBadge(UserBadgeEntity(userId = userId, badgeCode = badge.code, earnedAt = System.currentTimeMillis()))
        }
        return newBadges
    }

    private inline fun <T> List<T>.sumSafe(selector: (T) -> Int): Int = this.fold(0) { acc, item -> acc + selector(item) }
}
