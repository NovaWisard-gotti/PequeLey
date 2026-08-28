package com.educalab.pequeley.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.educalab.pequeley.data.local.dao.*
import com.educalab.pequeley.data.local.entity.*
import com.educalab.pequeley.data.local.seed.SeedRunner
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserProfileEntity::class,
        HouseRoomEntity::class,
        RoomUnlockEntity::class,
        LegalConceptEntity::class,
        ConceptStoryEntity::class,
        CharacterEntity::class,
        CharacterExpressionEntity::class,
        DailySituationEntity::class,
        SituationStepEntity::class,
        DecisionEntity::class,
        DecisionConsequenceEntity::class,
        AgreementEntity::class,
        AgreementItemEntity::class,
        ResponsibilityTaskEntity::class,
        RightLessonEntity::class,
        StoryEntity::class,
        StorySceneEntity::class,
        StoryChoiceEntity::class,
        ChallengeEntity::class,
        ChallengeAttemptEntity::class,
        ProgressEntity::class,
        BadgeEntity::class,
        UserBadgeEntity::class,
        GardenProgressEntity::class,
        UnlockedDecorationEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class PequeLeyDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao
    abstract fun houseRoomDao(): HouseRoomDao
    abstract fun roomUnlockDao(): RoomUnlockDao
    abstract fun legalConceptDao(): LegalConceptDao
    abstract fun characterDao(): CharacterDao
    abstract fun situationDao(): SituationDao
    abstract fun agreementDao(): AgreementDao
    abstract fun responsibilityDao(): ResponsibilityDao
    abstract fun rightLessonDao(): RightLessonDao
    abstract fun storyDao(): StoryDao
    abstract fun challengeDao(): ChallengeDao
    abstract fun progressDao(): ProgressDao
    abstract fun badgeDao(): BadgeDao
    abstract fun gardenDao(): GardenDao

    companion object {
        private const val DB_NAME = "pequeley.db"

        @Volatile private var INSTANCE: PequeLeyDatabase? = null

        fun getInstance(context: Context): PequeLeyDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }
        }

        private fun build(context: Context): PequeLeyDatabase {
            return Room.databaseBuilder(context.applicationContext, PequeLeyDatabase::class.java, DB_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // La base de datos se acaba de crear (primera instalación):
                        // poblar con los datos semilla para que la app se sienta
                        // completa desde el primer uso (ver sección 24 de la
                        // especificación maestra).
                        CoroutineScope(Dispatchers.IO).launch {
                            val instance = INSTANCE
                            if (instance != null) {
                                SeedRunner.run(instance)
                            }
                        }
                    }
                })
                .build()
        }
    }
}
