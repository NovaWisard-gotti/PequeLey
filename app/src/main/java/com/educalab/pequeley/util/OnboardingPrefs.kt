package com.educalab.pequeley.util

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "pequeley_prefs")
private val ONBOARDING_DONE_KEY = booleanPreferencesKey("onboarding_done")

/** Guarda si el niño ya vio el onboarding, para no repetirlo en cada apertura. */
object OnboardingPrefs {
    fun observe(context: Context): Flow<Boolean> =
        context.dataStore.data.map { it[ONBOARDING_DONE_KEY] ?: false }

    suspend fun setDone(context: Context) {
        context.dataStore.edit { it[ONBOARDING_DONE_KEY] = true }
    }
}
