-- ============================================================
-- PequeLey — Esquema de base de datos (SQLite / Room)
-- Generado a partir de las entidades reales en
-- app/src/main/java/com/educalab/pequeley/data/local/entity/
-- ============================================================

CREATE TABLE user_profile (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    alias TEXT NOT NULL,
    avatarId INTEGER NOT NULL,
    createdAt INTEGER NOT NULL,
    totalXp INTEGER NOT NULL DEFAULT 0,
    currentLevel INTEGER NOT NULL DEFAULT 1,
    soundEnabled INTEGER NOT NULL DEFAULT 1,
    hapticEnabled INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE house_room (
    code TEXT PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    orderIndex INTEGER NOT NULL,
    illustrationSeed INTEGER NOT NULL,
    colorHex TEXT NOT NULL,
    requiredLevelToUnlock INTEGER NOT NULL
);

CREATE TABLE room_unlock (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    roomCode TEXT NOT NULL,
    unlocked INTEGER NOT NULL,
    unlockedAt INTEGER,
    FOREIGN KEY (userId) REFERENCES user_profile(id) ON DELETE CASCADE,
    FOREIGN KEY (roomCode) REFERENCES house_room(code) ON DELETE CASCADE
);
CREATE UNIQUE INDEX index_room_unlock_userId_roomCode ON room_unlock(userId, roomCode);

CREATE TABLE legal_concept (
    code TEXT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    everydayExplanation TEXT NOT NULL,
    illustrationSeed INTEGER NOT NULL
);

CREATE TABLE concept_story (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    conceptCode TEXT NOT NULL,
    title TEXT NOT NULL,
    body TEXT NOT NULL,
    illustrationSeed INTEGER NOT NULL,
    FOREIGN KEY (conceptCode) REFERENCES legal_concept(code) ON DELETE CASCADE
);
CREATE INDEX index_concept_story_conceptCode ON concept_story(conceptCode);

CREATE TABLE character (
    code TEXT PRIMARY KEY NOT NULL,
    name TEXT NOT NULL,
    role TEXT NOT NULL,
    personality TEXT NOT NULL,
    shapeSeed INTEGER NOT NULL,
    paletteSeed INTEGER NOT NULL,
    accessorySeed INTEGER NOT NULL
);

CREATE TABLE character_expression (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    characterCode TEXT NOT NULL,
    mood TEXT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (characterCode) REFERENCES character(code) ON DELETE CASCADE
);
CREATE INDEX index_character_expression_characterCode ON character_expression(characterCode);

CREATE TABLE daily_situation (
    code TEXT PRIMARY KEY NOT NULL,
    roomCode TEXT NOT NULL,
    title TEXT NOT NULL,
    summary TEXT NOT NULL,
    difficulty INTEGER NOT NULL,
    mechanicType TEXT NOT NULL,
    illustrationSeed INTEGER NOT NULL,
    FOREIGN KEY (roomCode) REFERENCES house_room(code) ON DELETE CASCADE
);
CREATE INDEX index_daily_situation_roomCode ON daily_situation(roomCode);

CREATE TABLE situation_step (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    situationCode TEXT NOT NULL,
    orderIndex INTEGER NOT NULL,
    stepType TEXT NOT NULL,
    prompt TEXT NOT NULL,
    illustrationSeed INTEGER NOT NULL,
    FOREIGN KEY (situationCode) REFERENCES daily_situation(code) ON DELETE CASCADE
);
CREATE INDEX index_situation_step_situationCode ON situation_step(situationCode);

CREATE TABLE decision (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    situationCode TEXT NOT NULL,
    stepOrderIndex INTEGER NOT NULL,
    label TEXT NOT NULL,
    description TEXT NOT NULL,
    FOREIGN KEY (situationCode) REFERENCES daily_situation(code) ON DELETE CASCADE
);
CREATE INDEX index_decision_situationCode ON decision(situationCode);

CREATE TABLE decision_consequence (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    decisionId INTEGER NOT NULL,
    outcomeText TEXT NOT NULL,
    isPositive INTEGER NOT NULL,
    gardenImpact INTEGER NOT NULL,
    xpAward INTEGER NOT NULL,
    FOREIGN KEY (decisionId) REFERENCES decision(id) ON DELETE CASCADE
);
CREATE INDEX index_decision_consequence_decisionId ON decision_consequence(decisionId);

CREATE TABLE agreement (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    situationCode TEXT,
    title TEXT NOT NULL,
    createdAt INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES user_profile(id) ON DELETE CASCADE
);
CREATE INDEX index_agreement_userId ON agreement(userId);

CREATE TABLE agreement_item (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    agreementId INTEGER NOT NULL,
    symbolCode TEXT NOT NULL,
    orderIndex INTEGER NOT NULL,
    label TEXT NOT NULL,
    FOREIGN KEY (agreementId) REFERENCES agreement(id) ON DELETE CASCADE
);
CREATE INDEX index_agreement_item_agreementId ON agreement_item(agreementId);

CREATE TABLE responsibility_task (
    code TEXT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    objectIllustrationSeed INTEGER NOT NULL,
    careAction TEXT NOT NULL
);

CREATE TABLE right_lesson (
    code TEXT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    everydayExplanation TEXT NOT NULL,
    storyText TEXT NOT NULL,
    illustrationSeed INTEGER NOT NULL
);

CREATE TABLE story (
    code TEXT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    summary TEXT NOT NULL,
    mechanicType TEXT NOT NULL,
    coverIllustrationSeed INTEGER NOT NULL
);

CREATE TABLE story_scene (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    storyCode TEXT NOT NULL,
    orderIndex INTEGER NOT NULL,
    text TEXT NOT NULL,
    illustrationSeed INTEGER NOT NULL,
    FOREIGN KEY (storyCode) REFERENCES story(code) ON DELETE CASCADE
);
CREATE INDEX index_story_scene_storyCode ON story_scene(storyCode);

CREATE TABLE story_choice (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    sceneId INTEGER NOT NULL,
    label TEXT NOT NULL,
    leadsToSceneOrder INTEGER,
    isEnding INTEGER NOT NULL,
    consequenceText TEXT NOT NULL,
    FOREIGN KEY (sceneId) REFERENCES story_scene(id) ON DELETE CASCADE
);
CREATE INDEX index_story_choice_sceneId ON story_choice(sceneId);

CREATE TABLE challenge (
    code TEXT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    situationRef TEXT NOT NULL,
    difficulty INTEGER NOT NULL
);

CREATE TABLE challenge_attempt (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    challengeCode TEXT NOT NULL,
    completedAt INTEGER,
    success INTEGER NOT NULL,
    stepsData TEXT NOT NULL,
    FOREIGN KEY (userId) REFERENCES user_profile(id) ON DELETE CASCADE,
    FOREIGN KEY (challengeCode) REFERENCES challenge(code) ON DELETE CASCADE
);
CREATE INDEX index_challenge_attempt_userId ON challenge_attempt(userId);
CREATE INDEX index_challenge_attempt_challengeCode ON challenge_attempt(challengeCode);

CREATE TABLE progress (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    roomCode TEXT NOT NULL,
    situationsCompleted INTEGER NOT NULL DEFAULT 0,
    storiesCompleted INTEGER NOT NULL DEFAULT 0,
    challengesCompleted INTEGER NOT NULL DEFAULT 0,
    agreementsCreated INTEGER NOT NULL DEFAULT 0,
    masteryLevel INTEGER NOT NULL DEFAULT 0,
    updatedAt INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES user_profile(id) ON DELETE CASCADE,
    FOREIGN KEY (roomCode) REFERENCES house_room(code) ON DELETE CASCADE
);
CREATE UNIQUE INDEX index_progress_userId_roomCode ON progress(userId, roomCode);

CREATE TABLE badge (
    code TEXT PRIMARY KEY NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    illustrationSeed INTEGER NOT NULL,
    criteriaType TEXT NOT NULL,
    criteriaValue INTEGER NOT NULL
);

CREATE TABLE user_badge (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    badgeCode TEXT NOT NULL,
    earnedAt INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES user_profile(id) ON DELETE CASCADE,
    FOREIGN KEY (badgeCode) REFERENCES badge(code) ON DELETE CASCADE
);
CREATE UNIQUE INDEX index_user_badge_userId_badgeCode ON user_badge(userId, badgeCode);

CREATE TABLE garden_progress (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    growthLevel INTEGER NOT NULL DEFAULT 0,
    flowers INTEGER NOT NULL DEFAULT 0,
    paths INTEGER NOT NULL DEFAULT 0,
    animals INTEGER NOT NULL DEFAULT 0,
    lastUpdated INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES user_profile(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX index_garden_progress_userId ON garden_progress(userId);

CREATE TABLE unlocked_decoration (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER NOT NULL,
    decorationCode TEXT NOT NULL,
    unlockedAt INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES user_profile(id) ON DELETE CASCADE
);
CREATE INDEX index_unlocked_decoration_userId ON unlocked_decoration(userId);

-- ============================================================
-- Fin del esquema (25 tablas)
-- ============================================================
